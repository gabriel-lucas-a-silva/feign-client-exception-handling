package com.codemore.FeignClientExceptionHandling.controllers;

import com.codemore.FeignClientExceptionHandling.models.dto.response.BillerResponseDto;
import com.codemore.FeignClientExceptionHandling.services.BillerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.codemore.FeignClientExceptionHandling.helpers.LogHelpers.getJsonOf;

@Slf4j
@RestController
@RequestMapping("/biller")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BillerController {
    private BillerService billerService;

    @GetMapping(value = "/consult/{billerId}")
    public ResponseEntity<?> consultBill(@PathVariable String billerId) {
        log.info("INIT consulBill. biller id: [{}]", billerId);
        BillerResponseDto response = billerService.consultBill(billerId);

        log.info("SUCCESS consultBill. response: {}", getJsonOf(response));
        return ResponseEntity.ok(response);
    }

}
