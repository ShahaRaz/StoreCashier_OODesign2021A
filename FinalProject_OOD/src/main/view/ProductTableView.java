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
import main.model.Product;

public class ProductTableView extends GridPane {
	/** Boolean attribute for UpdateFields method */
	protected boolean isAddWindowSent;
	/** HBox */
	private HBox hbButtons;
	/** Buttons */
	private Button btnUndo;
	private Button btnSave;
	private Button btnReverse;
	/** Status */
	private Label lblStatus;
	private Label lblProfit;
	/** View */
	private View view;
	/** List of products */
	private final ObservableList<DisplayableProduct> data = FXCollections.observableArrayList();

	private int profit;

	public ProductTableView(View view) {
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
		initReverseButton();
		hbButtons.getChildren().addAll(btnSave, btnUndo, btnReverse);

		add(hbButtons, 1, 6);
	}

	// Get new styled HBox
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

	/** Initial Undo Button to undo last action */
	private void initUndoButton() {
		btnUndo = new Button("Undo");
		btnUndo.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnUndo.setOnAction(e -> {
			isAddWindowSent = true;
			view.fireUndo();
			isAddWindowSent = false;
		});
	}

	/** Initial Save Button to save current state */
	private void initSavaButton() {
		btnSave = new Button("Save");
		btnSave.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnSave.setOnAction(e -> {
			isAddWindowSent = true;
			view.fireSave();
			isAddWindowSent = false;
		});
	}

	/** Initial Reverse Button to return last state */
	private void initReverseButton() {
		btnReverse = new Button("Revers");
		btnReverse.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		btnReverse.setOnAction(e -> {
			isAddWindowSent = true;
			view.fireReverse();
			isAddWindowSent = false;
		});
	}

	private void initTableProducts() {
		initTable();
		initStatus();
		initprofit();
	}

	/**
	 * Update the table after editing the data
	 * 
	 * @param products - Map of all products
	 */
	public void updateTable(Set<Map.Entry<String, Product>> products) {
		data.clear();
		profit = 0;

		for (Map.Entry<String, Product> e : products) {
			DisplayableProduct tmp = new DisplayableProduct(e.getValue()); // here we changed
			profit += tmp.getProfit();
			data.addAll(tmp);
		}
		updateProfit("The profit is: " + getProfit() + "$", "green");
	}

	/** initial TableView */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initTable() {
		view.fireListOfProducts();

		TableView table = new TableView();

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

		TableColumn profit = new TableColumn("Profit");
		profit.setCellValueFactory(new PropertyValueFactory<DisplayableProduct, Integer>("profit"));

		table.setItems(data);
		table.getColumns().addAll(prodctNameCol, barcodeCol, priceCol, profit);

		view.fireListOfProducts();
		add(table, 0, 3, 5, 1);
		setHalignment(table, HPos.CENTER);
	}

	/** initial status label */
	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 4);
		add(lblStatus, 1, 4);
		setHalignment(lblStatus, HPos.CENTER);
	}

	/** initial profit label */
	private void initprofit() {
		lblProfit = new Label();
		add(new Label("Profit: "), 0, 5);
		add(lblProfit, 1, 5);
		setHalignment(lblProfit, HPos.CENTER);
	}

	/** update status */
	public void updateStatus(String status, String color) {
		lblStatus.setText(status);
		lblStatus.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
	}

	/** update profit */
	public void updateProfit(String status, String color) {
		lblProfit.setText(status);
		lblProfit.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
	}

	public int getProfit() {
		return profit;
	}
}