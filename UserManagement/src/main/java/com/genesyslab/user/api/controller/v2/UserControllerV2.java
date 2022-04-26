package com.genesyslab.user.api.controller.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.genesyslab.user.api.model.shared.v2.UserDtoV2;
import com.genesyslab.user.api.model.v2.UserResponseModelV2;
import com.genesyslab.user.api.service.v2.UserServiceV2;

@RestController
@RequestMapping("/v2/users")
public class UserControllerV2 {

	@Autowired
	UserServiceV2 userServiceV2;

	@GetMapping
	public ResponseEntity<List<UserResponseModelV2>> getUsers() {

		List<UserDtoV2> userDtoV2 = userServiceV2.getUsers();

		ModelMapper modelMapper = new ModelMapper();

		List<UserResponseModelV2> returnValue = userDtoV2.stream()
				.map(userDto -> modelMapper.map(userDto, UserResponseModelV2.class)).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(returnValue);

	}
	
	@GetMapping("/user_posts/{userId}")
	public ResponseEntity<UserResponseModelV2> getUser_Posts(@PathVariable("userId") String userId) {

		UserDtoV2 userDtoV2 = userServiceV2.getUser_Posts(userId);

		UserResponseModelV2 returnValue = new ModelMapper().map(userDtoV2, UserResponseModelV2.class);

		return ResponseEntity.status(HttpStatus.OK).body(returnValue);

	}

}
