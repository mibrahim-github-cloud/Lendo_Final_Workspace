package com.genesyslab.user.api.service.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.genesyslab.user.api.exception.handling.NotFoundException;
import com.genesyslab.user.api.model.shared.v2.PostDtoV2;
import com.genesyslab.user.api.model.shared.v2.UserDtoV2;
import com.genesyslab.user.api.model.v2.PostResponseModelV2;
import com.genesyslab.user.api.model.v2.UserResponseModelV2;
import com.genesyslab.user.api.service.client.PostsServiceClient;

@Service
public class UserServiceImplV2 implements UserServiceV2 {

	private RestTemplate restTemplate;
	private Environment environment;
	private PostsServiceClient postsServiceClient;

	@Autowired
	public UserServiceImplV2(RestTemplate restTemplate, Environment environment,
			PostsServiceClient postsServiceClient) {
		super();
		this.restTemplate = restTemplate;
		this.environment = environment;
		this.postsServiceClient = postsServiceClient;
	}

	@Override
	public List<UserDtoV2> getUsers() {
		String getAllUsersUrl = environment.getProperty("users.url");
		ResponseEntity<List<UserDtoV2>> response = restTemplate.exchange(getAllUsersUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<UserDtoV2>>() {
				});
		List<UserDtoV2> usersList = response.getBody();
		return usersList;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			String getUserUrl = String.format(environment.getProperty("user.detail.url"), username);
			ResponseEntity<UserResponseModelV2> response = restTemplate.exchange(getUserUrl, HttpMethod.GET, null,
					new ParameterizedTypeReference<UserResponseModelV2>() {
					});
			UserResponseModelV2 userResponse = response.getBody();

			Optional<UserResponseModelV2> user = Optional.ofNullable(userResponse);

			if (!user.isPresent())
				throw new UsernameNotFoundException(username);

			// return new
			// User(user.get().getEmail(),user.get().getEncryptedPassword(),true,true,true,true,
			// new ArrayList<>());
			return new User(user.get().getId(), user.get().getId(), true, true, true, true, new ArrayList<>());

		} catch (Exception e) {
			throw new UsernameNotFoundException(username);
		}

	}

	@Override
	public UserDtoV2 loadUserDetailsByEmail(String email) {
		String getUserUrl = String.format(environment.getProperty("user.detail.url"), email);
		ResponseEntity<UserDtoV2> response = restTemplate.exchange(getUserUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<UserDtoV2>() {
				});
		UserDtoV2 userResponse = response.getBody();

		Optional<UserDtoV2> user = Optional.ofNullable(userResponse);

		if (!user.isPresent())
			throw new UsernameNotFoundException(email);
		return user.get();

		
	}

	@Override
	public UserDtoV2 getUser_Posts(String email) {
		String getUserUrl = String.format(environment.getProperty("user.detail.url"), email);
		ResponseEntity<UserDtoV2> response = restTemplate.exchange(getUserUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<UserDtoV2>() {
				});
		UserDtoV2 userResponse = response.getBody();

		Optional<UserDtoV2> user = Optional.ofNullable(userResponse);

		if (!user.isPresent())
			throw new NotFoundException("Resource Not Found...");
		
		UserDtoV2 userDtoV2 = user.get();
		List<PostDtoV2> user_Posts = postsServiceClient.getUser_Posts(email);
		userDtoV2.setUserPosts(user_Posts);
		
		
		return userDtoV2;
	}

}
