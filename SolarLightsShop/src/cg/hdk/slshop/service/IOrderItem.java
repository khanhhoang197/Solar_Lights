package cg.hdk.slshop.service;

import cg.hdk.slshop.model.OrderItem;

import java.util.List;

public interface IOrderItem {
    List<OrderItem> findAllOrderItem();
    void addOrderItem(OrderItem newOrderItem);
    void updateOrderItem();
    OrderItem getOrderItemById();
}
