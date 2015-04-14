package com.dabanniu.core.bean.dict;

import java.util.HashMap;
import java.util.Set;

@SuppressWarnings("unchecked")
public class DataDict<T> extends HashMap<String, T> {
	
	private static final long serialVersionUID = -7069334823044189471L;
	
	private Class dataClass;

	public DataDict() {
		super();
	}
	
	public DataDict(Class<T> c) {
		super();
		this.dataClass=c;
	}
	
	
	public Class getDataClass() {
		return dataClass;
	}


	@Override
	public T get(Object key) {
		if (key==null)
			return null;
		return super.get(key.toString());
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return super.keySet();
	}

	@Override
	public T put(String key, T value) {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}
	public T put(Number key, T value) {
		// TODO Auto-generated method stub
		return super.put(key.toString(), value);
	}
	
	public T putDictBean (IDataDictBean value) {		
		return super.put(value.getDataDictKey(), (T) value);
	}
}
