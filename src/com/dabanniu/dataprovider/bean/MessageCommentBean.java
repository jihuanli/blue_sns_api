package com.dabanniu.dataprovider.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageCommentBean {
	
	@JsonProperty("news_id")
	private Integer news_id;
	
	private String content;
	
	private String image_url;
	
	private Long create_time;
	
	private Integer factory_id;
	
	private Integer user_id;
	
	private Integer like_num;

	public Integer getNews_id() {
		return news_id;
	}

	public void setNews_id(Integer news_id) {
		this.news_id = news_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Integer getFactory_id() {
		return factory_id;
	}

	public void setFactory_id(Integer factory_id) {
		this.factory_id = factory_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setAuthor_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getLike_num() {
		return like_num;
	}

	public void setLike_num(Integer like_num) {
		this.like_num = like_num;
	}
}
