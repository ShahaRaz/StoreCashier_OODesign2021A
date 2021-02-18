package main.view;
/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.Product;

public class ProductTableView extends GridPane {
	protected boolean isAddWindowSent;
	private HBox hbButtons;

	private Button btnUndo;
	private Button btnSave;
	private Button btnRevers;
	/* Status */
	private Label lblStatus;
	
	private Stage stage;
	private View view;
	private final ObservableList<DisplayableProduct> data = FXCollections.observableArrayList();

	public ProductTableView(Stage stg, View view) {
		this.stage = stg;
		this.view = view;
		init();
	}

	private void init() {
		initRoot();
		initTitle();
		initTableProducts();
		initHbox();
	}

	private void initHbox() {
		hbButtons = getHBox();
		initUndoButton();
		initSavaButton();
		initReversButton();
		hbButtons.getChildren().addAll(btnSave, btnUndo, btnRevers);
		add(hbButtons, 0, 6);
	}

	// get new styled hbox
	private HBox getHBox() {
		HBox hBox = new HBox(5);
		hBox.setMinSize(300, 50);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setAlignment(Pos.CENTER);
		return hBox;
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
		Text title = new Text("Table of all Products");
		title.setFont(Font.font("ariel", FontWeight.BOLD, 25));
		title.setFill(Color.RED);
		title.setEffect(r);
		setHalignment(title, HPos.CENTER);
		add(title, 0, 0, 5, 1);
	}

	private void initUndoButton() {
		btnUndo = new Button("Undo");
		btnUndo.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnUndo.setOnAction(e -> {
			// TODO: change from undo to memento.
			isAddWindowSent = true;
			view.fireUndo();
			isAddWindowSent = false;
		});
//		setHalignment(btnUndo, HPos.CENTER);
//		add(btnUndo, 0, 6, 5, 1);
	}

	private void initSavaButton() {
		btnSave = new Button("Save");
		btnSave.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnSave.setOnAction(e -> {
			// TODO: change from undo to memento.
			isAddWindowSent = true;
			view.fireSave();
			isAddWindowSent = false;
		});
//		setHalignment(btnSave, HPos.CENTER);
//		add(btnSave, 0, 6, 5, 1);
	}

	private void initReversButton() {
		btnRevers = new Button("Revers");
		btnRevers.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnRevers.setOnAction(e -> {
			// TODO: change from undo to memento.
			isAddWindowSent = true;
			view.fireRevers();
			isAddWindowSent = false;
		});
//		setHalignment(btnRevers, HPos.CENTER);
//		add(btnRevers, 0, 6, 5, 1);
	}

	private void initTableProducts() {
		initTable();
		initStatus();
		stage.show();
	}

	public void updateTable(Set<Map.Entry<String, Product>> products) {
		data.clear();
		for (Map.Entry<String, Product> e : products) {
			DisplayableProduct tmp = new DisplayableProduct(e.getValue()); // here we changed
			data.addAll(tmp);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initTable() {
		view.fireListOfProducts();

		TableView table = new TableView();

		stage.setTitle("Product's Table");
		TableColumn prodctNameCol = new TableColumn("Description");
		prodctNameCol.setCellValueFactory(new PropertyValueFactory("description"));

		TableColumn barcodeCol = new TableColumn("Barcode");
		barcodeCol.setCellValueFactory(new PropertyValueFactory("barcode"));

		TableColumn storePrice = new TableColumn("Store Price");
		storePrice.setCellValueFactory(new PropertyValueFactory<DisplayableProduct, Integer>("costToStore"));
		TableColumn customerPrice = new TableColumn("Customer price");
		customerPrice.setCellValueFactory(new PropertyValueFactory<DisplayableProduct, Integer>("priceSold"));

		TableColumn priceCol = new TableColumn("Price");
		priceCol.getColumns().addAll(storePrice, customerPrice);

		table.setItems(data);
		table.getColumns().addAll(prodctNameCol, barcodeCol, priceCol);

		view.fireListOfProducts();
		add(table, 0, 3, 5, 1);
	}

	// init status
	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 4);
		add(lblStatus, 0, 4);
		setHalignment(lblStatus, HPos.CENTER);
	}

	// update status
	public void updateStatus(String status, String color) {
		lblStatus.setText(status);
		lblStatus.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
	}
}

// Hard Code. will be from File.
//Store.getInstance(null)
//		.addNewProduct(new Product("Cola", 1, 4, new Customer("Mama", "054789654", false), "Co7736"));
//Store.getInstance(null)
//		.addNewProduct(new Product("Sprite", 12, 16, new Customer("Lili", "0524756987", false), "Sp9187"));
//Store.getInstance(null)
//		.addNewProduct(new Product("Nestea", 8, 10, new Customer("Gaga", "0549512365", true), "Ne1658"));
//Store.getInstance(null)
//		.addNewProduct(new Product("Milk", 1, 3, new Customer("Lolo", "0541236549", false), "Mi982"));
// TODO: Change HardCode to read from File.
