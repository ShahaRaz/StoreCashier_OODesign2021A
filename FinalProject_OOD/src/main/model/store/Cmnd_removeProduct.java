package main.model.store;

import main.model.FileHandler;
import main.model.Product;

import java.util.ArrayList;
import java.util.SortedMap;

public class Cmnd_removeProduct implements Command {

    private ArrayList<Product> soldProductsArr_ref; // reference
    private Product product;
    private SortedMap<String, Product> map_ref;
    private FileHandler theFile;

    public Cmnd_removeProduct(Product product, ArrayList<Product> soldProductsArr_ref,
                              SortedMap<String, Product> map_ref, FileHandler theFile, int currentMapOrdering) {
        this.product = map_ref.get(product.getBarcode());
        this.map_ref = map_ref; // reference to the main map
        this.soldProductsArr_ref = soldProductsArr_ref; // A Reference!
        this.theFile = theFile; // A Reference!
    }


    @Override
    public void execute() {
        soldProductsArr_ref.remove(product);
        map_ref.remove(product.getBarcode());
//        theFile.removeProductFromFile(product);

    }

    @Override
    public void undo() {
        soldProductsArr_ref.add(product);
        map_ref.put(product.getBarcode(),product);
        theFile.addProductToFile(product);

    }
}
