package cg.hdk.slshop.views;

import cg.hdk.slshop.model.Order;
import cg.hdk.slshop.model.OrderItem;
import cg.hdk.slshop.model.ProductsManager;
import cg.hdk.slshop.service.IProductService;
import cg.hdk.slshop.service.ProductsService;
import cg.hdk.slshop.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {

    public final String PATH_ORDER = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\Order.csv";
    Scanner scanner = new Scanner(System.in);
    public List<Order> orders;
    public OrderView() {
        List<Order> orderList = new ArrayList<>();
        this.orders = orderList;
    }

    public List<Order> findAllOrder(){
        List<Order> orders = new ArrayList<>();
        List<String> lines = CSVUtils.read(PATH_ORDER);
        for (String line : lines){
            orders.add(Order.parseOder(line));
        }
        return orders;
    }
    public List<OrderItem> addOrderItem(Long id) {
        List<OrderItem> orderItems = new ArrayList<>();
        ProductsView productsView = new ProductsView();
        productsView.showProducts();
    return null;
    }
}
