package com.genesyslab.user.api.service.v2;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.genesyslab.user.api.model.shared.v2.UserDtoV2;

public interface UserServiceV2 extends UserDetailsService{

	
	List<UserDtoV2> getUsers();
	
	UserDtoV2 loadUserDetailsByEmail(String email);
	
	UserDtoV2 getUser_Posts(String email);
	
}
