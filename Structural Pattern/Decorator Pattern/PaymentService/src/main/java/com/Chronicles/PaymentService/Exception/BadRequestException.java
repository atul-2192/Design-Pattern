package com.Chronicles.PaymentService.Exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) { super(message); }
}
