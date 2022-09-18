package cg.hdk.slshop.service;

import cg.hdk.slshop.model.ProductsManager;

import java.util.Comparator;

public class SortByPriceDESC implements Comparator<ProductsManager> {
    @Override
    public int compare(ProductsManager o1, ProductsManager o2) {
        return (int) (o2.getPrice() - o1.getPrice());
    }
}
