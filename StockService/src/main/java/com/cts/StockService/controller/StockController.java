package com.cts.StockService.controller;

import com.cts.StockService.model.StockList;
import com.cts.StockService.service.StockService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stocks")
@SecurityRequirement(name = "bearerAuth")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public StockList getStockByCountry(@RequestParam String country) {
        return stockService.getStocksByCountry(country);
    }

}
