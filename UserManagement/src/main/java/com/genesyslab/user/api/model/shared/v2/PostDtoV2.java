package com.genesyslab.user.api.model.shared.v2;

import java.io.Serializable;
import java.util.List;

public class PostDtoV2 implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7140354119998188100L;
	private String id;
	private String user_id;
	private String title;
	private String body;
	private List<CommentDtoV2> post_comments;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<CommentDtoV2> getPost_comments() {
		return post_comments;
	}
	public void setPost_comments(List<CommentDtoV2> post_comments) {
		this.post_comments = post_comments;
	}
	
}
