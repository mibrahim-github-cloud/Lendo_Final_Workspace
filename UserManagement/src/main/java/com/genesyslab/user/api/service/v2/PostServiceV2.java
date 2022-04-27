package com.genesyslab.user.api.service.v2;

import java.util.List;

import com.genesyslab.user.api.model.shared.v2.PostDtoV2;


public interface PostServiceV2 {

	List<PostDtoV2> getPosts();
	
	PostDtoV2 getPost_Comments(String postId);
	
}
