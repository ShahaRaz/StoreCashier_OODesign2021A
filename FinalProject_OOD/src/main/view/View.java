package main.view;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 * */
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.interfaces.saleEventListener;
import main.listeners.ViewListenable;
import main.model.Product;
import main.model.store.Store;

public class View extends GridPane {

	private static final double ENLRAGMENT_FACTOR = 1; // constant

	private ArrayList<ViewListenable> allListeners;
	private HBox hbButtons;
	private static final String TAG = "View";

	private Stage stage;

	private AddProductView addWindow;
	private RemoveProductView removeWindow;
	private ProductTableView tableView;
	private SaleWondow saleWindow;
	private Set<Map.Entry<String, Product>> products_set_copy;
	private Table_CostumersRespondToPromotion tableResponse;

	public void registerListener(ViewListenable l) {
		allListeners.add(l);
	}

	public View(Stage stg) {
		allListeners = new ArrayList<ViewListenable>();
		this.stage = stg;

		hbButtons = getHBox();
		creatTabPane();

		Scene scene = new Scene(hbButtons, 505 * ENLRAGMENT_FACTOR, 600 * ENLRAGMENT_FACTOR);
		stage.setScene(scene);
		stage.setTitle("Store Saver");
		stage.show();
	}

	/**
	 * Creating the TabPane with TableView & AddWindow & RemoveWindow & SaleWindow.
	 */
	public void creatTabPane() {
		TabPane tbPane = new TabPane();

		tableView = new ProductTableView(this);
		addWindow = new AddProductView(this);
		removeWindow = new RemoveProductView(this);
		saleWindow = new SaleWondow(this);
		tableResponse = new Table_CostumersRespondToPromotion(this);

		Tab tab1 = new Tab("Table Of All Products", tableView);
		Tab tab2 = new Tab("Add Product", addWindow);
		Tab tab3 = new Tab("Remove Product", removeWindow);
		Tab tab4 = new Tab("Sale", saleWindow);
		Tab tab5 = new Tab("CustomerResponse", tableResponse);

		tbPane.getTabs().add(tab1);
		tbPane.getTabs().add(tab2);
		tbPane.getTabs().add(tab3);
		tbPane.getTabs().add(tab4);
		tbPane.getTabs().add(tab5);

		hbButtons.getChildren().add(tbPane);
	}

	/** @param addMe - View sending request to add specific product */
	public void fireAddNewProduct(Product addMe) {
		for (ViewListenable l : allListeners)
			l.viewAskToAddProduct(addMe);
	}

	/** @param removeMe - View sending request to remove specific product */
	public void fireRemoveProduct(Product removeMe) {
		for (ViewListenable l : allListeners)
			l.viewAskToRemoveProduct(removeMe);
	}

	/** @param searchMe - View sending request to search specific product */
	public void fireSearchProduct(String searchMe) {
		for (ViewListenable l : allListeners)
			l.viewAskForProduct(searchMe);
	}

	/** View sending request to update list of products */
	public void fireListOfProducts() {
		for (ViewListenable l : allListeners)
			l.viewAskForListOfAllProducts();
	}

	/** View sending request to Undo last action */
	public void fireUndo() {
		for (ViewListenable l : allListeners)
			l.viewAskForUndo();
	}

	/** View sending request to save current state */
	public void fireSave() {
		for (ViewListenable l : allListeners)
			l.viewAskToSave();
	}

	/** View sending request to reverse a specific state */
	public void fireReverse() {
		for (ViewListenable l : allListeners)
			l.viewAskToReverse();
	}

	// TODO: Send product to model to create manipulation on product.
	/** @param product - View sending request to create sale for product */
	public void fireSale(Product product) {
		for (ViewListenable l : allListeners)
			l.viewAskToSendSale(product);
	}

	/** Sending fire to remove all products */
	public void fireRemoveAllProducts() {
		for (ViewListenable l : allListeners) {
			l.viewAskToRemoveAllProducts();
		}
	}

	/**
	 * Sending sorting Key to model
	 * 
	 * @param toggle - The selected RadioButton from the user
	 */
	private void fireSortingMethod(Toggle toggle) {
		int key = 0;
		if (toggle.toString().contains("Ascending Order")) {
			key = Store.KEYS.ORDER_BY_ABC_UP;
		} else if (toggle.toString().contains("Descending Order")) {
			key = Store.KEYS.ORDER_BY_ABC_DOWN;
		} else
			key = Store.KEYS.ORDER_BY_INSERT_ORDER;

		// TODO: Try replace toString to equals on Toggle
		for (ViewListenable l : allListeners)
			l.viewSendSortingMethod(key);

	}

	/* After updating the status field, cleaning the fields for each tab */
	/** Cleaning the ComboBox of AddWindow and SaleWindow */
	public void clearFieldsAfterRemoved() {
		// Clear other's tabs fields after remove product.
		addWindow.cleanValueFields();
		saleWindow.cleanValueFields();
	}

	/**
	 * Run through listeners and sending sale
	 * 
	 * @param listeners - all subscribed customers
	 */
	public void notifySubscribedCustomers(ArrayList<saleEventListener> listeners) {
		tableResponse.sendSaleListeners(listeners);
		saleWindow.updateStatus("S-A-L-E sent!", "green");
		Platform.runLater(() -> {
			new Thread(tableResponse).start();
		});
	}

	/** @param products - List of all product to update the relevant ComboBox */
	public void nofityProductsArrived(Set<Map.Entry<String, Product>> products) {
		this.products_set_copy = products;
		updateBomboBox();
	}

	/** Update the ComboBox and TableView */
	private void updateBomboBox() {
		tableView.updateTable(products_set_copy);
		addWindow.updateComboBox(products_set_copy);
		removeWindow.updateComboBox(products_set_copy);
		saleWindow.updateComboBox(products_set_copy);
	}

	/**
	 * @param productDetails - Getting from the model a specific product and insert
	 *                       to relevant fields
	 */
	public void getProductFromModel(Product productDetails) {
		if (addWindow.isAddWindowSent) {
			addWindow.setFields(productDetails);
			saleWindow.cleanValueFields(); // Not necessary needed
		} else if (saleWindow.isSaleWindowSent) {
			saleWindow.setFields(productDetails);
			addWindow.cleanValueFields(); // Not necessary needed
		}
	}

	/**
	 * Notify a 'Good' message
	 * 
	 * @param content - The message to display on Status label
	 */
	public void notifyNewMessageFromModel(String content) {
//		if (content.contains("The Sale has been sent!")) {
//			 TODO: what we want in here
//		} else { // ask for product list, and update all statuses
		System.out.println("view line 231");
		fireListOfProducts();
		notifyNewMessage(content, "green");
//		}
	}

	/**
	 * Notify a 'Error' message
	 * 
	 * @param elaborate - The message to display on Status label
	 */
	public void notifyFailedOperation(String elaborate) {
		notifyNewMessage(elaborate, "red");
	}

	/**
	 * If block to pick the right status' tab to display
	 * 
	 * @param status - The message to display on Status label
	 * @param color  - Color to color the caption in status
	 */
	private void notifyNewMessage(String status, String color) {
		if (addWindow.isAddWindowSent) {
			this.addWindow.updateStatus(status, color);
		} else if (removeWindow.isRemoveWindowSent) {
			this.removeWindow.updateStatus(status, color);
		} else if (saleWindow.isSaleWindowSent) {
			this.saleWindow.updateStatus(status, color);
		} else if (tableView.isAddWindowSent)
			this.tableView.updateStatus(status, color);
	}

	// Get new styled HBox
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

//	private void popUpShortMassage(String headLine, String Massage, int Width, int Height, int fontSize) {
//		Stage miniStage = new Stage();
//		VBox vbPopup = new VBox();
//		miniStage.setTitle(headLine);
//		Label lblMiniPopup = setHeadLine(Massage, fontSize);
//		// lblMiniPopup.setText(Massage);
//		// lblMiniPopup.setAlignment(Pos.CENTER);
//		setStageCONSTSize(miniStage, Width, Width, Height, Height);
//		Button btnClose = new Button();
//		btnClose.setText("Ok");
//		btnClose.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				miniStage.close();
//			}
//		});
//		vbPopup.getChildren().addAll(lblMiniPopup, btnClose);
//		vbPopup.setAlignment(Pos.CENTER);
//		vbPopup.setSpacing(20 * ENLRAGMENT_FACTOR);
//
//		Scene scene = new Scene(vbPopup, Width * ENLRAGMENT_FACTOR, Height * ENLRAGMENT_FACTOR);
//		miniStage.setScene(scene);
//
//		// miniStage.initStyle(StageStyle.UNDECORATED);
//		miniStage.show();
//
//		miniStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent theEvent) {
//				if (theEvent.getCode() == KeyCode.ENTER || theEvent.getCode() == KeyCode.ESCAPE)
//					miniStage.close();
//			}
//		});
//	}
//
//	private void setStageCONSTSize(Stage stg, int minWidth, int maxWidth, int minHeihgt, int maxHeight) { // height
//		stg.setMinHeight(minHeihgt * ENLRAGMENT_FACTOR);
//		stg.setMaxHeight(maxHeight * ENLRAGMENT_FACTOR);
//		stg.setMinWidth(minWidth * ENLRAGMENT_FACTOR);
//		stg.setMaxWidth(maxWidth * ENLRAGMENT_FACTOR);
//	}
//
//	private Label setHeadLine(String headLine, int fontSize) {
//		Label lblHeadline = new Label(headLine);
//		lblHeadline.setMinHeight(10 * ENLRAGMENT_FACTOR);
//		lblHeadline.setAlignment(Pos.TOP_CENTER);
////		lblHeadline.setFont(Font.font("Cambria", fontSize));
//		return lblHeadline;
//	}

	/** Pop-Up window to get from the user the sorting method */
	public void getSorteWindow() {
		Stage miniStage = new Stage();
		ToggleGroup tglSelectedSort = new ToggleGroup();
		RadioButton rdoSortKey1 = new RadioButton("Ascending Order");
		RadioButton rdoSortKey2 = new RadioButton("Descending Order");
		RadioButton rdoSortKey3 = new RadioButton("Insertion Order");
		rdoSortKey1.setToggleGroup(tglSelectedSort);
		rdoSortKey2.setToggleGroup(tglSelectedSort);
		rdoSortKey3.setToggleGroup(tglSelectedSort);

		// Selected as a default value.
		rdoSortKey1.setSelected(true);

		Button btOK = new Button("OK");
		btOK.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btOK.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fireSortingMethod(tglSelectedSort.getSelectedToggle());
				miniStage.close();
			}
		});

		Button btUndo = new Button("Undo");
		btUndo.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btUndo.setOnAction(e -> {
			fireUndo();
			miniStage.close();
		});

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(10));
		hbox.getChildren().addAll(btOK, btUndo);

		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(rdoSortKey1, rdoSortKey2, rdoSortKey3, hbox);

		Scene scene = new Scene(vbox, 150 * ENLRAGMENT_FACTOR, 150 * ENLRAGMENT_FACTOR);
		vbox.setStyle("-fx-background-color: BEIGE;");

		miniStage.setScene(scene);
		miniStage.initOwner(stage);
		miniStage.initModality(Modality.WINDOW_MODAL);
		miniStage.requestFocus();
		miniStage.show();
		miniStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent theEvent) {
				if (theEvent.getCode() == KeyCode.ENTER || theEvent.getCode() == KeyCode.ESCAPE)
					miniStage.close();
			}
		});
	}

}