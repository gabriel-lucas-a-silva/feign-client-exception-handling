package com.codemore.FeignClientExceptionHandling.helpers;

import com.codemore.FeignClientExceptionHandling.models.dto.request.BillConsultRequestDto;

public class BillerHelpers {
    public static BillConsultRequestDto createBillRequestDto(String billerId) {
        return BillConsultRequestDto.builder()
                .billerId(billerId)
                .build();
    }
}
