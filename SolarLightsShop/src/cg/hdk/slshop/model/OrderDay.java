package cg.hdk.slshop.model;

import java.time.Instant;

public class OrderDay {
        private Long id;
        private String name;
        private double price;
        private int quantity;
        private Double total;
        private Instant createAt;

        public OrderDay() {
        }

        public OrderDay(Long id, String name, double price, int quantity, Double total, Instant createAt) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.total = total;
            this.createAt = createAt;
        }

        public static OrderDay parserOrderDay(String raw) {
            OrderDay orderDay = new OrderDay();
            String[] fields = raw.split(",");
            orderDay.id = Long.parseLong(fields[0]);
            orderDay.name = fields[1];
            orderDay.price = Double.parseDouble(fields[2]);
            orderDay.quantity = Integer.parseInt(fields[3]);
            orderDay.total = Double.parseDouble(fields[4]);
            orderDay.createAt = Instant.parse(fields[5]);
            return orderDay;
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

        public void setName(String nameFood) {
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

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Instant getCreateAt() {
            return createAt;
        }

        public void setCreateAt(Instant createAt) {
            this.createAt = createAt;
        }

        @Override
        public String toString() {
            return String.format("%d,%s,%s,%s,%s,%s", id, name, price, quantity, total, createAt);
        }
}
