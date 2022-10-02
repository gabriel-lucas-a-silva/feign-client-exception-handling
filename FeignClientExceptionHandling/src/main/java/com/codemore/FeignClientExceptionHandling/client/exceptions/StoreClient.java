package com.codemore.FeignClientExceptionHandling.client.exceptions;

import com.codemore.FeignClientExceptionHandling.configs.FooConfiguration;
import com.codemore.FeignClientExceptionHandling.exceptions.CompanyNotFoundException;
import com.codemore.FeignClientExceptionHandling.models.Store;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@FeignClient(
        name = "stores",
        url = "http://localhost:3334",
        configuration = FooConfiguration.class
//        fallback = StoreClientFallback.class
//        fallbackFactory = StoreClientFallbackFactory.class
)
public interface StoreClient {
    @GetMapping(value = "/stores", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Store> getStores();
}

@Component
class StoreClientFallbackFactory implements FallbackFactory<FallbackWithFactory> {
    @Override
    public FallbackWithFactory create(Throwable cause) {
        if (cause instanceof FeignException.NotFound) {
            throw new CompanyNotFoundException("Couldn't find company");
        }

        return new FallbackWithFactory();
    }
}

class FallbackWithFactory implements StoreClient {
    @Override
    public List<Store> getStores() {
        return new ArrayList<>();
    }
}