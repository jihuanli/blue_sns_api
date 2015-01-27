package com.dabanniu.dataprovider.impl;

import com.dabanniu.core.utils.BeanUtils;
import com.dabanniu.dao.impl.ProductDao;
import com.dabanniu.dataprovider.bean.ProductBean;
import com.dabanniu.model.Product;

public class ProductProvider {
	
	private ProductDao productDao;
	
	/**
	 * 获取产品信息
	 * @throws Exception 
	 * */
	
	public ProductBean getProduct(long productId) throws Exception {
		Product product = productDao.getProduct(productId);
		ProductBean bean = new ProductBean(); 
		BeanUtils.copyProperties(product, bean);
		return bean;
	}	
	
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
