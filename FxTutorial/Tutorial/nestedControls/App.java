package nestedControls;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(this.getClass().getResource("App.fxml"));

		primaryStage.setWidth(350);
		primaryStage.setHeight(400);
		primaryStage.setTitle("Test");

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) { launch(); }

}
