package main.view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.model.Product;

public class SaleWondow extends GridPane {

	// Variables

	/** Boolean attribute for ComboBox.SenOnAction */
	protected boolean isAddressingModel = true;
	/** Boolean attribute for UpdateFields method */
	protected boolean isSaleWindowSent;
	private View view;
	/** Barcode */
	private ComboBox<String> cboxPrdctBarCode;
	/** Status */
	private Label lblStatus;
	/** Add button */
	private Button btnAddSale;
	/** Clear button */
	private Button btnClear;

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
		initAddButton();
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
				isSaleWindowSent = true;
				view.fireSearchProduct(selectedValue);
				isSaleWindowSent = false;
			}
			isAddressingModel = true; // don't go to model for searching empty product
		});
	}

	/** initial status */
	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 7);
		add(lblStatus, 1, 7, 4, 1);
	}

	/** update status */
	public void updateStatus(String status, String color) {
		lblStatus.setText(status);
		lblStatus.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
	}

	/** Update the products list's ComboBox. */
	public void updateComboBox(Set<Entry<String, Product>> products) {
		cboxPrdctBarCode.getItems().clear(); // SetOnAction->FireSearchProducct (AVOID ME!)

		for (Map.Entry<String, Product> e : products) {
			cboxPrdctBarCode.getItems().add(e.getKey());
		}
	}

	/** Initial Clear Button to clean all fields */
	private void initClearButton() {
		btnClear = new Button("Clear Product");
		btnClear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnClear.setOnAction(e -> {
			cleanValueFields();
		});
		add(btnClear, 1, 11);
	}

	/** Initial Add Button to add a new \ update Sale */
	private void initAddButton() {
		btnAddSale = new Button("Add Sale");
		btnAddSale.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnAddSale.setOnAction(e -> {
			// Store the data.
			String str_Error = "";

			// Product's id
			String id = cboxPrdctBarCode.getValue();
			try {
				id = id.equals("") ? null : id;
			} catch (Exception e2) {
				str_Error = "Overing invalid value Bercode";
			}

			// Product's Customer who bought it.
			if (!str_Error.isEmpty())
				updateStatus(str_Error, "red");

			isSaleWindowSent = true;
			view.fireSale(new Product(null, 0, 0, null, id));
			isSaleWindowSent = false;
			isAddressingModel = false;

			cboxPrdctBarCode.requestFocus();
			cleanValueFields();
		});
		add(btnAddSale, 1, 10);
	}

	/** clean Value Fields */
	public void cleanValueFields() {
		isAddressingModel = false;
		cboxPrdctBarCode.setValue("");
		isAddressingModel = true;
	}
}
