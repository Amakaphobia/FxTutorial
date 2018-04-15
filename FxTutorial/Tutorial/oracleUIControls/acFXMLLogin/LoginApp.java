package oracleUIControls.acFXMLLogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class LoginApp extends Application 
{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/oracleUIControls/acFXMLLogin/LoginFxml.fxml"));
		Scene sc = new Scene(root, 300, 275);
		
		primaryStage.setTitle("FXML Login");
		primaryStage.setScene(sc);
		
		primaryStage.show();
	}

}
