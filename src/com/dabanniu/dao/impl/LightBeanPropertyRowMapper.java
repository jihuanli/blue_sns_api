/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.dao.impl;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-4-9<p>
//-------------------------------------------------------
public class LightBeanPropertyRowMapper<T> implements RowMapper<T> {

    /** Logger available to subclasses */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /** The class we are mapping to */
    private Class<T> mappedClass;

    /** Whether we're strictly validating */
    private boolean checkFullyPopulated = false;

    /** Whether we're defaulting primitives when mapping a null value */
    private boolean primitivesDefaultedForNullValue = false;

    /** Map of the fields we provide mapping for */
    private Map<String, PropertyDescriptor> mappedFields;

    /** Set of bean properties we provide mapping for */
    private Set<String> mappedProperties;

    /**
     * Create a new LightBeanPropertyRowMapper for bean-style configuration.
     * 
     * @see #setMappedClass
     * @see #setCheckFullyPopulated
     */
    public LightBeanPropertyRowMapper() {
    }

    /**
     * Create a new LightBeanPropertyRowMapper, accepting unpopulated properties in the target bean.
     * <p>
     * Consider using the {@link #newInstance} factory method instead, which allows for specifying the mapped type once
     * only.
     * 
     * @param mappedClass the class that each row should be mapped to
     */
    public LightBeanPropertyRowMapper(Class<T> mappedClass) {
        initialize(mappedClass);
    }

    /**
     * Create a new LightBeanPropertyRowMapper.
     * 
     * @param mappedClass the class that each row should be mapped to
     * @param checkFullyPopulated whether we're strictly validating that all bean properties have been mapped from
     *        corresponding database fields
     */
    public LightBeanPropertyRowMapper(Class<T> mappedClass, boolean checkFullyPopulated) {
        initialize(mappedClass);
        this.checkFullyPopulated = checkFullyPopulated;
    }

    /**
     * Set the class that each row should be mapped to.
     */
    public void setMappedClass(Class<T> mappedClass) {
        if (this.mappedClass == null) {
            initialize(mappedClass);
        } else {
            if (!this.mappedClass.equals(mappedClass)) {
                throw new InvalidDataAccessApiUsageException("The mapped class can not be reassigned to map to "
                        + mappedClass + " since it is already providing mapping for " + this.mappedClass);
            }
        }
    }

    /**
     * Initialize the mapping metadata for the given class.
     * 
     * @param mappedClass the mapped class.
     */
    protected void initialize(Class<T> mappedClass) {
        this.mappedClass = mappedClass;
        this.mappedFields = new HashMap<String, PropertyDescriptor>();
        this.mappedProperties = new HashSet<String>();
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
        for (PropertyDescriptor pd : pds) {
            if (pd.getWriteMethod() != null) {
                this.mappedFields.put(pd.getName().toLowerCase(), pd);
                String underscoredName = underscoreName(pd.getName());
                if (!pd.getName().toLowerCase().equals(underscoredName)) {
                    this.mappedFields.put(underscoredName, pd);
                }
                this.mappedProperties.add(pd.getName());
            }
        }
    }

    /**
     * Convert a name in camelCase to an underscored name in lower case. Any upper case letters are converted to lower
     * case with a preceding underscore.
     * 
     * @param name the string containing original name
     * @return the converted name
     */
    private String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            result.append(name.substring(0, 1).toLowerCase());
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if (s.equals(s.toUpperCase())) {
                    result.append("_");
                    result.append(s.toLowerCase());
                } else {
                    result.append(s);
                }
            }
        }
        return result.toString();
    }

    /**
     * Get the class that we are mapping to.
     */
    public final Class<T> getMappedClass() {
        return this.mappedClass;
    }

    /**
     * Set whether we're strictly validating that all bean properties have been mapped from corresponding database
     * fields.
     * <p>
     * Default is <code>false</code>, accepting unpopulated properties in the target bean.
     */
    public void setCheckFullyPopulated(boolean checkFullyPopulated) {
        this.checkFullyPopulated = checkFullyPopulated;
    }

    /**
     * Return whether we're strictly validating that all bean properties have been mapped from corresponding database
     * fields.
     */
    public boolean isCheckFullyPopulated() {
        return this.checkFullyPopulated;
    }

    /**
     * Set whether we're defaulting Java primitives in the case of mapping a null value from corresponding database
     * fields.
     * <p>
     * Default is <code>false</code>, throwing an exception when nulls are mapped to Java primitives.
     */
    public void setPrimitivesDefaultedForNullValue(boolean primitivesDefaultedForNullValue) {
        this.primitivesDefaultedForNullValue = primitivesDefaultedForNullValue;
    }

    /**
     * Return whether we're defaulting Java primitives in the case of mapping a null value from corresponding database
     * fields.
     */
    public boolean isPrimitivesDefaultedForNullValue() {
        return primitivesDefaultedForNullValue;
    }

    /**
     * Extract the values for all columns in the current row.
     * <p>
     * Utilizes public setters and result set metadata.
     * 
     * @see java.sql.ResultSetMetaData
     */
    public T mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Assert.state(this.mappedClass != null, "Mapped class was not specified");
        T mappedObject = BeanUtils.instantiate(this.mappedClass);
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
        initBeanWrapper(bw);

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Set<String> populatedProperties = (isCheckFullyPopulated() ? new HashSet<String>() : null);

        for (int index = 1; index <= columnCount; index++) {
            String column = JdbcUtils.lookupColumnName(rsmd, index);
            PropertyDescriptor pd = this.mappedFields.get(column.replaceAll(" ", "").toLowerCase());
            if (pd != null) {
                try {
                    Object value = getColumnValue(rs, index, pd);
                    if (logger.isDebugEnabled() && rowNumber == 0) {
                        logger.debug("Mapping column '" + column + "' to property '" + pd.getName() + "' of type "
                                + pd.getPropertyType());
                    }
                    try {
                        //null不处理
                        if (primitivesDefaultedForNullValue) {
                            if (value != null) {
                                bw.setPropertyValue(pd.getName(), value);
                            }
                        } else {
                            bw.setPropertyValue(pd.getName(), value);
                        }
                    } catch (TypeMismatchException e) {
                        if (value == null && primitivesDefaultedForNullValue) {
                            logger.debug("Intercepted TypeMismatchException for row " + rowNumber + " and column '"
                                    + column + "' with value " + value + " when setting property '" + pd.getName()
                                    + "' of type " + pd.getPropertyType() + " on object: " + mappedObject);
                        } else {
                            throw e;
                        }
                    }
                    if (populatedProperties != null) {
                        populatedProperties.add(pd.getName());
                    }
                } catch (NotWritablePropertyException ex) {
                    throw new DataRetrievalFailureException("Unable to map column " + column + " to property "
                            + pd.getName(), ex);
                }
            }
        }

        if (populatedProperties != null && !populatedProperties.equals(this.mappedProperties)) {
            throw new InvalidDataAccessApiUsageException("Given ResultSet does not contain all fields "
                    + "necessary to populate object of class [" + this.mappedClass + "]: " + this.mappedProperties);
        }

        return mappedObject;
    }

    /**
     * Initialize the given BeanWrapper to be used for row mapping. To be called for each row.
     * <p>
     * The default implementation is empty. Can be overridden in subclasses.
     * 
     * @param bw the BeanWrapper to initialize
     */
    protected void initBeanWrapper(BeanWrapper bw) {
    }

    /**
     * Retrieve a JDBC object value for the specified column.
     * <p>
     * The default implementation calls {@link JdbcUtils#getResultSetValue(java.sql.ResultSet, int, Class)}. Subclasses
     * may override this to check specific value types upfront, or to post-process values return from
     * <code>getResultSetValue</code>.
     * 
     * @param rs is the ResultSet holding the data
     * @param index is the column index
     * @param pd the bean property that each result object is expected to match (or <code>null</code> if none specified)
     * @return the Object value
     * @throws SQLException in case of extraction failure
     * @see org.springframework.jdbc.support.JdbcUtils#getResultSetValue(java.sql.ResultSet, int, Class)
     */
    protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index, pd.getPropertyType());
    }

    /**
     * Static factory method to create a new LightBeanPropertyRowMapper (with the mapped class specified only once).
     * 
     * @param mappedClass the class that each row should be mapped to
     */
    public static <T> LightBeanPropertyRowMapper<T> newInstance(Class<T> mappedClass) {
        LightBeanPropertyRowMapper<T> newInstance = new LightBeanPropertyRowMapper<T>();
        newInstance.setMappedClass(mappedClass);
        return newInstance;
    }

}
