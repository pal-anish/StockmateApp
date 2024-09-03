package com.cts.StockService.serviceImpl;

import com.cts.StockService.model.Stock;
import com.cts.StockService.model.StockList;
import com.cts.StockService.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private static final String API_KEY = "4a4b56055b0c462d819cff620eb805d6";

    private static final String API_URL = "https://api.twelvedata.com";

    private final RestTemplate restTemplate;

    @Autowired
    public StockServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public StockList getStocksByCountry(String country) {

        String URL = API_URL + "/stocks?country=" + country + "&apikey=" + API_KEY;
        System.out.println("URL = " + URL);
        return restTemplate.getForObject(URL, StockList.class);
    }


}
