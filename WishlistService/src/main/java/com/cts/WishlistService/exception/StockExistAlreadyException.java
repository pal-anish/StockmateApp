package com.cts.WishlistService.exception;

public class StockExistAlreadyException extends RuntimeException{
	public StockExistAlreadyException(String message)
	{
		super(message);
	}
}
