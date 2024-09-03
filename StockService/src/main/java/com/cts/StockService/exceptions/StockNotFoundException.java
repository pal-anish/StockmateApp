package com.cts.StockService.exceptions;

public class StockNotFoundException extends RuntimeException{

	public StockNotFoundException(String message) {
		super(message);
	}

	public StockNotFoundException() {
        super();
    }
}
