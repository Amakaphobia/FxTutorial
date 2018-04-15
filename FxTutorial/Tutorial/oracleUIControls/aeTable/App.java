package oracleUIControls.aeTable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application 
{
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc  = new Scene(new Table());
		primaryStage.setTitle("Tabelle");		
		primaryStage.setScene(sc);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	public static void main(String[] arg) { launch(arg); }
	
}
