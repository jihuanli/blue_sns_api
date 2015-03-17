package com.dabanniu.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.core.response.ErrorResponse;
import com.dabanniu.core.utils.JsonResponseUtils;
import com.dabanniu.core.utils.JsonUtils;
import com.dabanniu.dataprovider.bean.ProductBean;
import com.dabanniu.dataprovider.impl.ProductProvider;

public class ProductService {
	
	private ProductProvider productProvider;
	
	public void getProductDetail(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext,long productId) throws Exception{
		System.out.println("in1");
		ProductBean product = productProvider.getProduct(productId);
		if (product == null) {
			JsonResponseUtils.writeJson(response, new ErrorResponse("无效的产品！"));
			return;
		}
		JsonResponseUtils.writeJson(response, JsonUtils.objectToJsonString(product));
		return;
	}    
    
	public void setProductProvider(ProductProvider productProvider) {
		this.productProvider = productProvider;
	}
}
