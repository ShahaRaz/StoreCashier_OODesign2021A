package main.controller;

/*
 * @author Gadi Engelsman.
 * @author Shachar Raz.
 * */

import main.listeners.LogicListenable;
import main.listeners.ViewListenable;
import main.model.Model;
import main.model.Product;
import main.view.View;

public class Controller implements ViewListenable, LogicListenable {
    private Model theModel;
    private View theView;

public Controller(Model m, View v) {
    theModel = m;
    theView = v;

    theModel.registerListener(this);
    theView.registerListener(this); //throws
}

    @Override
    public void viewAskToAddProduct(Product p) {
        theModel.addProduct(p);
    }

    @Override
    public void modelRejectedProduct(Product p, String str) {
        theView.notifyProductRejected(p,str);
    }

    @Override
    public void modelAddedProduct(Product p) {
        theView.notifyProductAdded(p);
    }

//    public class Controller implements ChampionshipListenable , ViewListenable {
//        private Model theModel;
//        private View theView;
}
