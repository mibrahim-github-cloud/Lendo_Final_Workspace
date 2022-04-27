package com.genesyslab.user.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesyslab.user.api.model.shared.v2.UserDtoV2;
import com.genesyslab.user.api.model.v2.LoginRequestModelV2;
import com.genesyslab.user.api.service.v2.UserServiceV2;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private UserServiceV2 userService;

	public AuthenticationFilter(UserServiceV2 usersService, AuthenticationManager authenticationManager) {
		this.userService = usersService;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginRequestModelV2 cred = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModelV2.class);

//			return getAuthenticationManager().authenticate(
//					new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword(), new ArrayList<>()));
			
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(cred.getUserId(), cred.getEmail(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String userName = ((User) authResult.getPrincipal()).getUsername();

		UserDtoV2 userDto = userService.loadUserDetailsByEmail(userName);
		
		//userService.updateLastLogin(userName);

		String token = Jwts.builder().setSubject(userDto.getId())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong("84000000")))
				.signWith(SignatureAlgorithm.HS512, "jkmlijorknlmmpkuhjnklmyg").compact();

		response.addHeader("token", token);
		response.addHeader("userid", userDto.getId());

	}
	

}
