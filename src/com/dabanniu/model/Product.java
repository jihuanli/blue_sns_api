package com.dabanniu.model;

import java.io.Serializable;

import com.dabanniu.model.BaseModel;

public class Product extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = -3354231969216571323L;

	private String name;
	
	private String alias;
	
	//功效
	private String effect;
	
	//规格
	private String size;
	
	//价格
	private Float price;
	
	//描述
	private String details;
	
	//查看状态
	private Integer view_status=0;
	
	//审核状态
	private Integer verify_status=0;
	
	private String productSource;
	
	private String ingredientSource; 
	
	private Long category_id;
	
	private Long brand_id;
	
	private Integer viewNum=0;
	
	private Integer usedNum=0;
	
	private Integer likeNum=0;
	
	private Integer isStop=0;
	
	private Integer isStarProduct=0;
	
	private  String officialLink;
	
	private Integer officialLinkFlag=0;
	
	private Integer questionNum=0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getView_status() {
		return view_status;
	}

	public void setView_status(Integer viewStatus) {
		view_status = viewStatus;
	}

	public Integer getVerify_status() {
		return verify_status;
	}

	public void setVerify_status(Integer verifyStatus) {
		verify_status = verifyStatus;
	}

	public String getProductSource() {
		return productSource;
	}

	public void setProductSource(String productSource) {
		this.productSource = productSource;
	}

	public String getIngredientSource() {
		return ingredientSource;
	}

	public void setIngredientSource(String ingredientSource) {
		this.ingredientSource = ingredientSource;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long categoryId) {
		category_id = categoryId;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brandId) {
		brand_id = brandId;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Integer getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getIsStop() {
		return isStop;
	}

	public void setIsStop(Integer isStop) {
		this.isStop = isStop;
	}

	public Integer getIsStarProduct() {
		return isStarProduct;
	}

	public void setIsStarProduct(Integer isStarProduct) {
		this.isStarProduct = isStarProduct;
	}

	public String getOfficialLink() {
		return officialLink;
	}

	public void setOfficialLink(String officialLink) {
		this.officialLink = officialLink;
	}

	public Integer getOfficialLinkFlag() {
		return officialLinkFlag;
	}

	public void setOfficialLinkFlag(Integer officialLinkFlag) {
		this.officialLinkFlag = officialLinkFlag;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
	

}
