package main.view;
/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import main.model.Customer;
import main.model.Product;

public class AddProductView extends GridPane {

	// Variables
	private static final String TAG = "AddProductView";
	/** Boolean attribute for ComboBox.SenOnAction */
	protected boolean isAddressingModel = true;
	/** Boolean attribute for UpdateFields method */
	protected boolean isAddWindowSent;

	private View view;
	/** ProductName */
	private TextField txtFldPrdctName;
	/** Price */
	private TextField txtFldPrdctPrice;
	/** Barcode */
	private ComboBox<String> cboxPrdctBarCode;
	/** PriceToStore */
	private TextField txtFldPrdctPriceToStore;
	/** Customer */
	private TextField txtFldCustomer;
	/** Customer's Phone */
	private TextField txtFldCustomerPhone;
	/** Promotion Customer */
	private CheckBox checkBoxPromotion;
	/** Status */
	private Label lblStatus;
	/** Add button */
	private Button btnAdd;
	/** Clear button */
	private Button btnClear;
	/** Undo button */
	private Button btnUndo;

	public AddProductView(View view) {
		this.view = view;
		init();
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
		setHalignment(title, HPos.CENTER);
		Label lblTitle = new Label("ENTER Product TO ADD:");
		lblTitle.setStyle("-fx-text-alignment: center;-fx-text-fill: black;-fx-font-weight: bold");
		setHalignment(lblTitle, HPos.CENTER);
		add(title, 0, 0, 5, 1);
		add(lblTitle, 0, 1, 5, 1);
	}

	private void initAddProduct() {
		initFldBarcode();
		initFldName();
		initFldPrice();
		initFldPriceToStore();
		initFldCustomer();
		initFldCustomerPhone();
		initCheckBox();
		initAddButton();
		initClearButton();
		initUndoButton();
	}


	/** Initial TextField product's Name. */
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

	/** Initial TextField product's Price. */
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

	/** Initial TextField product's Barcode. */
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
				isAddWindowSent = true;
				view.fireSearchProduct(selectedValue);
				isAddWindowSent = false;
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

	/** Update the products list's ComboBox. */
	public void updateComboBox(Set<Entry<String, Product>> products) {
		cboxPrdctBarCode.getItems().clear(); // SetOnAction->FireSearchProducct (AVOID ME!)

		for (Map.Entry<String, Product> e : products) {
			cboxPrdctBarCode.getItems().add(e.getKey());
		}
	}

	/**
	 * Setting the Fields with the right data.
	 * 
	 * @param productDetails - The selected product from the ComboBox.
	 */
	public void setFields(Product productDetails) {
		/* Product's Fields */
		txtFldPrdctName.setText(productDetails.getDescription());
		txtFldPrdctPrice.setText(String.valueOf((productDetails.getPriceSold())));
		txtFldPrdctPriceToStore.setText(String.valueOf((productDetails.getCostToStore())));
		/* Customer's Fields */
		txtFldCustomer.setText(productDetails.getCustomer().getName());
		txtFldCustomerPhone.setText(productDetails.getCustomer().getMobileNumber());
		checkBoxPromotion.setSelected(productDetails.getCustomer().getIsAcceptingPromotions());
	}

	/** Initial TextField product's PriceToStore. */
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

	/** Initial TextField product's Customer. */
	private void initFldCustomer() {
		txtFldCustomer = new TextField();
		txtFldCustomer.setOnMouseClicked(e -> updateStatus("", "black"));
		txtFldCustomer.setPromptText("Customer");

		add(new Label("Product's Customer: "), 0, 7);
		add(txtFldCustomer, 1, 7);
		// Switch to the next txtField after pressing Enter.
		txtFldCustomer.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				txtFldCustomerPhone.requestFocus();
				ev.consume();
			}
		});
	}

	/** Initial TextField product's CustomerPhone. */
	private void initFldCustomerPhone() {
		txtFldCustomerPhone = new TextField();
		txtFldCustomerPhone.setOnMouseClicked(e -> updateStatus("", "black"));
		txtFldCustomerPhone.setPromptText("Customer Phone");

		add(new Label("Customer's Phone: "), 0, 8);
		add(txtFldCustomerPhone, 1, 8);
		// Switch to the next txtField after pressing Enter.
		txtFldCustomerPhone.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				checkBoxPromotion.requestFocus();
				ev.consume();
			}
		});
	}

	/** Initial CheckBox product's CustomerIsPromoted. */
	private void initCheckBox() {
		checkBoxPromotion = new CheckBox();
		add(new Label("Promotion: "), 0, 9);
		add(checkBoxPromotion, 1, 9);
		checkBoxPromotion.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (checkBoxPromotion.isSelected())
					checkBoxPromotion.setSelected(false);
				else
					checkBoxPromotion.setSelected(true);
				e.consume();
			}
		});
	}

	/** Initial Clear Button to clean all fields */
	private void initClearButton() {
		btnClear = new Button("Clear Product");
		btnClear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnClear.setOnAction(e -> {
			cleanValueFields();
		});
		add(btnClear, 1, 13);
	}

	/** Initial Undo Button to undo last action */
	private void initUndoButton() {
		btnUndo = new Button("Undo");
		btnUndo.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnUndo.setOnAction(e -> {
			isAddWindowSent = true;
			view.fireUndo();
			isAddWindowSent = false;
		});
		add(btnUndo, 1, 11);
	}

	/** Initial Add Button to add a new \ update product */
	private void initAddButton() {
		btnAdd = new Button("Add Product");
		btnAdd.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnAdd.setOnAction(e -> {
			// Store the data.
			String str_Error = "";
			// Product's Description
			String description = txtFldPrdctName.getText();
			description = description.equals("") ? "NA" : description;

			// Product's price to store
			String priceToStoreStr = txtFldPrdctPriceToStore.getText();
			int priceToStore = 0;
			try {
				priceToStore = Integer.parseInt(priceToStoreStr.equals("") ? "0" : priceToStoreStr);
			} catch (Exception e1) {
				str_Error = "Overing invalid value priceToStore";
			}

			// Product's price sold
			String priceSoldStr = txtFldPrdctPrice.getText();
			int priceSold = 0;
			try {
				priceSold = Integer.parseInt(priceSoldStr.equals("") ? "0" : priceSoldStr);
			} catch (Exception e2) {
				str_Error = "Overing invalid value priceSold";
			}

			// Product's id
			String id = cboxPrdctBarCode.getValue();
			try {
				id = id.equals("") ? null : id;
			} catch (Exception e2) {
				str_Error = "Overing invalid value Barcode";
			}

			// Product's Customer who bought it.
			String custName = txtFldCustomer.getText();
			try {
				custName = custName.equals("") ? "_Customer_" : custName;
			} catch (Exception e2) {
				str_Error = "Overing invalid value Customer's Name";
			}

			String custPhone = txtFldCustomerPhone.getText();
			try {
				custPhone = custPhone.equals("") ? "000-0000000" : custPhone;
			} catch (Exception e2) {
				str_Error = "Overing invalid value Phone";
			}

			if (!str_Error.isEmpty())
				updateStatus(str_Error, "red");
			
			Customer c = new Customer(custName, custPhone, checkBoxPromotion.isSelected());
			isAddWindowSent = true;
			view.fireAddNewProduct(new Product(description, priceToStore, priceSold, c, id));
			isAddWindowSent = false;
			isAddressingModel = false;

			cboxPrdctBarCode.requestFocus();
			cleanValueFields();
		});
		add(btnAdd, 1, 12);
	}

	/** initial status */
	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 10);
		add(lblStatus, 1, 10, 4, 1);
	}

	/** update status */
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

	/** clean Value Fields */
	public void cleanValueFields() {
		isAddressingModel = false;
		txtFldCustomer.setText("");
		cboxPrdctBarCode.setValue("");
		txtFldPrdctName.setText("");
		txtFldPrdctPriceToStore.setText("");
		txtFldPrdctPrice.setText("");
		txtFldCustomerPhone.setText("");
		checkBoxPromotion.setSelected(false);
		isAddressingModel = true;
	}
}
