package main.model;

/*
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 * */

import main.listeners.LogicListenable;

import java.util.ArrayList;

public class Model {
    private Store store;
    private ArrayList<LogicListenable> allListeners;

    public Model() {
        this.allListeners = new ArrayList<>();
        store = new Store();
    }

    public void registerListener(LogicListenable l) {
        allListeners.add(l);
    }

    public void addProduct(Product p) {
        if(store.getProductDetails(p.getId())==null) { //  product isn't yet in hashMap
            if(p.isValidProduct()){
                store.addNewProduct(p);
                fireProductAdded(p);
            }
            else{
                fireProductNotGood(p,"Insert All Product's fields");
            }
            return;
        }

        store.addNewProduct(p);
        fireProductAdded(p);


    }


    private void fireProductAdded(Product p) {
        for (LogicListenable l  : allListeners){
            l.modelAddedProduct(p);
        }
    }

    private void fireProductNotGood(Product p,String str) {
        for (LogicListenable l  : allListeners){
            l.modelRejectedProduct(p,str);
        }
    }

    private void fireSendProductsArrToView(ArrayList<Product> products){
        for (LogicListenable l  : allListeners){
            l.modelSendProductsList(products);
        }
    }
}
