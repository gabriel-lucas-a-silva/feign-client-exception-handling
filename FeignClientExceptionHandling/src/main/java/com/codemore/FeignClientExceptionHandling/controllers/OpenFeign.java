package com.codemore.FeignClientExceptionHandling.controllers;

import com.codemore.FeignClientExceptionHandling.client.exceptions.StoreClient;
import com.codemore.FeignClientExceptionHandling.exceptions.BillNotCreatedException;
import com.codemore.FeignClientExceptionHandling.models.dto.response.ClaroErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

@RestController
class StoreController {
    @Autowired
    private StoreClient storeClient;

    @GetMapping("/store")
    public Object getStore() {
        return storeClient.getStores();
    }
}

@FeignClient(
        name = "myclient",
        url = "http://localhost:3333"
)
interface MyClient {
    @PostMapping("/biller/consult")
    Object getSmth();
}


@RestController
class MyController {
    @Autowired
    private MyClient myClient;

    @GetMapping("/gabe/test")
    public Object testFeign() {
        return myClient.getSmth();
    }
}