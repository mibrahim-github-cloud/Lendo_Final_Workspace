package com.genesyslab.user.api.service.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.genesyslab.user.api.model.shared.v2.PostDtoV2;

@Service
public class PostServiceImplV2 implements PostServiceV2{
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment environment;
	

	@Override
	public List<PostDtoV2> getPosts() {
		String getAllPostsUrl = environment.getProperty("posts.url");
		ResponseEntity<List<PostDtoV2>> response = restTemplate.exchange(getAllPostsUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PostDtoV2>>() {
				});
		List<PostDtoV2> postsList = response.getBody();
		return postsList;

		
	}


}
