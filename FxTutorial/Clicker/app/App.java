package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {

	@Override
	public void start(Stage Stage) throws Exception {
		Stage.setTitle("Clicker");
		Parent root = FXMLLoader.load(getClass().getResource("Clicker.fxml"));
		
		Scene sc = 	new Scene(root);
		Stage.setScene(sc);
		Stage.sizeToScene();
		Stage.show();
	}

	public static void main(String[] args) { launch(args); }

}
