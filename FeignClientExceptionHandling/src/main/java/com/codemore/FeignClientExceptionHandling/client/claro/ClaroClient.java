package com.codemore.FeignClientExceptionHandling.client.claro;

import com.codemore.FeignClientExceptionHandling.models.dto.request.BillConsultRequestDto;
import com.codemore.FeignClientExceptionHandling.models.dto.response.BillConsultResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name =  "clarofactory" , url = "${claro.standard.url}")
public interface ClaroClient {

    @PostMapping(value = "${claro.biller.consult.url}", consumes = MediaType.APPLICATION_JSON_VALUE)
    BillConsultResponseDto consultBill(@RequestBody BillConsultRequestDto billConsultRequestDto);
}