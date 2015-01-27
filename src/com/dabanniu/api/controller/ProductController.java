package com.dabanniu.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.service.impl.ProductService;

@Controller
public class ProductController {
	
	private ProductService productService;
	
	/**
	 * 产品列表
	 * */
	@RequestMapping("/getProductDetail.do")
    public void getProductDetail(HttpServletRequest request,HttpServletResponse response,ApiContext apiContext,
    		@RequestParam(value="productId",required=true) long productId) throws Exception {
		productService.getProductDetail(request, response,apiContext,productId);
    }	
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
}
