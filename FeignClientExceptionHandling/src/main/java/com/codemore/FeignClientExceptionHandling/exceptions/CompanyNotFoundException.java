package com.codemore.FeignClientExceptionHandling.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(final String message) {
        super(message);
    }
}
