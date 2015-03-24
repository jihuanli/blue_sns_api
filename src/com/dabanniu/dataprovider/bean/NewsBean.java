package com.dabanniu.dataprovider.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsBean {
	
	@JsonProperty("news_id")
	private Integer news_id;
	
	private String content;
	
	private String image_path;
	
	private Integer create_time;
	
	private Integer factory_id;
	
	private Integer author_id;
	
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

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public Integer getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Integer create_time) {
		this.create_time = create_time;
	}

	public Integer getFactory_id() {
		return factory_id;
	}

	public void setFactory_id(Integer factory_id) {
		this.factory_id = factory_id;
	}

	public Integer getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}

	public Integer getLike_num() {
		return like_num;
	}

	public void setLike_num(Integer like_num) {
		this.like_num = like_num;
	}
}
