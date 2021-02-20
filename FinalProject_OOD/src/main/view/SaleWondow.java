package main.view;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.Customer;
import main.model.Product;

public class SaleWondow extends GridPane {

	/*Boolean attribute for ComboBox.SenOnAction*/
	protected boolean isAddressingModel = true;
	/*Boolean attribute for UpdateFields method*/
	protected boolean isSaleWindowSent;
	// Variables
	private View view;
	/* ProductName */
	private TextField txtFldPrdctName;
	/* Price */
	private TextField txtFldPrdctPrice;
	/* Barcode */
	private ComboBox<String> cboxPrdctBarCode;
	/* PriceToStore */
	private TextField txtFldPrdctPriceToStore;
	/* Status */
	private Label lblStatus;
	/* Add button */
	private Button btnAddSale;
	/* Clear button */
	private Button btnClear;
	/* Undo button */
	private Button btnUndo;

	public SaleWondow(View view) {
		super();
		this.view = view;

		init();
	}

	private void init() {
		initRoot();
		initTitle();
		initStatus();
		initSaleView();
	}

	private void initSaleView() {
		initFldBarcode();
		initFldName();
		initFldPrice();
		initFldPriceToStore();
		initAddButton();
		initUndoButton();
		initClearButton();
	}

	private void initRoot() {
		setMinSize(450, 400);
		setPadding(new Insets(10, 10, 10, 10));
		setVgap(10);
		setHgap(10);
		setAlignment(Pos.CENTER);
		setStyle("-fx-border-color: black");
		setStyle("-fx-background-color: BEIGE;");
	}

	private void initTitle() {
		Reflection r = new Reflection();
		r.setFraction(0.6);
		Text title = new Text("S A L E ! !");
		title.setFont(Font.font("ariel", FontWeight.BOLD, 25));
		title.setFill(Color.RED);
		title.setEffect(r);
		setHalignment(title, HPos.CENTER);
		Label lblTitle = new Label("ENTER Product and sent promotion:");
		lblTitle.setStyle("-fx-text-alignment: center;-fx-text-fill: black;-fx-font-weight: bold");
		setHalignment(lblTitle, HPos.CENTER);
		add(title, 0, 0, 5, 1);
		add(lblTitle, 0, 1, 5, 1);
	}

	private void initFldBarcode() {
		cboxPrdctBarCode = new ComboBox<String>();
		cboxPrdctBarCode.setOnMouseClicked(e -> updateStatus("", "black"));
		cboxPrdctBarCode.setPromptText("Barcode");
		cboxPrdctBarCode.getSelectionModel().select("");
		cboxPrdctBarCode.setEditable(true);

		add(cboxPrdctBarCode, 1, 3);
		add(new Label("Product Barcode: "), 0, 3);

		view.fireListOfProducts();

		cboxPrdctBarCode.setOnAction(e -> {
			String selectedValue = (String) cboxPrdctBarCode.getValue();

			if (selectedValue != null && isAddressingModel) {
				isSaleWindowSent = true;
				view.fireSearchProduct(selectedValue);
				isSaleWindowSent = false;
			}
			isAddressingModel = true; // don't go to model for searching empty product
		});

		cboxPrdctBarCode.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				txtFldPrdctName.requestFocus();
				ev.consume();
			}
		});
	}

	private void initFldName() {
		txtFldPrdctName = new TextField();
		txtFldPrdctName.setOnMouseClicked(e -> updateStatus("", "black"));
		txtFldPrdctName.setPromptText("Product Name");

		add(new Label("Product Name: "), 0, 4);
		add(txtFldPrdctName, 1, 4);
		// Switch to the next txtField after pressing Enter.
		txtFldPrdctName.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				txtFldPrdctPrice.requestFocus();
				ev.consume();
			}
		});
	}

	private void initFldPrice() {
		txtFldPrdctPrice = new TextField();
		txtFldPrdctPrice.setOnMouseClicked(e -> updateStatus("", "black"));
		txtFldPrdctPrice.setPromptText("Price");

		add(new Label("Product Price: "), 0, 5);
		add(txtFldPrdctPrice, 1, 5);
		// Switch to the next txtField after pressing Enter.
		txtFldPrdctPrice.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				txtFldPrdctPriceToStore.requestFocus();
				ev.consume();
			}
		});
	}

	private void initFldPriceToStore() {
		txtFldPrdctPriceToStore = new TextField();
		txtFldPrdctPriceToStore.setOnMouseClicked(e -> updateStatus("", "black"));
		txtFldPrdctPriceToStore.setPromptText("Price to Store");

		add(new Label("Store Price: "), 0, 6);
		add(txtFldPrdctPriceToStore, 1, 6);
		// Switch to the next txtField after pressing Enter.
		txtFldPrdctPriceToStore.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				btnAddSale.requestFocus();
				ev.consume();
			}
		});
	}
	
	// init status
	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 7);
		add(lblStatus, 1, 7, 4, 1);
	}

	// update status
	public void updateStatus(String status, String color) {
		lblStatus.setText(status);
		lblStatus.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
	}

	public void updateComboBox(Set<Entry<String, Product>> products) {
		cboxPrdctBarCode.getItems().clear(); // SetOnAction->FireSearchProducct (AVOID ME!)

		for (Map.Entry<String, Product> e : products) {
			cboxPrdctBarCode.getItems().add(e.getKey());
		}
	}

	public void setFields(Product productDetails) {
		txtFldPrdctName.setText(productDetails.getDescription());
		txtFldPrdctPrice.setText(String.valueOf((productDetails.getPriceSold())));
		txtFldPrdctPriceToStore.setText(String.valueOf((productDetails.getCostToStore())));
	}

	private void initClearButton() {
		btnClear = new Button("Clear Product");
		btnClear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnClear.setOnAction(e -> {
			cleanValueFields();
		});
		add(btnClear, 1, 11);
	}

	private void initAddButton() {
		btnAddSale = new Button("Add Sale");
		btnAddSale.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnAddSale.setOnAction(e -> {
			// Store the data.

			// Product's Description
			String description = txtFldPrdctName.getText();
			description = description.equals("") ? "NA" : description;

			// Product's price to store
			String priceToStoreStr = txtFldPrdctPriceToStore.getText();
			int priceToStore = 0;
			try {
				priceToStore = Integer.parseInt(priceToStoreStr.equals("") ? "0" : priceToStoreStr);
			} catch (Exception e1) {
				System.err.println("letting priceToStore be 0");
			}

			// Product's price sold
			String priceSoldStr = txtFldPrdctPrice.getText();
			int priceSold = 0;
			try {
				priceSold = Integer.parseInt(priceSoldStr.equals("") ? "0" : priceSoldStr);
			} catch (Exception e2) {
				System.err.println("Letting price to store be 0");
			}

			// Product's id
			String id = cboxPrdctBarCode.getValue();
			try {
				id = id.equals("") ? null : id;
			} catch (Exception e2) {
				System.err.println("Letting Bercode be null");
			}

			// Product's Customer who bought it.
			isSaleWindowSent = true;
			view.fireSale(new Product(description, priceToStore, priceSold, null, id));
			isSaleWindowSent = false;
			isAddressingModel = false;
			
			cboxPrdctBarCode.requestFocus();
			cleanValueFields();
		});
		add(btnAddSale, 1, 10);
	}

	private void initUndoButton() {
		btnUndo = new Button("Undo");
		btnUndo.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnUndo.setOnAction(e -> {
			isSaleWindowSent = true;
			view.fireUndo();
			isSaleWindowSent = false;
		});
		add(btnUndo, 1, 12);
	}

	// clean Value Fields
	public void cleanValueFields() {
		isAddressingModel = false;
		cboxPrdctBarCode.setValue("");
		txtFldPrdctName.setText("");
		txtFldPrdctPriceToStore.setText("");
		txtFldPrdctPrice.setText("");
		isAddressingModel = true;
	}
}
