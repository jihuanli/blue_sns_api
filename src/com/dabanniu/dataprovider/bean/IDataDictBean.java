package com.dabanniu.dataprovider.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IDataDictBean {
	@JsonIgnore
	public String getDataDictKey();
}
