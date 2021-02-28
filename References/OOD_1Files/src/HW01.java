import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * @author Grigory Shaulov
 */
public class HW01 extends Application {

    /**
     * Variables
     */

    // Save And Load Pane and components*/
    private SaveAndLoadPane saveAndLoadPane;
    private File dataFile;
    private File objectFile;
    private ToggleGroup group;
    private Button btnSave;
    private Button btnLoadData;
    private Button btnLoadBookmark;

    // Replace text Pane and components*/
    private ReplaceTextPane replaceTextPane;
    private File textFile;
    private Button btnSelectFile;
    private TextField txtFldFind;
    private TextField txtFldReplaceWith;
    private Button btnReplace;

    // Other variables
    private DataOutputStream dOut;
    private DataInputStream dIn;
    private ObjectOutputStream oOut;
    private ObjectInputStream oIn;
    private boolean isAppendableData;
    private boolean isAppendableObject;
    private Stage stage;

    /**
     * Application initialization method before start
     */
    public void init() throws Exception {
        initAppandable();
        initSaveAndLoadPane();
        initReplaceTextPane();
        initDataStream();
        initObjectStream();
    }

    // check if file exists to append objects
    private void initAppandable() {
        dataFile = new File("values.dat");
        isAppendableData = dataFile.exists();
        objectFile = new File("values.obj");
        isAppendableObject = objectFile.exists();
    }

    // init Save And Load Pane
    private void initSaveAndLoadPane() {
        saveAndLoadPane = new SaveAndLoadPane();
        group = saveAndLoadPane.getGroup();
        btnSave = saveAndLoadPane.getBtnSave();
        btnSave.setOnAction(e -> saveValueToFile(group));
        btnLoadData = saveAndLoadPane.getBtnLoadData();
        btnLoadData.setOnAction(e -> loadDataFromFile());
        btnLoadBookmark = saveAndLoadPane.getBtnLoadBookmark();
        btnLoadBookmark.setOnAction(e -> loadBookmarkFromFile());
    }

    // init Replace Text Pane
    private void initReplaceTextPane() {
        replaceTextPane = new ReplaceTextPane();
        btnSelectFile = replaceTextPane.getBtnSelectFile();
        btnSelectFile.setOnAction(e -> selectFile());
        txtFldFind = replaceTextPane.getTxtFldFind();
        txtFldReplaceWith = replaceTextPane.getTxtFldReplaceWith();
        btnReplace = replaceTextPane.getBtnReplaceWith();
        btnReplace.setOnAction(e -> replaceText());
    }

    // select text file
    private void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        textFile = fileChooser.showOpenDialog(stage);
        if (textFile != null) {
            replaceTextPane.getTxtFldFileName().setText(textFile.getName() + "");
            replaceTextPane.updateStatus("File Selected!", "green");
        } else {
            replaceTextPane.getTxtFldFileName().setText("");
            replaceTextPane.updateStatus("", "black");
        }

    }

    // is valid input method
    private boolean isValidInput() {
        replaceTextPane.updateStatus("", "black");
        boolean isValid = false;
        if (textFile != null) {
            if (txtFldFind == null || txtFldReplaceWith == null)
                replaceTextPane.updateStatus("ERROR! Please Restart the Window!", "red");
            else if (txtFldFind.getText().equals(""))
                replaceTextPane.updateStatus("Enter Text To Find!", "red");
            else {
                isValid = true;
            }
        } else
            replaceTextPane.updateStatus("No Text File Selected!", "red");
        return isValid;
    }

    // reset OutputStream
    // A method that prevents Exception of adding objects to file
    private void resetOutputStream(String type) {
        //            switch (type) {
//                case "Data":
////                    System.out.println("resetOutputStream: isAppendable: " + isAppendableData);
//                     if file exists and isAppendable==false -> the file will be overwritten
//                    dOut = new DataOutputStream(new FileOutputStream(dataFile, isAppendableData));
//                    break;
//                case "Object":
//                     TODO: 20/11/2020 delete next line and uncomment the lines after it
//                    oOut = new ObjectOutputStream(new FileOutputStream(objectFile, isAppendableObject));
//
//                    if (isAppendableObject) {///||objectFile.length()==0) {
//                        oOut = new ObjectOutputStream(new FileOutputStream(objectFile, isAppendableObject)) {
//                            @Override // anonymous class that overrides the method that writes the header in the file
//                            protected void writeStreamHeader() throws IOException {
//                                // do not write header again
//                                return;
//                            }
//                        };
//                    } else {
//                        oOut = new ObjectOutputStream(new FileOutputStream(objectFile, isAppendableObject));
//                    }
//                    break;
//            }
    }

    // save Value To File (DATA & OBJECT)
    private void saveValueToFile(ToggleGroup toggleGroup) {

        if (saveAndLoadPane.getTxtFldValue() == null || saveAndLoadPane.getTxtFldValue().getText().equals("")) {
            saveAndLoadPane.updateStatus("Value can not be empty!", "", "", "red");
            return;
        } else {
            RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();
            String type = selected.getText(); // the type of the data selected
            String value = saveAndLoadPane.getTxtFldValue().getText(); // the value inserted to the text box

            boolean isValidBookmark = false;


            try {
                switch (type) {
                    case "Boolean":
                        dOut.writeChar('B');
                        dOut.writeBoolean(Boolean.parseBoolean(value));
                        break;
                    case "Char":
                        dOut.writeChar('C');
                        dOut.writeChar(value.charAt(0));
                        break;
                    case "String":
                        dOut.writeChar('S');
                        dOut.writeUTF(value);
                        break;
                    case "Integer":
                        dOut.writeChar('I');
                        dOut.writeInt(Integer.parseInt(value));
                        break;
                    case "Bookmark":
                        isValidBookmark = saveBookmarkToFile();

                    default:
                        System.out.println("something went wrong... type wasn't choosen well..");
                }
                if (isValidBookmark) {
                    saveAndLoadPane.updateStatus(type + " saved!", "", "", "green");
                    saveAndLoadPane.cleanValueFields();
                }
            } catch (IOException e) {
                saveAndLoadPane.updateStatus(type + " not saved!", "", "", "red");
            } catch (NumberFormatException e) {
                saveAndLoadPane.updateStatus("Entered value must be " + type + "!", "", "", "red");
            } catch (Exception e) {
                saveAndLoadPane.updateStatus(type + " not saved!", "", "", "red");
            }
        }
    }

    // save Bookmark To File
    private boolean saveBookmarkToFile() {
        try {
            if (saveAndLoadPane.getTxtFldTitle() == null || saveAndLoadPane.getTxtFldTitle().getText().equals("")) {
                saveAndLoadPane.updateStatus("Title can not be empty!", "", "", "red");
            } else {
                oOut.writeUTF("nextObject:");
                String title = saveAndLoadPane.getTxtFldTitle().getText(), value = saveAndLoadPane.getTxtFldValue().getText();
//				oOut = new ObjectOutputStream(new FileOutputStream(objectFile)); // might need to delete here (reset output stream does it)
                oOut.writeObject(new Bookmark(title, value));
//				// TODO: 20/11/2020 delete the closing next line
//				oOut.close();
                return true;

            }
        } catch (IOException e) {
            saveAndLoadPane.updateStatus("Bookmark not saved!", "", "", "red");
        } catch (Exception e) {
            saveAndLoadPane.updateStatus("Bookmark not saved!", "", "", "red");
        }
        return false;
    }

    // reset InputStream
    private void resetInputStream(String type) {
        try {
            switch (type) {
                case "Data":
                    dIn = new DataInputStream(new FileInputStream(dataFile));
                    break;
                case "Object":
                    oIn = new ObjectInputStream(new FileInputStream(objectFile));
                    break;
            }
        } catch (FileNotFoundException e) {
            System.out.println("resetInputStream Method Exception: FileNotFound " + e.getMessage());
        } catch (IOException e) {
            System.out.println("resetInputStream Method Exception: IOException " + e.getMessage());
        }
    }

    // load Value From File
    private void loadDataFromFile() {
        String type = "", value = "";
        char chType;
        try {
            if ((dataFile.length() > 0) && (dIn.available() == 0)) { // file not empty, and pointer is at the end
                dIn = new DataInputStream(new FileInputStream(dataFile)); // go the beginning of the file
            }
            if (dIn.available() != 0) {
                chType = dIn.readChar();
                switch (chType) {
                    case 'B':
                        type = "Boolean";
                        value = "" + dIn.readBoolean();
                        break;
                    case 'C':
                        type = "Char";
                        value = "" + dIn.readChar();
                        break;
                    case 'S':
                        type = "String";
                        value = "" + dIn.readUTF();
                        break;
                    case 'I':
                        type = "Integer";
                        value = "" + dIn.readInt();
                        break;
                    default:
                        System.out.println("chType is: " + chType);
                        System.out.println("something went wrong... type wasn't chosen well..");
                }

                saveAndLoadPane.setRButtonSelected(type);
                saveAndLoadPane.updateStatus("Data loaded!", type, value, "green");
            } else {
                saveAndLoadPane.updateStatus("There is no data to load!", "", "", "red");
            }
        } catch (IOException e) {
            saveAndLoadPane.updateStatus("Data not loaded!", "", "", "red");
        } catch (Exception e) {
            saveAndLoadPane.updateStatus("Data not loaded!", "", "", "red");
        }
    }

    // load Bookmark From File
    private void loadBookmarkFromFile() {
        String type = "", value = "";
        Bookmark incoming;
        int avaiblle;
        String fuckingWhyAmINeeded;
        try {
            if (oIn.available() == 0)
                resetInputStream("Object");
            avaiblle = oIn.available();
            System.out.println(avaiblle);
//			if (oIn==null)
//				oIn = new ObjectInputStream(new FileInputStream(objectFile));
//			else if((objectFile.length()>0) && (oIn.available()==0)) { // file not empty, and pointer is at the end
//				oIn = new ObjectInputStream(new FileInputStream(objectFile)); // go the beginning of the file
//			}
            if (oIn.available() != 0) {
                // TODO: 19/11/2020 Q2
                fuckingWhyAmINeeded = oIn.readUTF();
                incoming = (Bookmark) (oIn.readObject());
                type = "Bookmark";
                value = incoming.getTitle() + ": " + incoming.getValue();
                saveAndLoadPane.setRButtonSelected(type);
                saveAndLoadPane.updateStatus("Object Loaded!", type, value, "green");
            } else {
                saveAndLoadPane.updateStatus("There is no object to load!", "", "", "red");
            }

        } catch (IOException e) {
            e.printStackTrace();
            saveAndLoadPane.updateStatus("Object not loaded!", "", "", "red");
        } catch (Exception e) {
            e.printStackTrace();
            saveAndLoadPane.updateStatus("Object not loaded!", "", "", "red");
        }
    }

    // replace "find" to "replacewith" string
    private void replaceText() {
        boolean isFound = false;
        if (isValidInput()) {
            String find = txtFldFind.getText(), replaceWith = txtFldReplaceWith.getText();
            try (RandomAccessFile raf = new RandomAccessFile(textFile, "rw")) {
                // TODO: 22/11/2020 Q3 __ Write here
                // 1. find the word
                // 2.

                int readPointer = 0; // where we read
                byte[] data = new byte[find.length()]; // bytes arr in the length of the word we're searching for.
                String readText; // init b4 the while.
                while (raf.read(data) != -1) { // while file not ended, raf.read(buffer) fill the bytes buffer with data
                    readText = new String(data); // convert the read data
                    if (readText.equals(find)) { // we got to word to be replaced
                        isFound = true;
                        byte[] temp = new byte[(int) (raf.length() - readPointer + find.length())]; // set up a char that will hold the suffix of the file
                        raf.read(temp); // temp hold's the suffix of the data
                        raf.setLength(readPointer); // "cut" the data to b4 the begging of the deleted word
                        raf.write(replaceWith.getBytes()); // insert the word that is replacing
                        raf.write(temp); // write back the suffix
                    } else {
                        raf.seek(readPointer++);
                    }
                    raf.seek(readPointer); // back to scanning position
                }
                raf.setLength(readPointer);

                if (isFound) {
                    replaceTextPane.updateStatus("String Replaced!", "green");
                    replaceTextPane.cleanValueFields();
                } else
                    replaceTextPane.updateStatus("No String Found!", "red");
            } catch (FileNotFoundException e) {
                System.out.println("DeleteStrFromFileMethodException: File Not Found! " + e.getMessage());
            } catch (IOException e) {
                System.out.println("DeleteStrFromFileMethodException: Input Output Exception! " + e.getMessage());
            }

        }

    }

    // init Data Stream
    private void initDataStream() {
        resetOutputStream("Data");
        resetInputStream("Data");
    }

    // init Object stream
    private void initObjectStream() {
        resetOutputStream("Object");
        resetInputStream("Object");
    }

    /**
     * Application start method
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setScene(new Scene(saveAndLoadPane, 500, 500));
            stage.setTitle("Data And Object Saver");
            stage.show();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(replaceTextPane, 500, 500));
            secondStage.setTitle("Text Editor");
            secondStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args parameter for main method
     */
    public static void main(String[] args) {
        launch(args);
    }

}
