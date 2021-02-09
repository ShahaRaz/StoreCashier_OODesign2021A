package main.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.Customer;
import main.model.Product;

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

	public void createTable() {

		final ObservableList<Product> data = FXCollections.observableArrayList(
				new Product("Bamba", 1, 3, new Customer("Momo"), "123456"),
				new Product("Bisli", 1, 3, new Customer("Lulu"), "1234567"),
				new Product("Apropo", 1, 3, new Customer("Gogo"), "12345678"),
				new Product("Banana", 1, 3, new Customer("Bilbi"), "123456789"));

		TableView table = new TableView();

		stage.setTitle("Product's Table");

		TableColumn firstNameCol = new TableColumn("Description");
//		firstNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));

		TableColumn lastNameCol = new TableColumn("Barcode");
//		lastNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("pID"));

		TableColumn emailCol = new TableColumn("Price");
//		emailCol.setCellValueFactory(new PropertyValueFactory<Product, String>("priceSold"));

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

		add(table, 0, 3, 5, 1);
	}
}
