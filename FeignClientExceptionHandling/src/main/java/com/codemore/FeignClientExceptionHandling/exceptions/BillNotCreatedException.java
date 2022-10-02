package com.codemore.FeignClientExceptionHandling.exceptions;

import com.codemore.FeignClientExceptionHandling.models.dto.response.ClaroErrorResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BillNotCreatedException extends RuntimeException {
    private ClaroErrorResponseDto claroErrorResponseDto;

    public BillNotCreatedException(ClaroErrorResponseDto claroErrorResponseDto) {
        this.claroErrorResponseDto = claroErrorResponseDto;
    }
}
