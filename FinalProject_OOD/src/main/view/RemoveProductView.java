package main.view;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
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
import main.model.Product;

public class RemoveProductView extends GridPane {
	private View view;
	/* Barcode */
	private ComboBox<String> cboxPrdctBarCode;
	/* Remove button */
	private Button btnRemove;	
	/* Undo button */
	private Button btnUndo;
	/* Status */
	private Label lblStatus;

	private Stage stage;

	public RemoveProductView(Stage stg, View view) {
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
		initRemoveProduct();
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
		Text title = new Text("Remove Product");
		title.setFont(Font.font("ariel", FontWeight.BOLD, 25));
		title.setFill(Color.RED);
		title.setEffect(r);
		GridPane.setHalignment(title, HPos.CENTER);
		Label lblTitle = new Label("ENTER Product TO REMOVE:");
		lblTitle.setStyle("-fx-text-alignment: center;-fx-text-fill: black;-fx-font-weight: bold");
		GridPane.setHalignment(lblTitle, HPos.CENTER);
		add(title, 0, 0, 5, 1);
		add(lblTitle, 0, 1, 5, 1);
	}

	private void initRemoveProduct() {
		initFldBarcode();
		initRemoveButton();
		initUndoButton();
	}

	// init Text Field.
	private void initFldBarcode() {
		cboxPrdctBarCode = new ComboBox<String>();
		cboxPrdctBarCode.setOnMouseClicked(e -> updateStatus("", "black"));
		cboxPrdctBarCode.getSelectionModel().select("");
		cboxPrdctBarCode.setEditable(true);
		cboxPrdctBarCode.setPromptText("Barcode");

		add(cboxPrdctBarCode, 1, 5);
		add(new Label("Product Barcode: "), 0, 5);

		view.fireListOfProducts();

		// Make the Enter available from the txt field
		cboxPrdctBarCode.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				btnRemove.fire();
				ev.consume();
			}
		});
	}

	public void updateComboBox(Set<Entry<String, Product>> products) {

		cboxPrdctBarCode.getItems().clear();

		for (Map.Entry<String, Product> e : products) {
			cboxPrdctBarCode.getItems().add(e.getKey());
		}
	}

	private void initUndoButton() {
		btnUndo = new Button("Undo");
		btnUndo.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnUndo.setOnAction(e -> {
			view.fireUndo();
		});
		add(btnUndo, 1, 10);
	}

	// init Button.
	private void initRemoveButton() {
		btnRemove = new Button("Remove Product");
		btnRemove.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnRemove.setOnAction(e -> {
			String pID = cboxPrdctBarCode.getValue();
			view.fireRemoveProduct(new Product(pID));
			
			cleanValueFields();
			cboxPrdctBarCode.requestFocus();
		});
		add(btnRemove, 1, 9);
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
	public ComboBox<String> getCboxPrdctBarCode() {
		return cboxPrdctBarCode;
	}

	public void setCboxPrdctBarCode(ComboBox<String> cboxPrdctBarCode) {
		this.cboxPrdctBarCode = cboxPrdctBarCode;
	}

	public Button getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(Button btnRemove) {
		this.btnRemove = btnRemove;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
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
		cboxPrdctBarCode.setValue("");
	}
}
