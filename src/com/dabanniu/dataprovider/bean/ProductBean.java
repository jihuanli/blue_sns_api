package com.dabanniu.dataprovider.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductBean implements IDataDictBean{
	
	@JsonProperty("productId")
	private Long id;
	
	private String name;
	
	private String alias;
	
	//功效
	private String effect;
	
	//规格
	private String size;
	
	private Float price;
	
	//描述
	private String details;
	
	@JsonIgnore
	private Long category_id;
	
	@JsonIgnore
	private Long brand_id;

	private Integer reviewNum=0;
	
	private Integer topicNum=0;

	private Integer usedNum=0;
	
	private Integer likeNum=0;
	
	private Integer viewNum=0;
	
	private Integer isStop=0;
	
	private Integer isStarProduct=0;
	
	private Integer starProductNum=0;
	
	@JsonIgnore
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getDataDictKey() {
		// TODO Auto-generated method stub
		return this.id.toString();
	}

	public Integer getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(Integer reviewNum) {
		this.reviewNum = reviewNum;
	}

	public Integer getTopicNum() {
		return topicNum;
	}

	public void setTopicNum(Integer topicNum) {
		this.topicNum = topicNum;
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

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

	public Integer getStarProductNum() {
		return starProductNum;
	}

	public void setStarProductNum(Integer starProductNum) {
		this.starProductNum = starProductNum;
	}

	public Float getGoodReviewRatio() {
		if(usedNum==null||usedNum==0||likeNum==null||likeNum==0){
			return 0f;
		}
		float ratio=((float)likeNum/usedNum);
		if(ratio>1){
			ratio=1;
		}
		return Float.parseFloat(String.format("%.2f", ratio));
	}

	public String getOfficialLink() {
		return officialLink;
	}

	public void setOfficialLink(String officialLink) {
		this.officialLink = officialLink;
	}

	public String getDetails() {
		if(details!=null){
			return details.replaceAll("<[^>]+>", "");
		}
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getOfficialLinkFlag() {
		return officialLinkFlag;
	}

	public void setOfficialLinkFlag(Integer officialLinkFlag) {
		this.officialLinkFlag = officialLinkFlag;
	}
	
	public List<String> getAliasList() {
		List<String> aliasList=new ArrayList<String>();
		if(StringUtils.isNotBlank(alias)){
			String[] aliasArray=alias.split(",|，");
			aliasList = new ArrayList<String>(Arrays.asList(aliasArray));
			int index=aliasList.indexOf(name);
			if(index>=0){
				aliasList.remove(index);
			}
		}
		return aliasList;
	}
	
	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
	
}
