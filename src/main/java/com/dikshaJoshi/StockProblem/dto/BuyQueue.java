package com.dikshaJoshi.StockProblem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyQueue {
    String  buyItemIndex;
    Integer buyItemPrice;
    Integer buyItemCount;

    public BuyQueue(String buyItemIndex, Integer buyItemPrice, Integer buyItemCount) {
        this.buyItemIndex = buyItemIndex;
        this.buyItemPrice = buyItemPrice;
        this.buyItemCount = buyItemCount;
    }
}
