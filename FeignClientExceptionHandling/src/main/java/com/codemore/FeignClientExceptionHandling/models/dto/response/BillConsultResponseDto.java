package com.codemore.FeignClientExceptionHandling.models.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BillConsultResponseDto {
    private BigDecimal accountNumber;
    private Integer billerId;
    private BigDecimal billAmount;
    private String billAmountCurrency;
    private BigDecimal paymentTransactionFee;
    private BigDecimal paymentTotalUsd;
}
