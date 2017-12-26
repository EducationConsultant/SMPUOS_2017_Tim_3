package com.rezervacija;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableAutoConfiguration
//@EnableDiscoveryClient
//@SpringBootApplication
@SpringCloudApplication
@EnableFeignClients
public class RezervacijaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RezervacijaApplication.class, args);
	}
}
