package com.cts.StockService.exceptions;

public class JWTTokenException extends RuntimeException {
    public JWTTokenException(String message) {
		super(message);
	}
}
