package cg.hdk.slshop.model;

import java.util.List;
import java.util.Objects;

public class ProductsManager {
    private Long idProduct;
    private String name;
    private int quantity;
    private double price;
    public List<ProductsManager> productsManagers;

    public ProductsManager() {

    }

    public ProductsManager(Long id, String name, int quantity, Double price) {
        this.idProduct = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public static ProductsManager parseProduct(String rawProduct){
        String[] array = rawProduct.split(",");
        ProductsManager product = new ProductsManager();
        product.setIdProduct(Long.parseLong(array[0]));
        product.setName(array[1]);
        product.setQuantity(Integer.parseInt(array[2]));
        product.setPrice(Double.parseDouble(array[3]));
        return product;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s",
                idProduct,
                name,
                quantity,
                price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductsManager that = (ProductsManager) o;
        return Objects.equals(idProduct, that.idProduct)
                && Objects.equals(name, that.name)
                && quantity == that.quantity
                && Double.compare(that.price, price) == 0;

    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, name, quantity, price);
    }
}
