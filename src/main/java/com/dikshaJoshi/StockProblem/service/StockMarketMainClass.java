package com.dikshaJoshi.StockProblem.service;

import com.dikshaJoshi.StockProblem.dto.BuyQueue;
import com.dikshaJoshi.StockProblem.dto.DLinkedListSellStocks;
import com.dikshaJoshi.StockProblem.dto.StockMarketDataQueues;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Getter
@Setter
public class StockMarketMainClass {

    @Autowired
    StockMarget stockMarget;

    private static StockMarketMainClass instance;
    private final Map<String, StockMarketDataQueues> dataMap;

    private DLinkedListSellStocks head = null;
    private DLinkedListSellStocks tail = null;

    private StockMarketMainClass(){
        this.dataMap = new HashMap<String, StockMarketDataQueues>();
    }

    public static StockMarketMainClass getInstance(){

        if(instance == null){
            instance = new StockMarketMainClass();
        }

        return instance;
    }

    public Boolean buyStock(String stockName, Integer stockPrice, String stockItemIndex, Integer stockItemCount){

        if(dataMap.containsKey(stockName)){

            dataMap.get(stockName)
                    .getBuyQueue()
                    .add(new BuyQueue(stockItemIndex, stockPrice, stockItemCount));

        }else{

            StockMarketDataQueues buySellQueue = new StockMarketDataQueues();

            Queue<BuyQueue> buyQueue = new LinkedList<>();
            buyQueue.add(new BuyQueue(stockItemIndex, stockPrice, stockItemCount));

            DLinkedListSellStocks sellNode = null;

            buySellQueue.setBuyQueue(buyQueue);
            buySellQueue.setSellNode(sellNode);

            dataMap.put(stockName, buySellQueue);

        }

//        return stockMarget.buyStock(stockName, stockValue, stockItemIndex, stockItemCount);
        return true;
    }

    public Boolean sellStock(String stockName, Integer stockItemPrice, String stockItemIndex, Integer stockItemCount){

        if(dataMap.containsKey(stockName)){

            DLinkedListSellStocks sellNode = new DLinkedListSellStocks(stockItemPrice, stockItemIndex, stockItemCount);
            addToMinSortedSellList(sellNode);
            runBuyForAllStocks(dataMap.get(stockName));

        }else{

            StockMarketDataQueues buySellQueue = new StockMarketDataQueues();
            Queue<BuyQueue> buyQueue = new LinkedList<>();

            DLinkedListSellStocks sellNodeList = new DLinkedListSellStocks(stockItemPrice, stockItemIndex, stockItemCount);
            addToMinSortedSellList(sellNodeList);

            buySellQueue.setBuyQueue(buyQueue);
            buySellQueue.setSellNode(sellNodeList);

            dataMap.put(stockName, buySellQueue);

        }



//        return stockMarget.sellStock(stockName, stockValue, stockItemIndex, stockItemCount);

        return true;
    }

    //Min first
    void addToMinSortedSellList(DLinkedListSellStocks sellNode){

        sellNode.setNext(null);
        sellNode.setPrev(head);

        if(head != null){
            if(sellNode.getSellItemPrice() < head.getSellItemPrice()){
                head.setPrev(sellNode);
            }
        }

        head = sellNode;
        head.setNext(null);

    }

    void runBuyForAllStocks(StockMarketDataQueues stockBuySellQueue){

        Queue<BuyQueue> buyQueue = stockBuySellQueue.getBuyQueue();
        DLinkedListSellStocks sellNodeList = stockBuySellQueue.getSellNode();
    }
}
