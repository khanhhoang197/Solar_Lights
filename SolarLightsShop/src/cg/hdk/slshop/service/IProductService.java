package cg.hdk.slshop.service;

import cg.hdk.slshop.model.ProductsManager;

import java.util.List;

public interface IProductService {
    List<ProductsManager> findAllProducts();
    List<ProductsManager> findProductsByName(String name);
    void addProducts(ProductsManager newProducts);
    void removeProducts(Long id);
    void updateProducts(ProductsManager newProducts);
    void updateQuantity(Long id, int quantity);
    boolean exitsById(Long id);
    ProductsManager checkId(Long id);

    List<ProductsManager> findAllOrderByPriceASC();

    List<ProductsManager> findAllOrderByPriceDESC();



}
