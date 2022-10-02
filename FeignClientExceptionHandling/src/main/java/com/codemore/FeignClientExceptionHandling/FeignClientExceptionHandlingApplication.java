package com.codemore.FeignClientExceptionHandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignClientExceptionHandlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientExceptionHandlingApplication.class, args);
	}

}
