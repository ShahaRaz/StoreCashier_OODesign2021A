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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.Customer;
import main.model.Product;
import main.model.store.Store;

public class ProductTableView extends GridPane {
	private Button btnUndo;
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
		initUndoButton();
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
			view.fireUndo();
		});
		setHalignment(btnUndo, HPos.CENTER);
		add(btnUndo, 0, 6, 5, 1);
	}

	private void initTableProducts() {
		initTable();
		stage.show();
	}

	public void updateTable(Set<Map.Entry<String, Product>> products) {
		data.clear();
		for (Map.Entry<String, Product> e : products) {
//			data.addAll(e.getValue());
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

//		view.fireListOfProducts();
		add(table, 0, 3, 5, 1);
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
