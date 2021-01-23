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

	//View wants to add product.
    @Override
    public void viewAskToAddProduct(Product p) {
        theModel.addProduct(p);
    }
    
    //Model return a massage: The produce added.
    @Override
    public void modelAddedProduct(Product p) {
        theView.notifyProductAdded(p);
    }

    //Model return a massage: The produce rejected.
    @Override
    public void modelRejectedProduct(Product p, String str) {
        theView.notifyProductRejected(p,str);
    }
    
	//View wants to add product.
	@Override
	public void viewAskToRemoveProduct(Product p) {
		theModel.removedProduct(p);
	}
	
    //Model return a massage: The produce removed.
	@Override
	public void modelRemovedProduct(Product p) {
		theView.notifyProductRemoved(p);
	}
	
	

//    public class Controller implements ChampionshipListenable , ViewListenable {
//        private Model theModel;
//        private View theView;
}
