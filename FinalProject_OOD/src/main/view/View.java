package main.view;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 * */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.listeners.ViewListenable;
import main.model.Product;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class View extends GridPane {
	private static final double ENLRAGMENT_FACTOR = 1; // constant

	private ArrayList<ViewListenable> allListeners;
	private HBox hbButtons;
	private Stage stage;

	private AddProductView addWindow;
	private RemoveProductView removeWindow;
	private ProductTableView tableView;

	public void registerListener(ViewListenable l) {
		allListeners.add(l);
	}

	public View(Stage stg) {
		allListeners = new ArrayList<ViewListenable>();
		this.stage = stg;

		hbButtons = getHBox();
		creatTabPane();
		
		Scene scene = new Scene(hbButtons, 470 * ENLRAGMENT_FACTOR, 600 * ENLRAGMENT_FACTOR);
		stage.setScene(scene);
		stage.setTitle("Store Saver");
		stage.show();
	}

	public void creatTabPane() {
		TabPane tbPane = new TabPane();
		Tab tab1 = new Tab("Table Of All Products", tableView = new ProductTableView(stage, this));
		Tab tab2 = new Tab("Add Product", addWindow = new AddProductView(stage, this));
		Tab tab3 = new Tab("Remove Product", removeWindow = new RemoveProductView(stage, this));
		tbPane.getTabs().add(tab1);
		tbPane.getTabs().add(tab2);
		tbPane.getTabs().add(tab3);

		hbButtons.getChildren().add(tbPane);
	}

	public void fireAddNewProduct(Product addMe) {
		for (ViewListenable l : allListeners) {
			l.viewAskToAddProduct(addMe);
		}
	}

	public void fireRemoveProduct(Product removeMe) {
		for (ViewListenable l : allListeners) {
			l.viewAskToRemoveProduct(removeMe);
		}
	}

	public void fireSearchProduct(String searchMe) {
		for (ViewListenable l : allListeners) {
			l.viewAskForProduct(searchMe);
		}
	}

	public void fireListOfProducts() {
		for (ViewListenable l : allListeners) {
			l.viewAskForListOfAllProducts();
		}
	}

	public void notifyProductRejected(Product p, String str) {
		// show user that the massage and explanation
		addWindow.updateStatus(str, "red");
	}

	public void notifyProductNotExist(Product p, String str) {
		// show user that the massage and explanation
		removeWindow.updateStatus(str, "red");
	}

	public void notifyProductAdded(Product p) {
		// show user that the massage that product was successfully added
		addWindow.updateStatus("The product " + p.getBarcode() + " added!", "green");
	}

	public void notifyProductRemoved(Product p) {
		// Display removed massage.
		removeWindow.updateStatus("The product " + p.getBarcode() + " removed!", "green");
	}

	public void nofityProductsArrived(Set<Map.Entry<String, Product>> products) {
		tableView.updateTable(products);
		addWindow.updateComboBox(products);
		removeWindow.updateComboBox(products);
	}

	// get new styled hbox
	private HBox getHBox() {
		HBox hBox = new HBox(5);
		hBox.setMinSize(300, 50);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setAlignment(Pos.CENTER);
		return hBox;
	}

	public AddProductView getAddWindow() {
		return addWindow;
	}

	public void setAddWindow(AddProductView addWindow) {
		this.addWindow = addWindow;
	}

	public RemoveProductView getRemoveWindow() {
		return removeWindow;
	}

	public void setRemoveWindow(RemoveProductView removeWindow) {
		this.removeWindow = removeWindow;
	}

	public ProductTableView getTableView() {
		return tableView;
	}

	public void setTableView(ProductTableView tableView) {
		this.tableView = tableView;
	}

	public void getProductFromModel(Product productDetails) {
		addWindow.setFields(productDetails);
	}

}
//		btnAdd = new Button("Add Product");
//		btnAdd.setOnAction(e -> {
//			addWindow = new AddProductView(stage);
//			if (	!addWindow.getTxtFldPrdctBarCode().getText().equals("")
//				||  !addWindow.getTxtFldPrdctBarCode().getText().equals(null))
//				fireAddNewProduct(new Product(addWindow.getTxtFldPrdctBarCode().getText()));
//		});
//		btnRemove = new Button("Remove Product");
//		btnRemove.setOnAction(e -> {
//			removeWindow = new RemoveProductView(stage);
//			if (	!removeWindow.getTxtFldPrdctBarCode().getText().equals("")
//				||  !removeWindow.getTxtFldPrdctBarCode().getText().equals(null))
//				fireRemoveProduct(new Product(removeWindow.getTxtFldPrdctBarCode().getText()));
//		});
//
//		hbButtons.getChildren().addAll(btnAdd, btnRemove);

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