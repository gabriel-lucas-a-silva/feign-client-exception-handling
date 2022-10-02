package com.codemore.FeignClientExceptionHandling.client.claro;

import com.codemore.FeignClientExceptionHandling.configs.ClaroConfiguration;
import com.codemore.FeignClientExceptionHandling.exceptions.CompanyNotFoundException;
import com.codemore.FeignClientExceptionHandling.models.dto.request.BillConsultRequestDto;
import com.codemore.FeignClientExceptionHandling.models.dto.response.BillConsultResponseDto;
import feign.FeignException;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${claro.name}", url = "${claro.standard.url}", fallbackFactory = ClaroFallbackFactory.class)
public interface ClaroClientWithFactory {

    @PostMapping(value = "${claro.biller.consult.url}", consumes = MediaType.APPLICATION_JSON_VALUE)
    BillConsultResponseDto consultBill(@RequestBody BillConsultRequestDto billConsultRequestDto);

}

@Component
class ClaroFallbackFactory implements FallbackFactory<ClaroClientWithFactory> {

    @Override
    public ClaroClientWithFactory create(Throwable cause) {
        return new ClaroClientWithFactory() {
            @Override
            public BillConsultResponseDto consultBill(BillConsultRequestDto billConsultRequestDto) {
                if (cause instanceof FeignException.NotFound) {
                    throw new CompanyNotFoundException(cause.getMessage());
                }

                return null;
            }
        };
    }
}

//@Component
//class ClaroFallbackFactory implements FallbackFactory<ClaroClientFallbackFactory> {
//
//    @Override
//    public ClaroClientFallbackFactory create(Throwable cause) {
//        return new ClaroClientFallbackFactory();
//    }
//}

//class ClaroClientFallbackFactory implements ClaroClientWithFactory {
//    @Override
//    public BillConsultResponseDto consultBill(BillConsultRequestDto billConsultRequestDto) {
//        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
//    }
//}