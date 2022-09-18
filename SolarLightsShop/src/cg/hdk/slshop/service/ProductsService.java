package cg.hdk.slshop.service;

import cg.hdk.slshop.model.ProductsManager;
import cg.hdk.slshop.utils.CSVUtils;

import java.util.ArrayList;
import java.util.Comparator;
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
    public void addProducts(ProductsManager newProducts) {
        List<ProductsManager> products = findAllProducts();
        products.add(newProducts);
        CSVUtils.write(PATH, products);
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
