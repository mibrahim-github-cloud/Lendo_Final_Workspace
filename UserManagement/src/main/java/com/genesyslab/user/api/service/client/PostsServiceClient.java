package com.genesyslab.user.api.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.genesyslab.user.api.model.shared.v2.PostDtoV2;


@FeignClient(url="https://gorest.co.in/public/v2")
public interface PostsServiceClient {

	@GetMapping("/users/{id}/posts")
	public List<PostDtoV2> getUser_Posts(@PathVariable String id);
}
