package cg.hdk.slshop.model;

import java.sql.DataTruncation;
import java.time.Instant;

import static java.lang.Integer.parseInt;


public class OrderHistory {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private double total;
    private Instant timeCreate;
    public OrderHistory(){}

    public OrderHistory(Long id, String name, double price, int quantity, double total, Instant timeCreate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.timeCreate = timeCreate;
    }
    public static OrderHistory parseOrderHistory(String raw){
        OrderHistory orderHistory = new OrderHistory();
        String[] fields = raw.split(",");
        orderHistory.id = Long.parseLong(fields[0]);
        orderHistory.name = fields[1];
        orderHistory.price = Double.parseDouble(fields[2]);
        orderHistory.quantity = parseInt(fields[3]);
        orderHistory.total = Double.parseDouble(fields[4]);
        orderHistory.timeCreate = Instant.parse(fields[5]);
        return orderHistory;
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

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",
                id,
                name,
                price,
                quantity,
                total,
                timeCreate);
    }
}
