package com.dikshaJoshi.StockProblem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Getter
@Setter
public class StockMarketDataQueues {
    DLinkedListSellStocks sellNode;
    Queue<BuyQueue> buyQueue;

}
