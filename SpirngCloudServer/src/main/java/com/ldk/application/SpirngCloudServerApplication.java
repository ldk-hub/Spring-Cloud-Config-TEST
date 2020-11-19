package com.ldk.application;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RestController
public class SpirngCloudServerApplication {
	
@Autowired
//private DiscoveryClient discoveryClient;
private LoadBalancerClient loadBalancerClient;

	@GetMapping("/eureka/client")
	public void getEmployee() throws RestClientException, IOException {
    /*
    List<ServiceInstance> instances=discoveryClient.getInstances("employee-producer");
    ServiceInstance serviceInstance=instances.get(0);
    String baseUrl=serviceInstance.getUri().toString();
    */
	    ServiceInstance serviceInstance = loadBalancerClient.choose("employee-producer");
	    System.out.println("#####service-instance-uri:"+serviceInstance.getUri());
	    String baseUrl = serviceInstance.getUri().toString();
	
	    baseUrl=baseUrl+"/employee";
	
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response=null;
	    try{
	        response=restTemplate.exchange(baseUrl,
	                HttpMethod.GET, getHeaders(),String.class);
	    }catch (Exception ex)
	    {
	        System.out.println(ex);
	    }
	    System.out.println(response.getBody());
	}

		private static HttpEntity<?> getHeaders() throws IOException {
		    HttpHeaders headers = new HttpHeaders();
		    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		    return new HttpEntity<>(headers);
		}
}
/*@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpirngCloudServerApplication {
	 

	
	public static void main(String[] args) {
		SpringApplication.run(SpirngCloudServerApplication.class, args);
	}

	 	@Bean
	    @LoadBalanced //�������� Ʈ���� ����κ����� �й� 1��Ŭ�� 2��Ŭ�� �׽�Ʈ�ؾߵ�.
	    //1�� h2db connect 2�� postgresql�� ����
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	 	@Autowired
	     RestTemplate restTemplate;
	 	
	 	
	@RequestMapping(value="/")
	public String hoem() {
		return "Eureka Client Application Test1";
	}*/
