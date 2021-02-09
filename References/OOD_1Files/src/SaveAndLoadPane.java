import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * 
 * @author Grigory Shaulov
 *
 */
public class SaveAndLoadPane extends GridPane {

	/**
	 * Variables
	 */
	private HBox hbRButtons;
	private ToggleGroup group;
	RadioButton rButtonBoolean;
	RadioButton rButtonChar;
	RadioButton rButtonString;
	RadioButton rButtonInt;
	RadioButton rButtonBookmark;
	private TextField txtFldTitle;
	private TextField txtFldValue;
	private HBox hbButtons;
	Button btnSave;
	Button btnLoadData;
	Button btnLoadBookmark;
	private GridPane statusPane;
	private Label lblStatus;
	private Label lblType;
	private Label lblValue;

	/**
	 * Constructor
	 */
	public SaveAndLoadPane() {
		init();
	}

	// init components
	public void init() {
		initRoot();
		initTitle();
		initHBRadioButtons();
		initValueField();
		initHBButtons();
		initStatus();
	}

	// init root pane
	private void initRoot() {
		setMinSize(450, 400);
		setPadding(new Insets(10, 10, 10, 10));
		setVgap(10);
		setHgap(10);
		setAlignment(Pos.CENTER);
		setStyle("-fx-border-color: black");
	}

	// init title
	private void initTitle() {

		Reflection r = new Reflection();
		r.setFraction(0.6);
		Text title = new Text("Save And Load Data");
		title.setFont(Font.font("ariel", FontWeight.BOLD, 25));
		title.setFill(Color.RED);
		title.setEffect(r);
		GridPane.setHalignment(title, HPos.CENTER);
		add(title, 0, 0, 5, 1);
		Label centerTitle = new Label("ENTER VALUE TO SAVE:");
		centerTitle.setStyle("-fx-text-alignment: center;-fx-text-fill: blue;-fx-font-weight: bold");
		GridPane.setHalignment(centerTitle, HPos.CENTER);
		add(centerTitle, 0, 1, 5, 1);
	}

	// init HBox with RadioButtons
	private void initHBRadioButtons() {
		hbRButtons = getHBox();
		group = new ToggleGroup();

		rButtonBoolean = new RadioButton("Boolean");
		rButtonChar = new RadioButton("Char");
		rButtonString = new RadioButton("String");
		rButtonInt = new RadioButton("Integer");
		rButtonBookmark = new RadioButton("Bookmark");

		rButtonBoolean.setToggleGroup(group);
		rButtonChar.setToggleGroup(group);
		rButtonString.setToggleGroup(group);
		rButtonInt.setToggleGroup(group);
		rButtonBookmark.setToggleGroup(group);

		rButtonBoolean.setOnAction(e -> hideTitleField());
		rButtonChar.setOnAction(e -> hideTitleField());
		rButtonString.setOnAction(e -> hideTitleField());
		rButtonInt.setOnAction(e -> hideTitleField());
		rButtonBookmark.setOnAction(e -> showTitleField());

		rButtonBoolean.setSelected(true);
		hbRButtons.getChildren().addAll(rButtonBoolean, rButtonChar, rButtonString, rButtonInt, rButtonBookmark);
		add(hbRButtons, 0, 2, 5, 1);
	}

	// init Value Fields
	private void initValueField() {
		txtFldTitle = new TextField();
		txtFldTitle.setPromptText("Enter Title here");
		txtFldTitle.setOnMouseClicked(e -> updateStatus("", "", "", "black"));
		txtFldValue = new TextField();
		txtFldValue.setPrefWidth(450);
		txtFldValue.setPromptText("Enter Value here");
		txtFldValue.setOnMouseClicked(e -> updateStatus("", "", "", "black"));
		add(txtFldValue, 0, 3, 5, 1);
	}

	// init HBox with Buttons
	private void initHBButtons() {
		hbButtons = getHBox();
		btnSave = new Button("Save");
		btnLoadData = new Button("Load Data");
		btnLoadBookmark = new Button("Load Bookmark");
		hbButtons.getChildren().addAll(btnSave, btnLoadData, btnLoadBookmark);
		add(hbButtons, 0, 4, 5, 1);
	}

	// init status
	private void initStatus() {
		statusPane = getGridPane();
		lblStatus = new Label();
		lblType = new Label();
		lblValue = new Label();
		statusPane.add(new Label("Status: "), 0, 0);
		statusPane.add(lblStatus, 1, 0);
		statusPane.add(new Label(), 0, 1);
		statusPane.add(new Label("Type: "), 0, 2);
		statusPane.add(lblType, 1, 2);
		statusPane.add(new Label("Value: "), 0, 3);
		statusPane.add(lblValue, 1, 3);
		add(statusPane, 0, 5, 5, 1);
	}

	// hide Title Field
	private void hideTitleField() {
		getChildren().remove(txtFldTitle);
		getChildren().remove(txtFldValue);
		add(txtFldValue, 0, 3, 5, 1);
		cleanValueFields();
	}

	// show Title Field
	private void showTitleField() {
		getChildren().remove(txtFldTitle);
		getChildren().remove(txtFldValue);
		add(txtFldTitle, 0, 3, 2, 1);
		add(txtFldValue, 2, 3, 3, 1);
		cleanValueFields();
	}

	// clean Value Fields
	public void cleanValueFields() {
		txtFldTitle.setText("");
		txtFldValue.setText("");
	}

	// get new styled hbox
	private HBox getHBox() {
		HBox hBox = new HBox(5);
		hBox.setMinSize(450, 50);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setAlignment(Pos.CENTER);
		return hBox;
	}

	// get new styled vbox
	private GridPane getGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setMinSize(450, 50);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.TOP_LEFT);
		return gridPane;
	}

	// update status and value
	public void updateStatus(String status, String type, String value, String color) {
		lblStatus.setText(status);
		lblStatus.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
		lblType.setText(type);
		lblType.setStyle("-fx-text-fill: #960096;-fx-font-weight: bold");
		lblValue.setText(value);
		lblValue.setStyle("-fx-text-fill: blue;-fx-font-weight: bold");
	}

	// set radio button selected by type
	public void setRButtonSelected(String type) {
		switch (type) {
		case "Boolean":
			hideTitleField();
			rButtonBoolean.setSelected(true);
			break;
		case "Char":
			hideTitleField();
			rButtonChar.setSelected(true);
			break;
		case "String":
			hideTitleField();
			rButtonString.setSelected(true);
			break;
		case "Integer":
			hideTitleField();
			rButtonInt.setSelected(true);
			break;
		case "Bookmark":
			showTitleField();
			rButtonBookmark.setSelected(true);
			break;
		default:
			hideTitleField();
			rButtonBoolean.setSelected(true);
			break;
		}

	}

	// getters and setters
	public TextField getTxtFldTitle() {
		return txtFldTitle;
	}

	public void setTxtFldTitle(TextField txtFldTitle) {
		this.txtFldTitle = txtFldTitle;
	}

	public TextField getTxtFldValue() {
		return txtFldValue;
	}

	public void setTxtFldValue(TextField txtFldValue) {
		this.txtFldValue = txtFldValue;
	}

	public Label getLabelStatus() {
		return lblStatus;
	}

	public void setLabelStatus(Label lblStatus) {
		this.lblStatus = lblStatus;
	}

	public Label getLabelValue() {
		return lblValue;
	}

	public void setLabelValue(Label lblValue) {
		this.lblValue = lblValue;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public Button getBtnLoadData() {
		return btnLoadData;
	}

	public void setBtnLoadData(Button btnLoadData) {
		this.btnLoadData = btnLoadData;
	}

	public Button getBtnLoadBookmark() {
		return btnLoadBookmark;
	}

	public void setBtnLoadBookmark(Button btnLoadBookmark) {
		this.btnLoadBookmark = btnLoadBookmark;
	}

	public ToggleGroup getGroup() {
		return group;
	}

	public void setGroup(ToggleGroup group) {
		this.group = group;
	}
	// end getters and setters

}
