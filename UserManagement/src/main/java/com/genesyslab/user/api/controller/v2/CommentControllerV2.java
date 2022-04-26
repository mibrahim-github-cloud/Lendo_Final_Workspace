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

import com.genesyslab.user.api.model.shared.v2.CommentDtoV2;
import com.genesyslab.user.api.model.v2.CommentResponseModelV2;
import com.genesyslab.user.api.service.v2.CommentServiceV2;

@RestController
@RequestMapping("/v2/comments")
public class CommentControllerV2 {

	@Autowired
	CommentServiceV2 commentService;
	
	
	@GetMapping
	public ResponseEntity<List<CommentResponseModelV2>> getComments() {

		List<CommentDtoV2> commentDtos = commentService.getComments();
		ModelMapper modelMapper = new ModelMapper();

		List<CommentResponseModelV2> returnValue = commentDtos.stream()
				.map(commentDto -> modelMapper.map(commentDto, CommentResponseModelV2.class)).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(returnValue);

	}
}
