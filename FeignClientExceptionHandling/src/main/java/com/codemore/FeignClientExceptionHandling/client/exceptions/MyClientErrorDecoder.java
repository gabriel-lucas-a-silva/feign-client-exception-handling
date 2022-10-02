package com.codemore.FeignClientExceptionHandling.client.exceptions;

import com.codemore.FeignClientExceptionHandling.exceptions.BillNotCreatedException;
import com.codemore.FeignClientExceptionHandling.models.dto.response.ClaroErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
class MyClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        MyClientErrorResponse myClientErrorResponse = null;

        try (InputStream body = response.body().asInputStream()) {
            myClientErrorResponse = mapper.readValue(body, MyClientErrorResponse.class);
        } catch (IOException e) {
            log.error("ERROR reading response body as MyClientErrorResponse. ex message: {}", e.getMessage());
            return errorDecoder.decode(s, response);
        }

        switch (response.status()) {
            case 422:
                ClaroErrorResponseDto errorResponseDto = new ClaroErrorResponseDto(myClientErrorResponse.code,
                        myClientErrorResponse.message, "");
                return new BillNotCreatedException(errorResponseDto);
            default:
                return errorDecoder.decode(s, response);
        }
    }

    @Data
    static class MyClientErrorResponse {
        private String code;
        private String message;
        private Integer id;
    }
}
