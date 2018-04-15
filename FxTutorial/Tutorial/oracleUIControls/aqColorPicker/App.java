package oracleUIControls.aqColorPicker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {

	@SuppressWarnings("unused")
	private Parent test1() {
		//Empty contructor, the control appears with the default color, which is WHITE
		ColorPicker colorPicker1 = new ColorPicker();
		//Color constant set as the currently selected color
		ColorPicker colorPicker2 = new ColorPicker(Color.BLUE);
		//Web color value set as the currently selected color
		ColorPicker colorPicker3 = new ColorPicker(Color.web("#ffcce6"));
		
		VBox vb = new VBox();
		vb.setPadding(new Insets(10));
		vb.setSpacing(5);
		vb.getChildren().addAll(colorPicker1, colorPicker2, colorPicker3);
		
		return vb;
	}

	private Parent test2() {
		final ColorPicker cp = new ColorPicker();
		cp.setValue(Color.CORAL);
		
		final Text text = new Text("Try the new Color Picker");
		text.setFont(new Font("hack", 20));
		text.setFill(cp.getValue());
		
		text.fillProperty().bind(cp.valueProperty());
		
		HBox root = new HBox(20);
		root.setPadding(new Insets(5));
		root.getChildren().addAll(cp, text);
		
		return root;
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = new Scene(test2());
//		sc.setFill(Color.OLDLACE);
		
		primaryStage.setScene(sc);
//		primaryStage.setWidth(350);
//		primaryStage.setHeight(400);
		primaryStage.setTitle("ColorPicker");
		primaryStage.show();
	}
	public static void main(String[] args) { launch(); }

}
