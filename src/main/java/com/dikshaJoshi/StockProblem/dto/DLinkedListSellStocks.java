package com.dikshaJoshi.StockProblem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DLinkedListSellStocks {

    Integer sellItemPrice;
    String sellItemIndex;
    Integer sellItemCount;

    DLinkedListSellStocks prev;
    DLinkedListSellStocks next;

    public DLinkedListSellStocks(Integer itemPrice, String itemIndex, Integer itemCount) {
        this.sellItemPrice = itemPrice;
        this.sellItemIndex = itemIndex;
        this.sellItemCount = itemCount;
    }

}
