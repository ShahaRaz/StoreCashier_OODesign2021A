package main.view;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 * */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
	private SaleWondow saleWindow;
	private Set<Map.Entry<String, Product>> products_set_copy;

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

		tableView = new ProductTableView(stage, this);
		addWindow = new AddProductView(stage, this);
		removeWindow = new RemoveProductView(stage, this);
		saleWindow = new SaleWondow(stage, this);

		Tab tab1 = new Tab("Table Of All Products", tableView);
		Tab tab2 = new Tab("Add Product", addWindow);
		Tab tab3 = new Tab("Remove Product", removeWindow);
		Tab tab4 = new Tab("Sale", saleWindow);

		tbPane.getTabs().add(tab1);
		tbPane.getTabs().add(tab2);
		tbPane.getTabs().add(tab3);
		tbPane.getTabs().add(tab4);
		
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

	public void fireUndo() {
		for (ViewListenable l : allListeners) {
			l.viewAskForUndo();
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
		// finished adding product
		fireListOfProducts();

	}

	public void notifyProductRemoved(Product p) {
		// Display removed massage.
		removeWindow.updateStatus("The product " + p.getBarcode() + " removed!", "green");
		fireListOfProducts();
		addWindow.cleanValueFields();
	}

	public void nofityProductsArrived(Set<Map.Entry<String, Product>> products) {
		this.products_set_copy = products;
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

	public void notifyNewMessageFromModel(String headline, String content) {
		if (headline.contains("Undo completed!")){
			fireListOfProducts(); // ask model for new product list
			this.removeWindow.updateStatus("undo succeeded", "green");
			this.addWindow.updateStatus("undo succeeded", "green");
		}

	}

	public void notifyFailedOperation(String errorMassage, String elaborate) {
		if (errorMassage.contains("UNDO")){
			popUpShortMassage(errorMassage,elaborate,400,200,20);
			this.removeWindow.updateStatus("undo failed, no action recorded", "red");
			this.addWindow.updateStatus("undo failed, no action recorded", "red");
		}

	}

	private void popUpShortMassage(String headLine , String Massage,int Width, int Height,int fontSize) {
		Stage miniStage = new Stage();
		VBox vbPopup = new VBox();
		miniStage.setTitle(headLine);
		Label lblMiniPopup =setHeadLine(Massage,fontSize);
		//	lblMiniPopup.setText(Massage);
		//	lblMiniPopup.setAlignment(Pos.CENTER);
		setStageCONSTSize(miniStage, Width, Width, Height, Height);
		Button btnClose = new Button();
		btnClose.setText("Ok");
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				miniStage.close();
			}
		});
		vbPopup.getChildren().addAll(lblMiniPopup,btnClose);
		vbPopup.setAlignment(Pos.CENTER);
		vbPopup.setSpacing(20*ENLRAGMENT_FACTOR);

		Scene scene = new Scene(vbPopup,Width*ENLRAGMENT_FACTOR,Height*ENLRAGMENT_FACTOR);
		miniStage.setScene(scene);

		//miniStage.initStyle(StageStyle.UNDECORATED);
		miniStage.show();

		miniStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent theEvent) {
				if(theEvent.getCode()==KeyCode.ENTER||theEvent.getCode()== KeyCode.ESCAPE)
					miniStage.close();
			}
		});
	}
	private void setStageCONSTSize(Stage stg,int minWidth  , int maxWidth, int minHeihgt,int maxHeight) { // height
		stg.setMinHeight(minHeihgt*ENLRAGMENT_FACTOR);
		stg.setMaxHeight(maxHeight*ENLRAGMENT_FACTOR);
		stg.setMinWidth(minWidth*ENLRAGMENT_FACTOR);
		stg.setMaxWidth(maxWidth*ENLRAGMENT_FACTOR);
	}
	private Label setHeadLine(String headLine,int fontSize) {
		Label lblHeadline = new Label(headLine);
		lblHeadline.setMinHeight(10*ENLRAGMENT_FACTOR);
		lblHeadline.setAlignment(Pos.TOP_CENTER);
		lblHeadline.setFont(Font.font("Cambria", fontSize));
		return lblHeadline;
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