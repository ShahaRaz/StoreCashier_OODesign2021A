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
    private int lastMapOrdering;

    public Cmnd_removeProduct(Product product, ArrayList<Product> soldProductsArr_ref,
                              SortedMap<String, Product> map_ref, FileHandler theFile, int currentMapOrdering) {
        this.product = map_ref.get(product.getBarcode());
        this.map_ref = map_ref; // reference to the main map
        this.soldProductsArr_ref = soldProductsArr_ref; // A Reference!
        this.theFile = theFile; // A Reference!
        this.lastMapOrdering = currentMapOrdering;
    }


    @Override
    public void execute() {

        theFile.removeProductFromFile(product);
        theFile.readMapFromFile(map_ref,true); // it's inefficient, but that's what we were asked for.
//        map_ref.remove(product.getBarcode());

        soldProductsArr_ref.remove(product); // not listed in the system requirements, but we implement this for possible future use


    }

    /**
     * add the product back to the map
     * update the file
     */
    @Override
    public void undo() {
        map_ref.put(product.getBarcode(),product);
        theFile.saveMapToFile(map_ref,this.lastMapOrdering);

        soldProductsArr_ref.add(product);// not listed in the system requirements, but we implement this for possible future use


    }
}
