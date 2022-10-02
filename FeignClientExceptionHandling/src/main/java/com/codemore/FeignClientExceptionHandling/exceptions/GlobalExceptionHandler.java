package com.codemore.FeignClientExceptionHandling.exceptions;

import com.codemore.FeignClientExceptionHandling.models.dto.response.ClaroErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ClaroErrorResponseDto> handleCompanyNotFoundException(CompanyNotFoundException e) {
        log.error("ERROR handleCompanyNotFoundException. message: {}", e.getMessage());
        ClaroErrorResponseDto response = new ClaroErrorResponseDto("1", e.getMessage(), "");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ClaroErrorResponseDto> handleBillNotCreatedException(BillNotCreatedException e) {
        log.error("ERROR handleBillNotCreatedException. message: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getClaroErrorResponseDto());
    }
}
