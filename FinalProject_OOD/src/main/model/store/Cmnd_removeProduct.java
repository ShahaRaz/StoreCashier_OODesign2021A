package main.model.store;

import main.model.Product;

import java.util.ArrayList;
import java.util.SortedMap;

public class Cmnd_removeProduct implements Command {

    private ArrayList<Product> soldProductsArr_ref; // reference
    private Product product;
    private SortedMap<String, Product> map_ref;

    public Cmnd_removeProduct(Product product,  SortedMap<String, Product> map_ref, ArrayList<Product> soldProductsArr_ref) {
        this.product = product;
        this.map_ref = map_ref; // reference to the main map
        this.soldProductsArr_ref = soldProductsArr_ref; // A Reference!
    }

    @Override
    public void execute() {
        soldProductsArr_ref.remove(product);
        map_ref.remove(product.getBarcode()); // TODO delete me if im unnecessary
    }

    @Override
    public void undo() {
        soldProductsArr_ref.add(product);
        map_ref.put(product.getBarcode(),product);
    }
}
