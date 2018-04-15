package oracleUIControls.adColorList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Scene sc = new Scene(new ColorList(),200,200);
		stage.setTitle("Colorpicker");
		stage.setScene(sc);
		stage.show();

	}

	public static void main(String[] args) { launch(); }

}
