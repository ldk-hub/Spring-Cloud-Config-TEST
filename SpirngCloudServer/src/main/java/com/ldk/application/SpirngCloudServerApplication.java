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
	    @LoadBalanced //리본으로 트래픽 라운드로빈으로 분배 1번클라 2번클라 테스트해야됨.
	    //1은 h2db connect 2는 postgresql로 설정
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	 	@Autowired
	     RestTemplate restTemplate;
	 	
	 	
	@RequestMapping(value="/")
	public String hoem() {
		return "Eureka Client Application Test1";
	}*/
