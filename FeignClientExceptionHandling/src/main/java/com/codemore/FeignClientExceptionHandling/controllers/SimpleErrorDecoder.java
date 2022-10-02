package com.codemore.FeignClientExceptionHandling.controllers;

import com.codemore.FeignClientExceptionHandling.exceptions.CompanyNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class SimpleErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        if (response.status() == 404) {
            return new CompanyNotFoundException("Couldn't find company");
        }

        return errorDecoder.decode(s, response);
    }
}