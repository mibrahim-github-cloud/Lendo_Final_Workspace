package com.genesyslab.user.api.model.shared.v2;

import java.io.Serializable;
import java.util.List;

public class UserDtoV2 implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -856353660481200973L;
	
	private String id;
	private String name;
	private String email;
	private String gender;
	private String status;
	private List<PostDtoV2> userPosts;
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
	public List<PostDtoV2> getUserPosts() {
		return userPosts;
	}
	public void setUserPosts(List<PostDtoV2> userPosts) {
		this.userPosts = userPosts;
	}
	
	

}
