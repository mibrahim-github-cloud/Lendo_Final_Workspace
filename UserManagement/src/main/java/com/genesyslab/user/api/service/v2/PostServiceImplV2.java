package com.genesyslab.user.api.service.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.genesyslab.user.api.exception.handling.NotFoundException;
import com.genesyslab.user.api.model.shared.v2.CommentDtoV2;
import com.genesyslab.user.api.model.shared.v2.PostDtoV2;

@Service
public class PostServiceImplV2 implements PostServiceV2 {

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

	@Override
	public PostDtoV2 getPost_Comments(String postId) {
		String getAllPostsUrl = environment.getProperty("posts.url");
		PostDtoV2 currentPostDtoV2 = null;
		ResponseEntity<List<PostDtoV2>> response = restTemplate.exchange(getAllPostsUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PostDtoV2>>() {
				});
		List<PostDtoV2> postDtoV2 = response.getBody();
		
		for(PostDtoV2 post : postDtoV2) 
			if(post.getId().equalsIgnoreCase(postId))
				currentPostDtoV2 = post;
		
		Optional<PostDtoV2> posts = Optional.ofNullable(currentPostDtoV2);

		if (!posts.isPresent())
			throw new NotFoundException("Resource Not Found...");
		
		// Retrieving Post_Comments
		CompletableFuture<List<CommentDtoV2>> post_Comments = CompletableFuture.supplyAsync(() -> {
			String getUserPostUrl = String.format(environment.getProperty("posts.comments.url"), postId);
			ResponseEntity<List<CommentDtoV2>> postResponse = restTemplate.exchange(getUserPostUrl, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CommentDtoV2>>() {
					});
			List<CommentDtoV2> postComments = postResponse.getBody();

			return postComments;
		}).handle((msg, ex) -> {
			if (ex != null) {
				return new ArrayList<>();
			} else {
				return msg;
			}
		});
		;
		try {
			currentPostDtoV2.setPost_comments(post_Comments.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return currentPostDtoV2;
	}


}
