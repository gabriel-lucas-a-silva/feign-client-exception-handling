package com.codemore.FeignClientExceptionHandling.client.exceptions;

import com.codemore.FeignClientExceptionHandling.exceptions.BillNotCreatedException;
import com.codemore.FeignClientExceptionHandling.exceptions.CompanyNotFoundException;
import com.codemore.FeignClientExceptionHandling.models.ExceptionMessage;
import com.codemore.FeignClientExceptionHandling.models.dto.response.ClaroErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
//@Component
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        ExceptionMessage message = null;

        try (InputStream body = response.body().asInputStream()) {
            message = mapper.readValue(body, ExceptionMessage.class);
        } catch (IOException e) {
            log.error("ERROR decode. Couldn't read response body. message: {}", e.getMessage());
            return new Exception(e.getMessage());
        }

        switch (response.status()) {
            case 404:
                return new CompanyNotFoundException(message.getMessage());
            case 422:
                ClaroErrorResponseDto claroErrorResponseDto = new ClaroErrorResponseDto(message.getCode(),
                        message.getMessage(), "");
                return new BillNotCreatedException(claroErrorResponseDto);
            default:
                return errorDecoder.decode(s, response);
        }
    }
}
