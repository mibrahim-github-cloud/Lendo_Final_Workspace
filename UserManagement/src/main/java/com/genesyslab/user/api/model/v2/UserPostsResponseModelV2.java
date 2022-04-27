package com.genesyslab.user.api.model.v2;

import java.util.List;

public class UserPostsResponseModelV2 {
	
	private String id;
	private String name;
	private String email;
	private String gender;
	private String status;
	private List<PostResponseModelV2> user_posts;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<PostResponseModelV2> getUser_posts() {
		return user_posts;
	}
	public void setUser_posts(List<PostResponseModelV2> user_posts) {
		this.user_posts = user_posts;
	}
	
	
	
		
}
