package cg.hdk.slshop.service;

import cg.hdk.slshop.model.ProductsManager;
import cg.hdk.slshop.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductsService extends ProductsManager implements IProductService {
    public final static String PATH = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\products.csv";
    private static ProductsService instanceProducts;

    public static ProductsService getInstanceProducts() {
        if (instanceProducts == null)
            instanceProducts = new ProductsService();
        return instanceProducts;
    }

    public ProductsService() {
    }

    @Override
    public List<ProductsManager> findAllProducts() {
        List<ProductsManager> products = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            products.add(ProductsManager.parseProduct(record));
        }
        return products;
    }

    @Override
    public List<ProductsManager> findProductsByName(String name) {
        List<ProductsManager> products = findAllProducts();
        List<ProductsManager> listFind = new ArrayList<>();
        if (name != null) {
            for (ProductsManager oldProducts : products) {
                if (oldProducts.getName().toUpperCase().contains(name)) ;
                listFind.add(oldProducts);
            }
        }
        return listFind;
    }

    @Override
    public void addProducts(ProductsService newProducts) {
        List<ProductsManager> products = findAllProducts();
        products.add(newProducts);
        CSVUtils.write(PATH, products);

    }

    @Override
    public void editProducts(ProductsService newProducts) {
        List<ProductsManager> products = findAllProducts();
        for (ProductsManager oldProducts : products) {
            if (oldProducts.getIdProduct().equals(newProducts.getIdProduct())) {
                String name = newProducts.getName();
                if (name != null && !name.isEmpty()) {
                    oldProducts.setName(newProducts.getName());
                }
                Integer quantity = newProducts.getQuantity();
                if (quantity != null) {
                    oldProducts.setQuantity(quantity);
                }
                Double price = newProducts.getPrice();
                if (price != null) {
                    oldProducts.setPrice(price);
                }
                CSVUtils.write(PATH, products);
                break;
            }
        }
    }

    @Override
    public void removeProducts(Long id) {
        List<ProductsManager> products = findAllProducts();
        for (ProductsManager oldProduct : products){
            if (oldProduct.getIdProduct().equals(getIdProduct()))
                products.remove(id);
        }
        CSVUtils.write(PATH, products);

    }

    @Override
    public void updateProducts(Long id, Double quantity) {
        List<ProductsManager> products = findAllProducts();
        for (ProductsManager oldProducts : products){
            if (oldProducts.getIdProduct().equals(id)){
                int quantitys = oldProducts.getQuantity();
                if (quantitys >= quantity){
                    oldProducts.setQuantity((int) (quantitys - quantity));
                    CSVUtils.write(PATH, products);
                    break;
                }
            }
        }

    }

    @Override
    public boolean exitsById(Long id) {
        return checkId(id) != null;
    }

    @Override
    public ProductsManager checkId(Long id) {
        List<ProductsManager> products = findAllProducts();
        for (ProductsManager product : products) {
            if (product.getIdProduct().equals(id))
                return product;
        }
        return null;
    }
}
