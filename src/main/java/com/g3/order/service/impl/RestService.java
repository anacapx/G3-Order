package com.g3.order.service.impl;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.g3.order.util.User;
import com.google.gson.Gson;

public class RestService {
	

	public static User getUserById(Long id) throws IOException {
		
		Integer idInt = id.intValue();
		CloseableHttpClient client = HttpClientBuilder.create().build();
		CloseableHttpResponse response = client.execute(new HttpGet("http://localhost:8081/user/" + idInt.toString()));
		
		String bodyAsString = EntityUtils.toString(response.getEntity());
		User user = new Gson().fromJson(bodyAsString, User.class);

		return user;
    }
	
}
