package com.eteration.simplebanking.exceptions;

import com.eteration.simplebanking.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BankingExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = { InsufficientBalanceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(InsufficientBalanceException insufficientBalanceException) {
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(insufficientBalanceException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = { AccountNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(AccountNotFoundException accountNotFoundException) {
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(accountNotFoundException.getMessage())
                .build();
    }
}
