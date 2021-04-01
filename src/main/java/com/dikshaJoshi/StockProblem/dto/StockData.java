package com.dikshaJoshi.StockProblem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Getter
@Setter
public class StockData {
    Queue<SellItem> sellQueue = new PriorityQueue<>();
    Queue<BuyItem> buyQueue = new PriorityQueue<>();

    public void buyStock(OrderDetails order) {
        if (sellQueue.isEmpty()) {
            // No stock available for sell. Add the buy order to buy queue
            buyQueue.add(new BuyItem(order));
        } else {
            Integer quantityRemainingToBuy = order.quantity;
            while (quantityRemainingToBuy > 0 && !sellQueue.isEmpty() &&
                    sellQueue.peek().order.price <= order.price) {
                // Select stock with lowest offer price which is less than buy price
                OrderDetails currentSelectedForSell = sellQueue.poll().order;
                if (currentSelectedForSell.quantity > quantityRemainingToBuy) {
                    // All the required shares can be bought with selected item to sell
                    System.out.println("#" + order.orderId + " " + currentSelectedForSell.price + " " +
                            quantityRemainingToBuy + " #" + currentSelectedForSell.orderId);
                    // There are still stocks left to be sold. Update it back to sell queue with remaining quantity
                    Integer remainingToSell = currentSelectedForSell.quantity - quantityRemainingToBuy;
                    quantityRemainingToBuy = 0;
                    currentSelectedForSell.quantity = remainingToSell;
                    sellQueue.add(new SellItem(currentSelectedForSell));
                } else if (currentSelectedForSell.quantity == quantityRemainingToBuy) {
                    // All the required shares can be bought with select item to sell
                    System.out.println("#" + order.orderId + " " + currentSelectedForSell.price + " " +
                            quantityRemainingToBuy + " #" + currentSelectedForSell.orderId);
                    quantityRemainingToBuy = 0;
                } else {
                    // All the required shares can not be bought with select item to sell
                    // buy the stock with current price and remaining at a higher price if availale
                    System.out.println("#" + order.orderId + " " + currentSelectedForSell.price + " " +
                            currentSelectedForSell.quantity + " #" + currentSelectedForSell.orderId);
                    quantityRemainingToBuy = quantityRemainingToBuy - currentSelectedForSell.quantity;
                }
            }

            if (quantityRemainingToBuy > 0) {
                buyQueue.add(new BuyItem(order.clone(quantityRemainingToBuy)));
            }
        }
    }

    public void sellStock(OrderDetails order) {
        if (buyQueue.isEmpty()) {
            // No buy request pending for this stock. Add the sell order to sell queue
            sellQueue.add(new SellItem(order));
        } else {
            Integer quantityRemainingToSell = order.quantity;
            List<BuyItem> tempNonMatchingBuyRequests = new ArrayList();
            while (quantityRemainingToSell > 0 && !buyQueue.isEmpty()) {
                // Select stock for which the order came first
                BuyItem currentSelectedBuyItem = buyQueue.poll();
                OrderDetails currentSelectedForBuy = currentSelectedBuyItem.order;
                if (currentSelectedForBuy.price < order.price) {
                    // The request for buy is at a lower price, so add it back to  buy queue
                    tempNonMatchingBuyRequests.add(currentSelectedBuyItem);
                } else {
                    // The selected buy order can buy all of the sell request stocks
                    if (currentSelectedForBuy.quantity > quantityRemainingToSell) {
                        System.out.println("#" + currentSelectedForBuy.orderId + " " + currentSelectedForBuy.price + " " +
                                quantityRemainingToSell + " #" + order.orderId);
                        // Entire buy request is not satisfied with this sell order. Add remaining quantity to buy queue
                        Integer remainingToBuy = currentSelectedForBuy.quantity - quantityRemainingToSell;
                        quantityRemainingToSell = 0;
                        currentSelectedForBuy.quantity = remainingToBuy;
                        buyQueue.add(new BuyItem(currentSelectedForBuy));
                    } else if (currentSelectedForBuy.quantity == quantityRemainingToSell) {
                        // The selected buy order can buy all of the sell request stocks
                        System.out.println("#" + currentSelectedForBuy.orderId + " " + currentSelectedForBuy.price + " " +
                                quantityRemainingToSell + " #" + order.orderId);
                        quantityRemainingToSell = 0;
                    } else {
                        // The selected buy order can not buy all of the sell request stocks.
                        // Remaining quantity to be sold would be tried with some other buy order
                        System.out.println("#" + currentSelectedForBuy.orderId + " " + currentSelectedForBuy.price + " " +
                                currentSelectedForBuy.quantity + " #" + order.orderId);
                        quantityRemainingToSell = quantityRemainingToSell - currentSelectedForBuy.quantity;
                    }
                }
            }

            if (quantityRemainingToSell > 0) {
                sellQueue.add(new SellItem(order.clone(quantityRemainingToSell)));
            }
            // Add back all buy requests which were at a lower price compared to
            buyQueue.addAll(tempNonMatchingBuyRequests);
        }
    }

}
