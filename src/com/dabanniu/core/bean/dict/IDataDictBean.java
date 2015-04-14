package com.dabanniu.core.bean.dict;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IDataDictBean {
	@JsonIgnore
	public String getDataDictKey();
}
