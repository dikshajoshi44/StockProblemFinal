package com.dikshaJoshi.StockProblem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Represents the stock in pending buy requests queue. The ordering definted here ensure that
 * items from queue are returned in order of there requests time.
 */
public class BuyItem extends TransactionItem {

    public BuyItem(OrderDetails orderDetails) {
        this.order = orderDetails;
    }

    @Override
    public int compareTo(TransactionItem other) {
        if (this.order.orderTime > other.order.orderTime) {
            return 1;
        } else {
            return -1;
        }
    }
}
