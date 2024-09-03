package com.cts.StockService.model;

public class StockList {
    private Stock[] data;
    private String status;

    public StockList() {
        super();
    }

    public StockList(Stock[] data, String status) {
        this.data = data;
        this.status = status;
    }

    public Stock[] getData() {
        return data;
    }

    public void setData(Stock[] data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
