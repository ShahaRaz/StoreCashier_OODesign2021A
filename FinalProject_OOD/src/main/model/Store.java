package main.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Store {
    HashMap<String,Product> productsMap;
    ArrayList<Product> soldProducts;
    ArrayList<Product> inventory; // maybe from file..? needed?

    public Store() {
        this.productsMap =  new HashMap();
        soldProducts = new ArrayList<Product>();
        inventory = new ArrayList<Product>();

    }
    public Product getProductDetails(String id){
        return productsMap.get(id); // if not exists. return null

    }

    public void addNewProduct(Product p) {
        productsMap.put(p.getId(),p);
        soldProducts.add(p);


    }
}
