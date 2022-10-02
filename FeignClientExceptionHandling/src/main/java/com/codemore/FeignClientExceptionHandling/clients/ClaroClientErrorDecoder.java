package com.codemore.FeignClientExceptionHandling.clients;

import com.codemore.FeignClientExceptionHandling.exceptions.BillNotCreatedException;
import com.codemore.FeignClientExceptionHandling.exceptions.CompanyNotFoundException;
import com.codemore.FeignClientExceptionHandling.models.ExceptionMessage;
import com.codemore.FeignClientExceptionHandling.models.dto.response.ClaroErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ClaroClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder decoder = new Default();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        ExceptionMessage exceptionMessage = null;

        try (InputStream body = response.body().asInputStream()) {
            exceptionMessage = mapper.readValue(body, ExceptionMessage.class);
        } catch (IOException e) {
            log.error("ERROR decode. Couldn't read claro response body as ExceptionMessage. exception message: {}",
                    e.getMessage());
            return decoder.decode(s, response);
        }

        switch (response.status()) {
            case 404:
                return new CompanyNotFoundException(exceptionMessage.getMessage());
            case 422:
                ClaroErrorResponseDto errorResponse = new ClaroErrorResponseDto(exceptionMessage.getCode(),
                        exceptionMessage.getMessage(), "");
                return new BillNotCreatedException(errorResponse);
            default:
                return decoder.decode(s, response);
        }
    }
}
