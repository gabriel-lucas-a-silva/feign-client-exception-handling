package com.codemore.FeignClientExceptionHandling.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionMessage {
    private String id;
    private String code;
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}