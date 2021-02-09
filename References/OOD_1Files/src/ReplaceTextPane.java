import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * 
 * @author Grigory Shaulov
 *
 */
public class ReplaceTextPane extends GridPane {

	/**
	 * Variables
	 */
	/* select file */
	private TextField txtFldFileName;
	private Button btnSelectFile;
	/* find and raplace file */
	private TextField txtFldFind;
	private TextField txtFldReplaceWith;
	private Button btnReplaceWith;
	/* status */
	private Label lblStatus;

	/**
	 * Constructor
	 */
	public ReplaceTextPane() {
		init();
	}

	// init components
	public void init() {
		initRoot();
		initTitle();
		initSelectFile();
		initFindAndReplace();
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
		Text title = new Text("Find and Replace Text");
		title.setFont(Font.font("ariel", FontWeight.BOLD, 25));
		title.setFill(Color.RED);
		title.setEffect(r);
		GridPane.setHalignment(title, HPos.CENTER);
		Label lblTitle = new Label("ENTER TEXT TO REPLACE:");
		lblTitle.setStyle("-fx-text-alignment: center;-fx-text-fill: blue;-fx-font-weight: bold");
		GridPane.setHalignment(lblTitle, HPos.CENTER);
		add(title, 0, 0, 5, 1);
		add(lblTitle, 0, 1, 5, 1);
	}

	// init HBox to select file
	private void initSelectFile() {
		txtFldFileName = new TextField();
		txtFldFileName.setDisable(true);
		btnSelectFile = new Button("Select File");
		add(new Label("File Name:"), 0, 2);
		add(txtFldFileName, 1, 2);
		add(btnSelectFile, 2, 2);
	}

	// init HBox to Find And Replace text
	private void initFindAndReplace() {
		txtFldFind = new TextField();
		txtFldFind.setOnMouseClicked(e -> updateStatus("", "black"));
		txtFldReplaceWith = new TextField();
		txtFldReplaceWith.setOnMouseClicked(e -> updateStatus("", "black"));
		btnReplaceWith = new Button("Replace");
		add(new Label("Find: "), 0, 3);
		add(txtFldFind, 1, 3);
		add(new Label("Replace with: "), 0, 4);
		add(txtFldReplaceWith, 1, 4);
		add(btnReplaceWith, 2, 4);
	}

	// init status
	private void initStatus() {
		lblStatus = new Label();
		add(new Label("Status: "), 0, 5);
		add(lblStatus, 1, 5, 4, 1);
	}

	// update status
	public void updateStatus(String status, String color) {
		lblStatus.setText(status);
		lblStatus.setStyle("-fx-text-fill: " + color + ";-fx-font-weight: bold");
	}

	
	// clean Value Fields
	public void cleanValueFields() {
		txtFldFind.setText("");
		txtFldReplaceWith.setText("");
	}
	
	//getters&setters
	public TextField getTxtFldFileName() {
		return txtFldFileName;
	}

	public void setTxtFldFileName(TextField txtFldFileName) {
		this.txtFldFileName = txtFldFileName;
	}

	public TextField getTxtFldFind() {
		return txtFldFind;
	}

	public void setTxtFldFind(TextField txtFldFind) {
		this.txtFldFind = txtFldFind;
	}

	public TextField getTxtFldReplaceWith() {
		return txtFldReplaceWith;
	}

	public void setTxtFldReplaceWith(TextField txtFldReplaceWith) {
		this.txtFldReplaceWith = txtFldReplaceWith;
	}

	public Button getBtnReplaceWith() {
		return btnReplaceWith;
	}

	public void setBtnReplaceWith(Button btnReplaceWith) {
		this.btnReplaceWith = btnReplaceWith;
	}

	public Label getLblStatus() {
		return lblStatus;
	}

	public void setLblStatus(Label lblStatus) {
		this.lblStatus = lblStatus;
	}

	public Button getBtnSelectFile() {
		return btnSelectFile;
	}

	public void setBtnSelectFile(Button btnSelectFile) {
		this.btnSelectFile = btnSelectFile;
	}
	//end getters&setters


}
