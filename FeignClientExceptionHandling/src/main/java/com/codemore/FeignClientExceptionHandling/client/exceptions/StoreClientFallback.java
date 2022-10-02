package com.codemore.FeignClientExceptionHandling.client.exceptions;

import com.codemore.FeignClientExceptionHandling.exceptions.CompanyNotFoundException;
import com.codemore.FeignClientExceptionHandling.models.Store;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreClientFallback implements StoreClient {
    @Override
    public List<Store> getStores() {
//        throw new NoFallbackAvailableException("Boom!", new CompanyNotFoundException(""));
        return List.of();
    }
}
