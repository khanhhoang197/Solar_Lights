package cg.hdk.slshop.service;

import cg.hdk.slshop.model.ProductsManager;

import java.util.List;

public interface IProductService {
    List<ProductsManager> findAllProducts();
    void addProducts(ProductsManager newProducts);
    ProductsManager checkId(Long id);



}
