package main.view;
/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

import java.util.Map;
import java.util.SortedMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import main.model.Store;

public class ProductTableView extends GridPane {
	private Stage stage;

	public ProductTableView(Stage stg) {
		this.stage = stg;
		init();
	}

	private void init() {
		initRoot();
		initTitle();
		initTableProducts();
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
		Text title = new Text("Table of all Products");
		title.setFont(Font.font("ariel", FontWeight.BOLD, 25));
		title.setFill(Color.RED);
		title.setEffect(r);
		GridPane.setHalignment(title, HPos.CENTER);
		add(title, 0, 0, 5, 1);
	}

	private void initTableProducts() {
		createTable();

//		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
//				label.setText(new_val);
//				label.setTextFill(Color.web(new_val));
//			}
//		});

		stage.show();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createTable() {

		final ObservableList<Product> data = FXCollections.observableArrayList(
				new Product("Bamba", 1, 4, new Customer("Momo"), "BA536"),
				new Product("Bisli", 12, 16, new Customer("Lulu"), "BI987"),
				new Product("Apropo", 8, 10, new Customer("Gogo"), "AP3658"),
				new Product("Banana", 1, 3, new Customer("Bilbi"), "BA4862"));

		Store.getInstance().addNewProduct(new Product("Cola", 1, 4, new Customer("Mama"), "Co7736"));
		Store.getInstance().addNewProduct(new Product("Sprite", 12, 16, new Customer("Lili"), "Sp9187"));
		Store.getInstance().addNewProduct(new Product("Nestea", 8, 10, new Customer("Gaga"), "Ne1658"));
		Store.getInstance().addNewProduct(new Product("Milk", 1, 3, new Customer("Lolo"), "Mi982"));

		for (Map.Entry<String, Product> e : Store.getInstance().getProductsMap().entrySet()) {
			data.add(e.getValue());
		}
		TableView table = new TableView();

		stage.setTitle("Product's Table");

		TableColumn prodctNameCol = new TableColumn("Description");
		prodctNameCol.setCellValueFactory(new PropertyValueFactory("description"));

		TableColumn barcodeCol = new TableColumn("Barcode");
		barcodeCol.setCellValueFactory(new PropertyValueFactory("barcode"));

		TableColumn priceCol = new TableColumn("Price");
		TableColumn storePrice = new TableColumn("Store Price");
		TableColumn customerPrice = new TableColumn("Customer price");
		priceCol.getColumns().addAll(storePrice, customerPrice);
		storePrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("costToStore"));
		customerPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("priceSold"));

		table.setItems(data);
		table.getColumns().addAll(prodctNameCol, barcodeCol, priceCol);

		add(table, 0, 3, 5, 1);
	}
}
