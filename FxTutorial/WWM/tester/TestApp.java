package tester;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class TestApp extends Application{

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent p = FXMLLoader.load(this.getClass().getResource("../app/gameScreen/questionPanel/text/QuestionPanel.fxml"));

		Scene sc = new Scene(p);
		primaryStage.setScene(sc);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.show();
	}

}
