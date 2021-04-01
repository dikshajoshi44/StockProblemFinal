package com.dikshaJoshi.StockProblem;

import com.dikshaJoshi.StockProblem.service.StockMarketMainClass;

public class StockProblemUtil {

    static StockMarketMainClass instance = StockMarketMainClass.getInstance();

    public static void processOrder(String order) {
        String[] instructions = order.split("\\s+");
        String stockItemIndex = instructions[0];
        String stockName = instructions[2];
        String stockType = instructions[3];
        Double stockValue = Double.parseDouble(instructions[4]);
        Integer stockItemCount = Integer.parseInt(instructions[5]);

        if (stockType.equalsIgnoreCase("sell")) {
            instance.sellStock(stockItemIndex, stockName, stockValue, stockItemCount);
        } else {
            instance.buyStock(stockItemIndex, stockName, stockValue, stockItemCount);
        }

    }
}
