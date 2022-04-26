package com.genesyslab.user.api.service.v2;

import java.util.List;

import com.genesyslab.user.api.model.shared.v2.CommentDtoV2;


public interface CommentServiceV2 {
	
	List<CommentDtoV2> getComments();
		
}
