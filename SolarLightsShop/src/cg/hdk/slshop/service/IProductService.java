package cg.hdk.slshop.service;

import cg.hdk.slshop.model.ProductsManager;

import java.util.List;

public interface IProductService {
    List<ProductsManager> findAllProducts();
    List<ProductsManager> findProductsByName(String name);
    void addProducts(ProductsService newProducts);
    void editProducts(ProductsService newProducts);
    void removeProducts(Long id);
    void updateProducts(Long id, Double quantity);
    boolean exitsById(Long id);
    ProductsManager checkId(Long id);

}
