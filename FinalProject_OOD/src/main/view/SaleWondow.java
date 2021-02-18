package main.view;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import main.model.Product;

public class SaleWondow extends GridPane {

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
	/* Customer */
	private TextField txtFldCustomer;
	/* Customer's Phone */
	private TextField txtFldCustomerPhone;
	/* Promotion Customer */
	private CheckBox checkBoxPromotion;
	/* Status */
	private Label lblStatus;
	/* Add button */
	private Button btnAdd;
	/* Clear button */
	private Button btnClear;
	/* Undo button */
	private Button btnUndo;

	private Stage stage;

	public SaleWondow(Stage stg, View view) {
		super();
		this.stage = stg;
		this.view = view;

		init();
		this.stage.setScene(new Scene(this, 500, 500));
		this.stage.setTitle("Store Saver");
		this.stage.show();
	}

	private void init() {
		initRoot();
		initTitle();
		initStatus();
		initSaleView();
	}

	private void initSaleView() {
		initFldBarcode();
		
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
		Text title = new Text("S A l E ! !");
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

	// init status
	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 10);
		add(lblStatus, 1, 10, 4, 1);
	}

	// update status
	public void updateStatus(String status, String color) {
		lblStatus.setText(status);
		lblStatus.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
	}

	private void initFldBarcode() {
		cboxPrdctBarCode = new ComboBox<String>();
		cboxPrdctBarCode.setOnMouseClicked(e -> updateStatus("", "black"));
		cboxPrdctBarCode.getSelectionModel().select(" ");
		cboxPrdctBarCode.setEditable(true);
		cboxPrdctBarCode.setPromptText("Barcode");

		add(cboxPrdctBarCode, 1, 3);
		add(new Label("Product Barcode: "), 0, 3);

		view.fireListOfProducts();

		cboxPrdctBarCode.setOnAction(e -> {
			String selectedValue = (String) cboxPrdctBarCode.getValue();

			if (selectedValue != null) {// && isAddressingModel == true
				view.fireSearchProduct(selectedValue);
			}
//				isAddressingModel = true; // don't go to model for searching empty product
		});

		cboxPrdctBarCode.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				txtFldPrdctName.requestFocus();
				ev.consume();
			}
		});
	}

	public void updateComboBox(Set<Entry<String, Product>> products) {
		cboxPrdctBarCode.getItems().clear(); // SetOnAction->FireSearchProducct (AVOID ME!)

//			isAddressingModel = true; // don't go to model for searching empty product
		for (Map.Entry<String, Product> e : products) {
			cboxPrdctBarCode.getItems().add(e.getKey());
		}
	}
	private void initClearButton() {
		btnClear = new Button("Clear Product");
		btnClear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnClear.setOnAction(e -> {
			cleanValueFields();
		});
		add(btnClear, 1, 12);
	}

	private void initUndoButton() {
		btnUndo = new Button("Undo");
		btnUndo.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnUndo.setOnAction(e -> {
			view.fireUndo();
		});
		add(btnUndo, 1, 13);
	}

	// clean Value Fields
	public void cleanValueFields() {
		txtFldCustomer.setText("");
		cboxPrdctBarCode.setValue("");
		txtFldPrdctName.setText("");
		txtFldPrdctPriceToStore.setText("");
		txtFldPrdctPrice.setText("");
		txtFldCustomerPhone.setText("");
		checkBoxPromotion.setSelected(false);
	}
}
