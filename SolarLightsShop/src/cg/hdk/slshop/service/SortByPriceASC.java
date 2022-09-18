package cg.hdk.slshop.service;

import cg.hdk.slshop.model.ProductsManager;

import java.util.Comparator;

public class SortByPriceASC implements Comparator<ProductsManager> {
    @Override
    public int compare(ProductsManager o1, ProductsManager o2) {
        return (int) (o1.getPrice() - o2.getPrice());
    }
}
