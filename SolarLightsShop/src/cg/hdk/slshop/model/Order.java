package cg.hdk.slshop.model;

import java.time.Instant;

public class Order {
    private Long idOrder;
    private String fullName;
    private String mobile;
    private String address;
    private Instant timeCreate;

    public Order(){}
    public Order(Long idOrder, String fullName, String mobile, String address, Instant timeCreate) {
        this.idOrder = idOrder;
        this.fullName = fullName;
        this.mobile = mobile;
        this.address = address;
        this.timeCreate = timeCreate;
    }
    public static Order parseOder(String raw){
        Order order = new Order();
        String [] fields = raw.split(",");
        order.idOrder = Long.parseLong(fields[0]);
        order.fullName = fields[1];
        order.mobile = fields[2];
        order.address = fields[3];
        order.timeCreate = Instant.now();
        return order;
    }
    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Instant timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",
                idOrder,
                fullName,
                mobile,
                address,
                timeCreate);
    }
}
