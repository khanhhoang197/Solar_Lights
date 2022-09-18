package cg.hdk.slshop.service;

import cg.hdk.slshop.model.ProductsManager;
import java.util.Comparator;

public class SortByNameASC implements Comparator<ProductsManager> {
    @Override
    public int compare(ProductsManager o1, ProductsManager o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
