package main.view;

/*
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 * */
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddProductView extends GridPane {

//	  	Variables

	/* ProductName */
	private TextField txtFldPrdctName;
	/* Price */
	private TextField txtFldPrdctPrice;
	/* Barcode */
	private TextField txtFldPrdctBarCode;
	/* PriceToStore */
	private TextField txtFldPrdctPriceToStore;
	/* Customer */
	private TextField txtFldCustomer;
	/* Add button */
	private Button btnAdd;

	private Stage stage;
	private Scene baseScene;

	public AddProductView(Stage stg) {
		this.stage = stg;
		baseScene = stg.getScene();

		init();
		stage.setScene(new Scene(this, 500, 500));
		stage.setTitle("Store Saver");
		stage.show();
	}

	private void init() {
		initRoot();
		initTitle();
		initAddProduct();
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
		txtFldPrdctName = new TextField();
		add(new Label("Product Name: "), 0, 3);
		add(txtFldPrdctName, 1, 3);

		txtFldPrdctPrice = new TextField();
		add(new Label("Product Price: "), 0, 4);
		add(txtFldPrdctPrice, 1, 4);

		txtFldPrdctBarCode = new TextField();
		add(new Label("Product Barcode: "), 0, 5);
		add(txtFldPrdctBarCode, 1, 5);

		txtFldPrdctPriceToStore = new TextField();
		add(new Label("Store Price: "), 0, 6);
		add(txtFldPrdctPriceToStore, 1, 6);

		txtFldCustomer = new TextField();
		add(new Label("Product's Customer: "), 0, 7);
		add(txtFldCustomer, 1, 7);

		btnAdd = new Button("Add Product");
		btnAdd.setOnAction(e -> {
			stage.setScene(baseScene);
		});
		add(btnAdd, 1, 9);
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

	public TextField getTxtFldPrdctBarCode() {
		return txtFldPrdctBarCode;
	}

	public void setTxtFldPrdctBarCode(TextField txtFldPrdctBarCode) {
		this.txtFldPrdctBarCode = txtFldPrdctBarCode;
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

	// END Setters & Getters.

}
