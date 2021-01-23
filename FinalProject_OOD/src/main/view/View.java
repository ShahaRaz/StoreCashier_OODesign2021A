package main.view;

/*
 * @author Gadi Engelsman.
 * @author Shachar Raz.
 * */

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.listeners.ViewListenable;
import main.model.Customer;
import main.model.Product;

import java.util.ArrayList;

public class View {
    private ArrayList<ViewListenable> allListeners;
    private static final double ENLRAGMENT_FACTOR = 1; // constant
    public void registerListener(ViewListenable l) {
        allListeners.add(l);
    }
    public View(Stage stage) {
        allListeners = new ArrayList<ViewListenable>();


        BorderPane bpRoot = new BorderPane();

        // Btn -> setOnAction ->
        fireAddNewProduct();

        Scene scene = new Scene(bpRoot,760*ENLRAGMENT_FACTOR,420*ENLRAGMENT_FACTOR);
        stage.setScene(scene);
        stage.show();
    }

    private void fireAddNewProduct(String description, int costToStore, int priceSold, int customerId, String pID) {

        Product addMe = new Product(pID);

        for (ViewListenable l : allListeners){
            l.viewAskToAddProduct(addMe);
        }
    }


    public void notifyProductRejected(Product p, String str) {
        // show user that the massage and explanation
    }

    public void notifyProductAdded(Product p) {
        // show user that the massage that product was successfully added
    }
}


//
//// REFERENCE _______
//	bpRoot = new BorderPane();
//            bpRoot.setPadding(new Insets(20*ENLRAGMENT_FACTOR));
//
//            //____________________________________toggle game type
//            vbChooseGameYype = new VBox();
//            setRdoGameType(vbChooseGameYype);
//
//
//            //____________________________________ add player
//            hbAddPlayer = new HBox();
//            setHbAddPlayer (hbAddPlayer);
//
//            //_____________________________________ Start game
//            vbMiddleStartGame = new VBox();
//            setVbStartGameUnderAddPlayer(vbMiddleStartGame,vbChooseGameYype);
//
//
//            //__________________________________ player rows (General based on players.size() (in model))
//
//            HBox hbMain = new HBox();
//            hbMain.setPadding(new Insets(10));
//            hbMain.setSpacing(10);
//            hbMain.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
//
//
//            //__________________________________ ORGANS OF MAIN BOX
//            btnPlayGameEvent = new BtnPlayGameEventHandler();
//            vbNamesInRoundLbls = new ArrayList<VBox>();
//        tfPlayerNames2 = new ArrayList<TextField>();
//
//        vbPlayBtns = new ArrayList<VBox>();
//        btnPlayGame = new ArrayList<Button>();
//
//        VBox vbNamesTemp2;
//        TextField tfTemp2;
//
//        VBox vbPlayTemp2;
//        Button btnTemp2;
//
//        hbMainView = new HBox();
//        int b=0; //n=0
//        leaugeRoundPointer=0;// Names , Buttons , main round counter
//        // |||||||||| i = Log2n || n = (Log2n)*n || b  = (Log2n)*(n/2) ||||||||||
//        for (int i = NUMBER_OF_PLAYERS; i >= 1 ; i=i/2,leaugeRoundPointer++) { // runs log2n times
//        // add VB of names
//        vbNamesTemp2 = new VBox();
//        for(int j=0;j<i;j++) { //,n++
//        tfTemp2 = new TextField();
//        //tfTemp2.setText(""+n);
//        tfTemp2.setEditable(false); // disable direct editing of cells.
//        tfTemp2.setPrefHeight(10*ENLRAGMENT_FACTOR);
//        tfPlayerNames2.add(tfTemp2); // add to the TextField arrayList [index n]
//        vbNamesTemp2.getChildren().add(tfTemp2); // add to Current VBox [index k]
//        }
//        vbNamesTemp2.setAlignment(Pos.CENTER);
//        vbNamesTemp2.setSpacing((8*(1+leaugeRoundPointer))*ENLRAGMENT_FACTOR);
//        vbNamesTemp2.setPadding(new Insets(7*ENLRAGMENT_FACTOR));
//        vbNamesInRoundLbls.add(vbNamesTemp2); // add Current VBox to arrayList [index counter(i)]
//
//
//        if (i!=1) { // in last round we don't need Playbtn(winner there)
//        // add VB of Btns
//        vbPlayTemp2 = new VBox();
//        for(int k=0;k<i/2;k++,b++) { // maybe move k out, so that numbering
//        btnTemp2 = new Button();
//        btnTemp2.setId("" + b);
//        btnTemp2.setText("Play #" + k);
//        btnTemp2.getId();
//        btnTemp2.setOnAction(btnPlayGameEvent); // set action
//        btnTemp2.setPrefHeight(10*ENLRAGMENT_FACTOR);
//        btnPlayGame.add(btnTemp2); // add to Buttons arrayList [index b]
//        vbPlayTemp2.getChildren().add(btnTemp2);  // add to Current VBox [index k]
//
//        }
//        vbPlayTemp2.setAlignment(Pos.CENTER);
//        vbPlayTemp2.setSpacing((17*(2+leaugeRoundPointer))*ENLRAGMENT_FACTOR);
//        vbPlayBtns.add(vbPlayTemp2); // add Current VBox to arrayList [index counter(i)]
//
//        //____UNCOMMENT NEXT LINES TO PRESENT ALL LEAUGE STRUCTURE ON INIT___
//        // adding VBoxes to view
////		hbMainView2.getChildren().addAll(vbNamesInRoundLbls2.get(leaugeRoundPointer),vbPlayBtns2.get(leaugeRoundPointer));
//        }
//        else {
////			hbMainView2.getChildren().addAll(vbNamesInRoundLbls2.get(leaugeRoundPointer));
//
//        }
//        }
//        leaugeRoundPointer=0;
//        hbMainView.getChildren().addAll(vbNamesInRoundLbls.get(0));
//
//
//        //________________________________________________________________________________________
//
//        for(Node ny: bpRoot.getChildren()) {
//        ((Label)ny).setBorder(new Border(new BorderStroke
//        (Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(20))));
//        }
//
//
//        bpRoot.setLeft(hbMainView);
//        bpRoot.setRight(vbChooseGameYype);
//        bpRoot.setCenter(vbMiddleStartGame);
//
//        Scene scene = new Scene(bpRoot,760*ENLRAGMENT_FACTOR,420*ENLRAGMENT_FACTOR);
//        stage.setScene(scene);
//        stage.show();
//