package com.codemore.FeignClientExceptionHandling.controllers;

import com.codemore.FeignClientExceptionHandling.models.dto.response.BillConsultResponseDto;
import com.codemore.FeignClientExceptionHandling.services.BillerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/biller")
public class BillerController {

    @Autowired
    private BillerService billerService;

    @GetMapping("/consult/{billerId}")
    public ResponseEntity<BillConsultResponseDto> consultBill(@PathVariable final String billerId) {
        log.info("INIT consultBiller. biller id: {}", billerId);
        BillConsultResponseDto responseDto = billerService.consultBill(billerId);

        log.info("SUCCESS consultBiller. response: {}", responseDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/consult/factory/{billerId}")
    public ResponseEntity<BillConsultResponseDto> consultBillWithFactory(@PathVariable final String billerId) {
        log.info("INIT consultBiller. biller id: {}", billerId);
        BillConsultResponseDto responseDto = billerService.consultBillWithFactory(billerId);

        log.info("SUCCESS consultBiller. response: {}", responseDto);
        return ResponseEntity.ok(responseDto);
    }
}
