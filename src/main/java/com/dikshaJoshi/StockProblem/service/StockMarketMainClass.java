package com.dikshaJoshi.StockProblem.service;

import com.dikshaJoshi.StockProblem.dto.OrderDetails;
import com.dikshaJoshi.StockProblem.dto.StockData;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StockMarketMainClass {

    private static StockMarketMainClass instance;
    private final Map<String, StockData> stockMap;

    private StockMarketMainClass(){
        this.stockMap = new HashMap<String, StockData>();
    }

    public static StockMarketMainClass getInstance(){

        if(instance == null){
            instance = new StockMarketMainClass();
        }

        return instance;
    }

    public Boolean buyStock(String index, String stockName, Double stockPrice, Integer stockItemCount){

        OrderDetails orderdetails = OrderDetails.createOrder(index, "buy", stockName, System.nanoTime(),
                stockPrice, stockItemCount);

        if (stockMap.containsKey(stockName)) {
            stockMap.get(stockName)
                    .buyStock(orderdetails);
        } else {
            StockData stockData = new StockData();
            stockData.buyStock(orderdetails);
            stockMap.put(stockName, stockData);
        }
        return true;
    }

    public Boolean sellStock(String index, String stockName, Double stockPrice, Integer stockItemCount){

        OrderDetails orderdetails = OrderDetails.createOrder(index, "sell", stockName, System.nanoTime(),
                stockPrice, stockItemCount);

        if(stockMap.containsKey(stockName)){
            stockMap.get(stockName)
                    .sellStock(orderdetails);
        } else {
            StockData stockData = new StockData();
            stockData.sellStock(orderdetails);
            stockMap.put(stockName, stockData);
        }
        return true;
    }
}
