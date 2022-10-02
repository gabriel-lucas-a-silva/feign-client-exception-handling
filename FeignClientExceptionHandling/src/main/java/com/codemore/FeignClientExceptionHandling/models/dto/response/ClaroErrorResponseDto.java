package com.codemore.FeignClientExceptionHandling.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaroErrorResponseDto {
    private String code;
    private String message;
    private String messageCode;
}
