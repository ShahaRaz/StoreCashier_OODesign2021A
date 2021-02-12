package main.view;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private TextField txtFldPrdctBarCode;
	/* Remove button */
	private Button btnRemove;
	/* Status */
	private Label lblStatus;

	private Stage stage;
//	private Scene baseScene;

	public RemoveProductView(Stage stg, View view) {
		this.stage = stg;
		this.view = view;
//		baseScene = stg.getScene();

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
		initFldPrdctBarcode();
		initRemoveButton();
	}

	// init Text Field.
	private void initFldPrdctBarcode() {
		txtFldPrdctBarCode = new TextField();
		txtFldPrdctBarCode.setOnMouseClicked(e -> updateStatus("", "black"));
		add(new Label("Product Barcode: "), 0, 3);
		add(txtFldPrdctBarCode, 1, 3);

		// Make the Enter available from the txt field
		txtFldPrdctBarCode.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				btnRemove.fire();
				ev.consume();
			}
		});
	}

	// init Button.
	private void initRemoveButton() {
		btnRemove = new Button("Remove Product");
		btnRemove.setOnAction(e -> {
			String pID = getTxtFldPrdctBarCode().getText();
			view.fireRemoveProduct(new Product(pID));
			cleanValueFields();
			txtFldPrdctBarCode.requestFocus();
			view.getTableView().updateTable();
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
	public TextField getTxtFldPrdctBarCode() {
		return txtFldPrdctBarCode;
	}

	public void setTxtFldPrdctBarCode(TextField txtFldPrdctBarCode) {
		this.txtFldPrdctBarCode = txtFldPrdctBarCode;
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
		txtFldPrdctBarCode.setText("");
	}
}
