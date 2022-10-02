package com.codemore.FeignClientExceptionHandling.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LogHelpers {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Object getJsonOf(Object objectToTransform) {
        var object = objectToTransform;

        try {
            object = mapper.writeValueAsString(objectToTransform);
        } catch (JsonProcessingException e) {
            log.debug("ERROR getJsonOf. exception: {}", e.getMessage());
        }

        return object;
    }
}
