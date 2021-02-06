package main.view;

import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.Product;

public class ProductTableView extends GridPane {
	private ListView<String> list = new ListView<String>();
	private VBox box;

	/* Status */
	private Label lblStatus;

	private Stage stage;
	private Scene scene;

	public ProductTableView(Stage stg) {
		this.stage = stg;

		init();
	}

	private void init() {
		initRoot();
		initTitle();
		initStatus();

//		initTableProducts();
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

	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 8);
		add(lblStatus, 1, 8, 4, 1);
	}

	private void initTableProducts() {
		scene = new Scene(new Group());
		box = new VBox();

		final Label label = new Label("Products Details");
		label.setFont(new Font("Arial", 20));

		stage.setTitle("ListViewSample");
		box.getChildren().addAll(list, label);
		VBox.setVgrow(list, Priority.ALWAYS);

		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				label.setText(new_val);
				label.setTextFill(Color.web(new_val));
			}
		});

		stage.show();
	}

//	public void createTable() {
//
//		final ObservableList<Product> data = FXCollections.observableArrayList(
//				new Product("Bamba", 1, 3, null, "123456"), new Product("Bisli", 1, 3, null, "1234567"),
//				new Product("Apropo", 1, 3, null, "12345678"), new Product("Momo", 1, 3, null, "123456789"));
//
//		TableView table = new TableView();
//		Scene scene1 = new Scene(new Group());
//
//		stage.setTitle("Product's Table");
//
//		TableColumn firstNameCol = new TableColumn("Description");
//		firstNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
//		
//		TableColumn lastNameCol = new TableColumn("Barcode");
//		lastNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("pID"));
//		
//		TableColumn emailCol = new TableColumn("Price");
//		emailCol.setCellValueFactory(new PropertyValueFactory<Product, String>("priceSold"));
//
//		table.setItems(data);
//		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
//
//		final VBox vbox = new VBox();
//		vbox.setSpacing(5);
//		vbox.setPadding(new Insets(10, 0, 0, 10));
//		vbox.getChildren().addAll(table);
//
//		((Group) scene1.getRoot()).getChildren().addAll(vbox);
//
//		stage.setScene(scene1);
//		stage.show();
//	}

}
