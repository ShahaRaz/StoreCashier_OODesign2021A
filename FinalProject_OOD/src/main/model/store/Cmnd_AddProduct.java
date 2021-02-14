package main.model.store;

import main.model.Product;

import java.util.ArrayList;
import java.util.SortedMap;

public class Cmnd_AddProduct implements Command{
    private Product oldProductInMap=null; // save it in-case we'll overwrite old details of the product in the map
    boolean wasProductInMapB4thisCmnd=false;
    private ArrayList<Product> soldProductsArr_ref; // reference
    private Product product;
    private SortedMap<String, Product> map_ref;

    public Cmnd_AddProduct(Product product,  SortedMap<String, Product> map_ref, ArrayList<Product> soldProductsArr_ref) {
        this.product = product;
        this.map_ref = map_ref; // reference to the main map
        this.soldProductsArr_ref = soldProductsArr_ref; // A Reference!
    }

    @Override
    public void execute() {
        soldProductsArr_ref.add(product);
        if(map_ref.containsKey(product.getBarcode())) { // knowing if product is already in map
            wasProductInMapB4thisCmnd = true;
            oldProductInMap = map_ref.get(product.getBarcode());
        }
        else
            wasProductInMapB4thisCmnd = false;

        map_ref.put(product.getBarcode(),product);
    }

    @Override
    public void undo() {
        soldProductsArr_ref.remove(product);
        if(wasProductInMapB4thisCmnd) {
            map_ref.remove(product); // TODO delete me if im unnecessary
            map_ref.put(oldProductInMap.getBarcode(),oldProductInMap); // as this command should overwrite the old one

        }
    }
}
