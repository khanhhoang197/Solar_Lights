package cg.hdk.slshop.model;

import java.time.Instant;

public class OrderItem {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private double total;
    private Instant timeCreate;

    public OrderItem(){

    }

    public OrderItem(Long id, String name, double price, int quantity, double total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public OrderItem(Long id, String name, double price, int quantity, double total, Instant timeCreate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.timeCreate = timeCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Instant getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Instant timeCreate) {
        this.timeCreate = timeCreate;
    }

    public static OrderItem parserOderItem(String raw){
        OrderItem orderItem = new OrderItem();
        String[] fields = raw.split(",");
        orderItem.id = Long.parseLong(fields[0]);
        orderItem.name = fields[1];
        orderItem.price = Double.parseDouble(fields[2]);
        orderItem.quantity = Integer.parseInt(fields[3]);
        orderItem.total = Double.parseDouble(fields[4]);
        return orderItem;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",
                id,
                name,
                price,
                quantity,
                total);
    }
}
