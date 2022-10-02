package com.codemore.FeignClientExceptionHandling.services;

import com.codemore.FeignClientExceptionHandling.client.claro.ClaroClient;
import com.codemore.FeignClientExceptionHandling.client.claro.ClaroClientWithFactory;
import com.codemore.FeignClientExceptionHandling.models.dto.request.BillConsultRequestDto;
import com.codemore.FeignClientExceptionHandling.models.dto.response.BillConsultResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.codemore.FeignClientExceptionHandling.helpers.BillerHelpers.createBillRequestDto;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BillerService {
    private ClaroClient claroClient;
    private ClaroClientWithFactory claroClientWithFactory;

    public BillConsultResponseDto consultBill(String billerId) {
        BillConsultRequestDto requestDto = createBillRequestDto(billerId);
        return claroClient.consultBill(requestDto);
    }

    public BillConsultResponseDto consultBillWithFactory(String billerId) {
        BillConsultRequestDto requestDto = createBillRequestDto(billerId);
        return claroClientWithFactory.consultBill(requestDto);
    }
}
