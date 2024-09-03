package com.cts.StockService.service;

import com.cts.StockService.model.StockList;

import java.util.List;

public interface StockService {
    StockList getStocksByCountry(String country);

}
