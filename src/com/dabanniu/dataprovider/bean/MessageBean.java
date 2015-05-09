package com.dabanniu.dataprovider.bean;

public class MessageBean {
	
	private Integer message_id;
	
	private String content;
	
	private String image_url;
	
	private Long create_time;
	
	private Integer user_id;
	
	private Integer like_cnt;
	
	private Integer unlike_cnt;
	
	private Integer reply_num;
	
	private Double longitude;
	
	private Double latitude;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Integer getReply_num() {
		return reply_num;
	}

	public void setReply_num(Integer reply_num) {
		this.reply_num = reply_num;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(Integer like_cnt) {
		this.like_cnt = like_cnt;
	}

	public Integer getUnlike_cnt() {
		return unlike_cnt;
	}

	public void setUnlike_cnt(Integer unlike_cnt) {
		this.unlike_cnt = unlike_cnt;
	}

}
