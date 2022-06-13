package com.g3.order.service.impl;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.g3.order.model.ResponseBody;
import com.g3.order.model.User;
import com.google.gson.Gson;

public class RestService {

	public static User getUserById(Long id) throws IOException {

		Integer idInt = id.intValue();
		CloseableHttpClient client = HttpClientBuilder.create().build();
		CloseableHttpResponse response = client.execute(new HttpGet(System.getenv("API_USER_URL") + idInt.toString()));

		String bodyAsString = EntityUtils.toString(response.getEntity());
		User user = new Gson().fromJson(bodyAsString, User.class);

		return user;
	}

	public static UsernamePasswordAuthenticationToken validateToken(String token) throws IOException {

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
	}

}
