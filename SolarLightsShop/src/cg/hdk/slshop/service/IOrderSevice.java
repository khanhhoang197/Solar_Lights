package cg.hdk.slshop.service;

import cg.hdk.slshop.model.Order;

import java.util.List;

public interface IOrderSevice {
    List<Order> findAllOrder();
    void add(Order newOrder);
     void updateOder(Order newOrder);
     Order findOrderById(Long id);
    boolean exitsOrderId(Long id);
    List<Order> findOrderByUserId(Long id);
}
