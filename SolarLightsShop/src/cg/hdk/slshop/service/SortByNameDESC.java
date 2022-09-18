package cg.hdk.slshop.service;

import cg.hdk.slshop.model.ProductsManager;

import java.util.Comparator;

public class SortByNameDESC implements Comparator<ProductsManager> {
    @Override
    public int compare(ProductsManager o1, ProductsManager o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
