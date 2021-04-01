package com.dikshaJoshi.StockProblem.dto;

/**
 * Represents the stock in pending sell requests queue. The ordering defined here ensure that
 * items from queue are returned in order of the price. Order with lowest price will be
 * returned first.
 */
public class SellItem extends TransactionItem {

    public SellItem(OrderDetails orderDetails) {
        this.order = orderDetails;
    }

    @Override
    public int compareTo(TransactionItem other) {
        if (this.order.price > other.order.price) {
            return 1;
        } else {
            return -1;
        }
    }
}
