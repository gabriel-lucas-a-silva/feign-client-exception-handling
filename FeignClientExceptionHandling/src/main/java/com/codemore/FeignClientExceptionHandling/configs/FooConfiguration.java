package com.codemore.FeignClientExceptionHandling.configs;

import feign.Target;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class FooConfiguration {
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }

    @Bean
    public CircuitBreakerNameResolver circuitBreakerNameResolver() {
        return (String feignClientName, Target<?> target, Method method) -> feignClientName + "_" + method.getName();
    }

}

