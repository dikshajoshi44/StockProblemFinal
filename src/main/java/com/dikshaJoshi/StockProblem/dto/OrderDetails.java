package com.dikshaJoshi.StockProblem.dto;

/**
 * Class to hold metadata about the order
 */
public class OrderDetails {
    enum OrderType {
        BUY,
        SELL
    };

    Integer orderId;
    OrderType orderType;
    String stockName;
    Long orderTime;
    Double price;
    Integer quantity;

    public OrderDetails(Integer orderId, OrderType orderType, String stockName, Long orderTime, Double price,
                        Integer quantity) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.stockName = stockName;
        this.orderTime = orderTime;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderDetails createOrder(String orderId, String orderType, String stockName, Long orderTime,
                                           Double price, Integer quantity) {
        OrderType orderTypeEnumValue = null;
        Integer orderIdParsed = Integer.parseInt(orderId.split("#")[1]);
        if (orderType == "buy") {
            orderTypeEnumValue = OrderType.BUY;
        } else {
            orderTypeEnumValue = OrderType.SELL;
        }
        return new OrderDetails(orderIdParsed, orderTypeEnumValue, stockName, orderTime, price, quantity);
    }

    public OrderDetails clone(Integer newQuantity) {
        return new OrderDetails(this.orderId, this.orderType, this.stockName, this.orderTime, this.price,
                newQuantity);
    }
}
