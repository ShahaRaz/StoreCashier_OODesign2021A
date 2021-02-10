package Activation;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import javafx.application.Application;
import javafx.stage.Stage;
import main.controller.Controller;

import main.model.Model;
import main.view.View;

public class AppRunnable extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model theModel = new Model();
		View theView = new View(primaryStage);
		Controller TheController = new Controller(theModel, theView);
	}
}
