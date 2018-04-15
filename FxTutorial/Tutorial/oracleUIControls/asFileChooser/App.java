package oracleUIControls.asFileChooser;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import boxes.Pair;

@SuppressWarnings("javadoc")
public class App extends Application {

	private Label l1 = new Label("Ordner");
	private Label l2 = new Label("Datei");

	@SuppressWarnings("unused")
	private Parent test1() {
		Button b = new Button("Click");
		b.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Open File");
			this.setFile(fc.showOpenDialog(new Stage()));
		});
		Button b2 = new Button("Multiple");
		b2.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Open File");
			this.setFile(fc.showOpenMultipleDialog(new Stage()));
		});

		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.getChildren().addAll(b,b2,this.l1,this.l2);
		return root;
	}

	private void setFile(List<File> openMultiple) {
		if(openMultiple == null) return;
		Pair<String, String> result =
			openMultiple.stream()
				.map(file ->
					new Pair<>(
						file.getName(),
						file.getAbsolutePath()))
				.reduce(
					new Pair<>("", ""),
					(carry, ele) ->
						new Pair<>(
							carry.getKey().concat(ele.getKey()),
							carry.getValue().concat(ele.getValue())));

		this.l1.setText(result.getKey());
		this.l2.setText(result.getValue());
	}

	private void setFile(File f) {
		this.l1.setText(f.getName());
		this.l2.setText(f.getAbsolutePath());
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
	Scene sc = new Scene(this.test1());

	primaryStage.setScene(sc);
	primaryStage.setTitle("FileChooser");
	primaryStage.show();
	}

	public static void main(String[] args) { launch(); }
}
