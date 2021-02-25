package main.view;
/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.interfaces.saleEventListener;
import main.model.Customer;
import main.model.Product;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Table_CostumersRespondToPromotion extends GridPane implements Runnable {
	private static final String TAG = "Table_CostumersRespondT";
	/** Boolean attribute for UpdateFields method */
	protected boolean isAddWindowSent;
	/** HBox */
	private HBox hbButtons;
	/** Status */
	private Label lblStatus;
	/** View */
	private View view;
	/** List of products */
	private final ObservableList<DisplayableCustomer> data = FXCollections.observableArrayList();
	private int totalCustomersRespond; // respond
	private ArrayList<saleEventListener> theListeners;
	private int totalSubscribed=0;

	public Table_CostumersRespondToPromotion(View view) {
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
//		hbButtons.getChildren().addAll(btnSave, btnUndo, btnReverse);

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
		Text title = new Text("Customers Respond to Promotion");
		title.setFont(Font.font("ariel", FontWeight.BOLD, 25));
		title.setFill(Color.RED);
		title.setEffect(r);
		setHalignment(title, HPos.CENTER);
		add(title, 0, 0, 5, 1);
	}






	private void initTableProducts() {
		initTable();
		initStatus();
	}


//	public void updateTable(ArrayList<saleEventListener> saleEventListeners) {
//		data.clear();
//		totalCustomersRespond = 0;
//		for (saleEventListener l : saleEventListeners) {
//			DisplayableCustomer tmp = new DisplayableCustomer((Customer) l);
//			totalCustomersRespond += 1;
//			data.add(tmp);
//
//		}
//
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						wait(2000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.err.println((TAG + ", run: passes 2 sec"));
//				}
//			});
//	}
//		updateProfit("The profit is: " + getProfit() + "$", "green");
	/** update profit */
//	public void updateProfit(String status, String color) {
//		lblProfit.setText(status);
//		lblProfit.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
//	}
	/** initial TableView */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initTable() {
		view.fireListOfProducts();

		TableView table = new TableView();

		TableColumn customerNameCol = new TableColumn("Name");
		customerNameCol.setCellValueFactory(new PropertyValueFactory("name"));

		TableColumn PhoneNum = new TableColumn("PhoneNum");
		PhoneNum.setCellValueFactory(new PropertyValueFactory("mobileNumber"));

		TableColumn message = new TableColumn("Message");
		message.setCellValueFactory(new PropertyValueFactory("responseToPromotion"));


		table.setItems(data);
		table.getColumns().addAll(customerNameCol, message,PhoneNum);

		view.fireListOfProducts();
		add(table, 0, 3, 3, 1);
		setHalignment(table, HPos.CENTER);
	}

	/** initial status label */
	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 4);
		add(lblStatus, 1, 4);
		setHalignment(lblStatus, HPos.CENTER);
	}


	/** update status */
	public void updateStatus(String status, String color) {
		lblStatus.setText(status);
		lblStatus.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
	}


	@Override
	public void run() {
		// add message from user
		try {
			totalSubscribed += theListeners.size();
			for (saleEventListener l : this.theListeners) {
				DisplayableCustomer tmp = new DisplayableCustomer((Customer) l);
				totalCustomersRespond += 1;
				Platform.runLater(()-> {
					updateStatus(totalCustomersRespond + " / " + totalSubscribed , "black");
				});
				data.add(tmp);
				Thread.sleep(5000);


			}


		} catch (InterruptedException e) {
			// finished
			e.printStackTrace();
		}

	}

	public void sendSaleListeners(ArrayList<saleEventListener> listeners) {
		this.theListeners = listeners;
	}
}