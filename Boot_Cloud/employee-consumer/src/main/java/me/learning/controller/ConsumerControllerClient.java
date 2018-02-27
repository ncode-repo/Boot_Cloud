package me.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ConsumerControllerClient {
	
//	@Autowired
//	DiscoveryClient discoveryClient;
	@Autowired
	LoadBalancerClient loadBalancerClient;
	public void getEmployee() {
//		List<ServiceInstance> instances = discoveryClient.getInstances("employee-producer");
		
//		ServiceInstance serviceInstance = instances.get(0);
		ServiceInstance serviceInstance = loadBalancerClient.choose("employee-producer");
		System.out.println(serviceInstance.getUri().toString());
		//String baseUrl = "http://localhost:8080/employee";
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl +="/employee";
		
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<String> responseEntity= restTemplate.exchange(baseUrl, HttpMethod.GET, getHttpEntity(), String.class);
			System.out.println(responseEntity.getBody());
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	private HttpEntity<?> getHttpEntity(){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
		return httpEntity;
	}
}
