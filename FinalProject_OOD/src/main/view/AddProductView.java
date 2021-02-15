package main.view;
/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

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

public class AddProductView extends GridPane {

//	  	Variables

	private View view;
	/* ProductName */
	private TextField txtFldPrdctName;
	/* Price */
	private TextField txtFldPrdctPrice;
	/* Barcode */
	private ComboBox<String> cboxPrdctBarCode;
	/* PriceToStore */
	private TextField txtFldPrdctPriceToStore;
	/* Customer */
	private TextField txtFldCustomer;
	// TODO: Add TaxtField of phoneNum and isAcceptingPromotions.
	/* Status */
	private Label lblStatus;
	/* Add button */
	private Button btnAdd;
	/* Clear button */
	private Button btnClear;

	private Stage stage;

	public AddProductView(Stage stg, View view) {
		this.stage = stg;
		this.view = view;

		init();
		stage.setScene(new Scene(this, 500, 500));
		stage.setTitle("Store Saver");
		stage.show();
	}

	private void init() {
		initRoot();
		initTitle();
		initAddProduct();
		initStatus();
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
		Text title = new Text("Add Product");
		title.setFont(Font.font("ariel", FontWeight.BOLD, 25));
		title.setFill(Color.RED);
		title.setEffect(r);
		GridPane.setHalignment(title, HPos.CENTER);
		Label lblTitle = new Label("ENTER Product TO ADD:");
		lblTitle.setStyle("-fx-text-alignment: center;-fx-text-fill: black;-fx-font-weight: bold");
		GridPane.setHalignment(lblTitle, HPos.CENTER);
		add(title, 0, 0, 5, 1);
		add(lblTitle, 0, 1, 5, 1);
	}

	private void initAddProduct() {
		initFldName();
		initFldPrice();
		initFldBarcode();
		initFldPriceToStore();
		initFldCustomer();
		initAddButton();
		initClearButton();
	}

	private void initFldName() {
		txtFldPrdctName = new TextField();
		txtFldPrdctName.setOnMouseClicked(e -> updateStatus("", "black"));
		txtFldPrdctName.setPromptText("Product Name");

		add(new Label("Product Name: "), 0, 3);
		add(txtFldPrdctName, 1, 3);
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

		add(new Label("Product Price: "), 0, 4);
		add(txtFldPrdctPrice, 1, 4);
		// Switch to the next txtField after pressing Enter.
		txtFldPrdctPrice.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				cboxPrdctBarCode.requestFocus();
				ev.consume();
			}
		});
	}

	private void initFldBarcode() {
		cboxPrdctBarCode = new ComboBox<String>();
		cboxPrdctBarCode.setOnMouseClicked(e -> updateStatus("", "black"));
		cboxPrdctBarCode.getSelectionModel().select("");
		cboxPrdctBarCode.setEditable(true);
		cboxPrdctBarCode.setPromptText("Barcode");

		add(cboxPrdctBarCode, 1, 5);
		add(new Label("Product Barcode: "), 0, 5);

		view.fireListOfProducts();

		cboxPrdctBarCode.setOnAction(e -> {
			String selectedValue = (String) cboxPrdctBarCode.getValue();
			view.fireSearchProduct(selectedValue);
		});

		cboxPrdctBarCode.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				txtFldPrdctPriceToStore.requestFocus();
				ev.consume();
			}
		});
	}

	public void updateComboBox(Set<Entry<String, Product>> products) {
		for (Map.Entry<String, Product> e : products) {
			if (!cboxPrdctBarCode.getItems().contains(e.getKey()))
				cboxPrdctBarCode.getItems().add(e.getKey());
		}
	}

	public void setFields(Product productDetails) {
		txtFldPrdctName.setText(productDetails.getDescription());
		txtFldPrdctPrice.setText(String.valueOf((productDetails.getPriceSold())));
		txtFldPrdctPriceToStore.setText(String.valueOf((productDetails.getCostToStore())));
		txtFldCustomer.setText(productDetails.getCustomer().getName());
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
				txtFldCustomer.requestFocus();
				ev.consume();
			}
		});
	}

	private void initFldCustomer() {
		txtFldCustomer = new TextField();
		txtFldCustomer.setOnMouseClicked(e -> updateStatus("", "black"));
		txtFldCustomer.setPromptText("Customer");

		add(new Label("Product's Customer: "), 0, 7);
		add(txtFldCustomer, 1, 7);
		// Switch to the next txtField after pressing Enter.
		txtFldCustomer.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				btnAdd.fire();
				ev.consume();
			}
		});
	}
	
	private void initClearButton() {
		btnClear = new Button("Clear Product");
		btnClear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
		btnClear.setOnAction(e -> {
			cleanValueFields();
		});
		add(btnClear, 1, 10);
	}
	
	private void initAddButton() {
		btnAdd = new Button("Add Product");
		btnAdd.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
		btnAdd.setOnAction(e -> {
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
			id = id.equals("") ? "0000" : id;

			// Product's Customer who bought it.
			Customer c = new Customer(txtFldCustomer.getText());
			view.fireAddNewProduct(new Product(description, priceToStore, priceSold, c, id));

			txtFldPrdctName.requestFocus();
			view.fireListOfProducts();
		});
		add(btnAdd, 1, 9);
	}

	// init status
	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 8);
		add(lblStatus, 1, 8, 4, 1);
	}

	// update status
	public void updateStatus(String status, String color) {
		lblStatus.setText(status);
		lblStatus.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
	}

	// Setters & Getters.
	public TextField getTxtFldPrdctName() {
		return txtFldPrdctName;
	}

	public void setTxtFldPrdctName(TextField txtFldPrdctName) {
		this.txtFldPrdctName = txtFldPrdctName;
	}

	public TextField getTxtFldPrdctPrice() {
		return txtFldPrdctPrice;
	}

	public void setTxtFldPrdctPrice(TextField txtFldPrdctPrice) {
		this.txtFldPrdctPrice = txtFldPrdctPrice;
	}

	public ComboBox<String> getCboxPrdctBarCode() {
		return cboxPrdctBarCode;
	}

	public void setCboxPrdctBarCode(ComboBox<String> cboxPrdctBarCode) {
		this.cboxPrdctBarCode = cboxPrdctBarCode;
	}

	public TextField getTxtFldPrdctPriceToStore() {
		return txtFldPrdctPriceToStore;
	}

	public void setTxtFldPrdctPriceToStore(TextField txtFldPrdctPriceToStore) {
		this.txtFldPrdctPriceToStore = txtFldPrdctPriceToStore;
	}

	public TextField getTxtFldCustomer() {
		return txtFldCustomer;
	}

	public void setTxtFldCustomer(TextField txtFldCustomer) {
		this.txtFldCustomer = txtFldCustomer;
	}

	public Label getLblStatus() {
		return lblStatus;
	}

	public void setLblStatus(Label lblStatus) {
		this.lblStatus = lblStatus;
	}
	// END Setters & Getters.

	// clean Value Fields
	public void cleanValueFields() {
		txtFldCustomer.setText("");
		cboxPrdctBarCode.setValue("");
		txtFldPrdctName.setText("");
		txtFldPrdctPriceToStore.setText("");
		txtFldPrdctPrice.setText("");
	}
}
