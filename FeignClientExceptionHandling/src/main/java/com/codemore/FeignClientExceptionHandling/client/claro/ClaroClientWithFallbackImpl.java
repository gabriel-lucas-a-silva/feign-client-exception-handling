package com.codemore.FeignClientExceptionHandling.client.claro;

import com.codemore.FeignClientExceptionHandling.exceptions.CompanyNotFoundException;
import com.codemore.FeignClientExceptionHandling.models.dto.request.BillConsultRequestDto;
import com.codemore.FeignClientExceptionHandling.models.dto.response.BillConsultResponseDto;
import org.springframework.stereotype.Component;

@Component
class ClaroClientWithFallbackImpl implements ClaroClientWithFactory {
    @Override
    public BillConsultResponseDto consultBill(BillConsultRequestDto billConsultRequestDto) {
        throw new CompanyNotFoundException("");
    }
}
