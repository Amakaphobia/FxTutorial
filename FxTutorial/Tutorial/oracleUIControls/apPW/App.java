package oracleUIControls.apPW;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {
	
	@SuppressWarnings("unused")
	private Parent Test2() {
		final Label message = new Label("");

		VBox vb = new VBox();
		vb.setPadding(new Insets(10, 0, 0, 10));
		vb.setSpacing(10);
		HBox hb = new HBox();
		hb.setSpacing(10);
		hb.setAlignment(Pos.CENTER_LEFT);

		Label label = new Label("Password");
		final PasswordField pb = new PasswordField();
		
		pb.setOnAction(e -> {
		        if (!pb.getText().equals("T2f$Ay!")) {
		            message.setText("Your password is incorrect!");
		            message.setTextFill(Color.rgb(210, 39, 30));
		        } else {
		            message.setText("Your password has been confirmed");
		            message.setTextFill(Color.rgb(21, 117, 84));
		        }
		        pb.clear();
		});

		hb.getChildren().addAll(label, pb);
		vb.getChildren().addAll(hb, message);
		
		return vb;
	}
	
	@SuppressWarnings("unused")
	private Parent Test() {
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Your password");
		return passwordField;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = new Scene(Test2());
//		sc.setFill(Color.OLDLACE);
		
		primaryStage.setScene(sc);
//		primaryStage.setWidth(350);
//		primaryStage.setHeight(400);
		primaryStage.setTitle("PW");
		primaryStage.show();
	}

	public static void main(String[] args) { launch(); }

}
