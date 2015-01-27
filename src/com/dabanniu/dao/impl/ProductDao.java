package com.dabanniu.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import com.dabanniu.model.Product;

public class ProductDao extends NamedParameterJdbcDaoSupport{
	private static final LightBeanPropertyRowMapper<Product> MAPPER = new LightBeanPropertyRowMapper<Product>(
			Product.class);

	static {
		MAPPER.setPrimitivesDefaultedForNullValue(true);
	}

	/**
	 * 获取产品信息
	 * */
	private static final String GET_PRODUCT = "select * from dbn_products where id=?  ";

	public Product getProduct(long productId) {
		List<Product> list = this.getJdbcTemplate().query(
				GET_PRODUCT,new Object[] { productId }, MAPPER);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}	
}
