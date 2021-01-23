package main.model;

import main.listeners.LogicListenable;

import java.util.ArrayList;

public class Model {

    private ArrayList<LogicListenable> allListeners;

    public Model() {
        this.allListeners = new ArrayList<>();
    }

    public void registerListener(LogicListenable l) {
        allListeners.add(l);
    }
}
