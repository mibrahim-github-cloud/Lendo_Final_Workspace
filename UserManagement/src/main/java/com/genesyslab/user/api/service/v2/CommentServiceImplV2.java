package com.genesyslab.user.api.service.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.genesyslab.user.api.model.shared.v2.CommentDtoV2;

@Service
public class CommentServiceImplV2 implements CommentServiceV2{

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment environment;
	
	@Override
	public List<CommentDtoV2> getComments() {
		String getAllCommentsUrl = environment.getProperty("comments.url");
		ResponseEntity<List<CommentDtoV2>> response = restTemplate.exchange(getAllCommentsUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CommentDtoV2>>() {
				});
		List<CommentDtoV2> commandsList = response.getBody();
		return commandsList;
		
	}

	

}
