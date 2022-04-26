package com.genesyslab.user.api.controller.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genesyslab.user.api.model.shared.v2.PostDtoV2;
import com.genesyslab.user.api.model.v2.PostResponseModelV2;
import com.genesyslab.user.api.service.v2.PostServiceV2;

@RestController
@RequestMapping("/v2/posts")
public class PostControllerV2 {
	
	@Autowired
	PostServiceV2 postService;
		
	@GetMapping
	public ResponseEntity<List<PostResponseModelV2>> getPosts() {

		List<PostDtoV2> postDtos = postService.getPosts();
		ModelMapper modelMapper = new ModelMapper();

		List<PostResponseModelV2> returnValue = postDtos.stream()
				.map(postDto -> modelMapper.map(postDto, PostResponseModelV2.class)).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(returnValue);

	}
	
}
