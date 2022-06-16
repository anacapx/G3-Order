package com.g3.order.service.impl;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.lang.RuntimeException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.g3.order.model.ResponseBody;
import com.g3.order.model.User;
import com.google.gson.Gson;

public class RestService {

	public static User getUserById(Long userId, HttpServletRequest servletRequest) throws IOException {

		Integer userIdInt = userId.intValue();
		String token = servletRequest.getHeader("Authorization");
		token = token.replace("Bearer", "").trim();
//				SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
		HttpClient client = HttpClients.custom().build();
		String path = (System.getenv("API_USER_URL") + userIdInt.toString()).replace(" ", "");
		HttpUriRequest request = RequestBuilder.get().setUri(path)
				.setHeader("Authorization", "Bearer " + token).build();
		HttpResponse response = client.execute(request);

		String bodyAsString = EntityUtils.toString(response.getEntity());
		User user = new Gson().fromJson(bodyAsString, User.class);
		return user;
	}

	public static UsernamePasswordAuthenticationToken validateToken(String token) throws IOException {

		try {
			HttpClient client = HttpClients.custom().build();
			HttpUriRequest request = RequestBuilder.get().setUri(System.getenv("API_AUTH_URL"))
					.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
			HttpResponse response = client.execute(request);
			
			String bodyAsString = EntityUtils.toString(response.getEntity());
			ResponseBody responseBody = new Gson().fromJson(bodyAsString, ResponseBody.class);
			
//		UsernamePasswordAuthenticationToken constructor MUST get the authorities, otherwise it'll set authenticated = false.
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					responseBody.getPrincipal(), 
					responseBody.getCredentials(), 
					responseBody.getAuthorities());
			
			return usernamePasswordAuthenticationToken;
			
		} catch (Exception e) {
			throw new RuntimeException("Unable to get proper response from Auth API.");
		}
	}

}
