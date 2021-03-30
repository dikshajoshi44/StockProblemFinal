package com.dikshaJoshi.StockProblem.service;

import com.dikshaJoshi.StockProblem.dto.StockMarketDataQueues;

public interface StockMarget {

    Boolean buyStock(String stockName, String stockValue, String stockItemIndex, String stockItemCount);

    Boolean sellStock(String stockName, String stockValue, String stockItemIndex, String stockItemCount);
}
