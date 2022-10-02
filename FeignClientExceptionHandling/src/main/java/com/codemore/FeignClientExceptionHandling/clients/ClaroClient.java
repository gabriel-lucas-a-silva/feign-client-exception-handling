package com.codemore.FeignClientExceptionHandling.clients;

import com.codemore.FeignClientExceptionHandling.models.dto.request.BillConsultRequestDto;
import com.codemore.FeignClientExceptionHandling.models.dto.response.BillConsultResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "claro-client", url = "http://localhost:3333")
public interface ClaroClient {
    @PostMapping(value = "/biller/consult", consumes = MediaType.APPLICATION_JSON_VALUE)
    BillConsultResponseDto consultBill(@RequestBody BillConsultRequestDto requestDto);
}
