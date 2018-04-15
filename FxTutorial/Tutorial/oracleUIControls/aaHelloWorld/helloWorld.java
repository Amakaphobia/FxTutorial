package oracleUIControls.aaHelloWorld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class helloWorld extends Application
{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello World");
		Button btn = new Button();
		btn.setText("Sag Hallo");
		btn.setOnAction(e -> System.out.println("Hallo Welt"));

		StackPane root = new StackPane();
		root.getChildren().add(btn);

		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

}
