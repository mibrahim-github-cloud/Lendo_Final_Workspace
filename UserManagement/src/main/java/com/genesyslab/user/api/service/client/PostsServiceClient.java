package com.genesyslab.user.api.service.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.genesyslab.user.api.model.shared.v2.PostDtoV2;


@FeignClient(value="https://gorest.co.in/public/v2", fallback = PostServiceFallback.class)
public interface PostsServiceClient {

	@GetMapping("/users/{id}/posts")
	public List<PostDtoV2> getUser_Posts(@PathVariable String id);
}

@Component
class PostServiceFallback implements PostsServiceClient {


	@Override
	public List<PostDtoV2> getUser_Posts(String id) {
		return new ArrayList<>();
	}
}
