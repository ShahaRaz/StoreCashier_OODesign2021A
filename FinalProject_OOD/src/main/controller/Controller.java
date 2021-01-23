package main.controller;

import main.listeners.LogicListenable;
import main.listeners.ViewListenable;
import main.model.Model;
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

//    public class Controller implements ChampionshipListenable , ViewListenable {
//        private Model theModel;
//        private View theView;
}
