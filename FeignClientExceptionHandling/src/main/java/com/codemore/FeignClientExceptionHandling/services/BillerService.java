package com.codemore.FeignClientExceptionHandling.services;

import com.codemore.FeignClientExceptionHandling.clients.ClaroClient;
import com.codemore.FeignClientExceptionHandling.models.dto.request.BillConsultRequestDto;
import com.codemore.FeignClientExceptionHandling.models.dto.response.BillConsultResponseDto;
import com.codemore.FeignClientExceptionHandling.models.dto.response.BillerResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.codemore.FeignClientExceptionHandling.helpers.BillerHelpers.createBillRequestDto;
import static com.codemore.FeignClientExceptionHandling.helpers.LogHelpers.getJsonOf;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BillerService {
    private ClaroClient claroClient;

    public BillerResponseDto consultBill(final String billerId) {
        BillConsultRequestDto requestDto = createBillRequestDto(billerId);

        log.info("INIT claro client consult bill call. request: {}", getJsonOf(requestDto));
        BillConsultResponseDto responseDto = claroClient.consultBill(requestDto);

        log.info("SUCCESS calling claro client consult bill. response: {}", getJsonOf(responseDto));
        return convertToBillerResponseDto(responseDto);
    }

    private BillerResponseDto convertToBillerResponseDto(BillConsultResponseDto responseDto) {
        return BillerResponseDto.builder()
                .billerId(responseDto.getBillerId())
                .accountNumber(responseDto.getAccountNumber())
                .currency(responseDto.getBillAmountCurrency())
                .billerAmount(responseDto.getBillAmount())
                .build();
    }
}
