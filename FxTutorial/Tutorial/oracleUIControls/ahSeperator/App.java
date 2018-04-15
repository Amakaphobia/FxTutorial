package oracleUIControls.ahSeperator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {

	Stage pS;
	boolean sc1;
	Button btnSwitcher;
    Label caption = new Label("Weather Forecast");
    Label friday = new Label("Friday");
    Label saturday = new Label("Saturday");
    Label sunday = new Label("Sunday");
	
	private Parent customContent() {
		sc1 = false;
		final List<String> ll = Arrays.asList("March","April","May","Pirat","Juli");
		
		final Separator sep = new Separator();
		sep.setMaxWidth(40);
		sep.setValignment(VPos.CENTER);
		
		btnSwitcher = new Button("Switch");
		btnSwitcher.setOnAction(e -> changeScene());
		
		
		VBox root = new VBox();
		root.getChildren().addAll(
			ll.stream()
			  .map(CheckBox::new)
			  .collect(Collectors.toList()));
		
		root.setSpacing(5);
		
		root.getChildren().add(3, sep);
		root.getChildren().add(btnSwitcher);
		
		return root;
	}
	
	private Parent CustomContent2() {
 
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(2);
        grid.setHgap(5);
 
 
        Rectangle cloudImage = new Rectangle(40,40);
        cloudImage.setFill(Color.RED);
        Rectangle sunImage = new Rectangle(40,40);
        sunImage.setFill(Color.BLUE);
        Rectangle sunImage2 = new Rectangle(40,40);
        sunImage2.setFill(Color.BLUE);
        
        caption.setFont(Font.font("Verdana", 20));
        GridPane.setConstraints(caption, 0, 0);
        GridPane.setColumnSpan(caption, 8);
        grid.getChildren().add(caption);
 
        final Separator sepHor = new Separator();
        sepHor.setValignment(VPos.CENTER);
        GridPane.setConstraints(sepHor, 0, 1);
        GridPane.setColumnSpan(sepHor, 7);
        grid.getChildren().add(sepHor);
 
        friday.setFont(Font.font("Verdana", 18));
        GridPane.setConstraints(friday, 0, 2);
        GridPane.setColumnSpan(friday, 2);
        grid.getChildren().add(friday);
 
        final Separator sepVert1 = new Separator();
        sepVert1.setOrientation(Orientation.VERTICAL);
        sepVert1.setValignment(VPos.CENTER);
        sepVert1.setPrefHeight(80);
        GridPane.setConstraints(sepVert1, 2, 2);
        GridPane.setRowSpan(sepVert1, 2);
        grid.getChildren().add(sepVert1);
 
        saturday.setFont(Font.font("Verdana", 18));
        GridPane.setConstraints(saturday, 3, 2);
        GridPane.setColumnSpan(saturday, 2);
        grid.getChildren().add(saturday);
 
        final Separator sepVert2 = new Separator();
        sepVert2.setOrientation(Orientation.VERTICAL);
        sepVert2.setValignment(VPos.CENTER);
        sepVert2.setPrefHeight(80);
        GridPane.setConstraints(sepVert2, 5, 2);
        GridPane.setRowSpan(sepVert2, 2);
        grid.getChildren().add(sepVert2);
 
        sunday.setFont(Font.font("Verdana", 18));
        GridPane.setConstraints(sunday, 6, 2);
        GridPane.setColumnSpan(sunday, 2);
        grid.getChildren().add(sunday);
 
        
        GridPane.setConstraints(cloudImage, 0, 3);
        grid.getChildren().add(cloudImage);
 
        final Label t1 = new Label("16");
        t1.setFont(Font.font("Verdana", 20));
        GridPane.setConstraints(t1, 1, 3);
        grid.getChildren().add(t1);
 
        
        GridPane.setConstraints(sunImage, 3, 3);
        grid.getChildren().add(sunImage);
 
        final Label t2 = new Label("18");
        t2.setFont(Font.font("Verdana", 20));
        GridPane.setConstraints(t2, 4, 3);
        grid.getChildren().add(t2);
 
      
        GridPane.setConstraints(sunImage2, 6, 3);
        grid.getChildren().add(sunImage2);
 
        final Label t3 = new Label("20");
        t3.setFont(Font.font("Verdana", 20));
        GridPane.setConstraints(t3, 7, 3);
        grid.getChildren().add(t3);
        

		Group root = new Group();
        root.getChildren().add(grid);
 
        return root;
	}

	private void changeScene() {
		Scene sc = new Scene(sc1 ? customContent() : CustomContent2());
		pS.close();
		pS = new Stage();
		pS.setTitle("Seperator");
		pS.setScene(sc);
		pS.show();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		pS = primaryStage;
		Scene sc = new Scene(this.customContent());
		primaryStage.setTitle("Seperator");
		primaryStage.setScene(sc);
		primaryStage.show();

	}
	
	public static void main(String[] args) { launch(); }

}
