package com.bioskop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker

//@EnableAutoConfiguration
//@SpringCloudApplication
//@EnableFeignClients




@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class BioskopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BioskopApplication.class, args);
	}
}
