package oracleUIControls.anTitledPanesAccordion;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {
	final String[] names = {"red", "blue", "green"};
	final Color[] colors = new Color[names.length];
	final Rectangle[] recs = new Rectangle[names.length];
	final TitledPane[] panes = new TitledPane[names.length];
    final Label label = new Label("N/A");
	
	private Parent accTest() {
		TitledPane gridTitlePane = new TitledPane();
		
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("To: "), 0, 0);
        grid.add(new TextField(), 1, 0);
        grid.add(new Label("Cc: "), 0, 1);
        grid.add(new TextField(), 1, 1);
        grid.add(new Label("Subject: "), 0, 2);
        grid.add(new TextField(), 1, 2);        
        grid.add(new Label("Attachment: "), 0, 3);
        grid.add(label,1, 3);
        gridTitlePane.setText("Grid");
        gridTitlePane.setContent(grid);
        
        
		for(int i = 0; i < names.length; i++) {
			colors[i] = Color.web(names[i]);
			recs[i] = new Rectangle(40, 40);
			recs[i].setFill(colors[i]);
			panes[i] = new TitledPane(names[i], recs[i]);
		}
		
		final Accordion acc = new Accordion();
		acc.getPanes().addAll(panes);
		
		acc.expandedPaneProperty().addListener((ov, oldVal, newVal) -> {
			if(newVal == null) return;
			
			label.setText(acc.getExpandedPane().getText());
	    });
		
		HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        hbox.getChildren().setAll(gridTitlePane, acc);
		
		Group root = new Group();
		root.getChildren().add(hbox);
		
		return root;
	}
	
	@SuppressWarnings("unused")
	private Parent gridTest() {
		TitledPane gridTitlePane = new TitledPane();
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("First Name: "), 0, 0);
		grid.add(new TextField(), 1, 0);
		grid.add(new Label("Last Name: "), 0, 1);
		grid.add(new TextField(), 1, 1);
		grid.add(new Label("Email: "), 0, 2);
		grid.add(new TextField(), 1, 2);        
		gridTitlePane.setText("Grid");
		gridTitlePane.setContent(grid);
		
		gridTitlePane.setCollapsible(false);
		gridTitlePane.setAnimated(false);
		
		return gridTitlePane;
	}

	@SuppressWarnings("unused")
	private Parent basicTest() {
		TitledPane tp = new TitledPane();
		tp.setText("My Titled Pane");
		tp.setContent(new Button("Button"));
		
		return tp;
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = new Scene(accTest());
		sc.setFill(Color.GHOSTWHITE);
		
		primaryStage.setScene(sc);
//		primaryStage.setWidth(300);
//      primaryStage.setHeight(150);
		primaryStage.setTitle("TitledPane");
		primaryStage.show();
	}



	public static void main(String[] args) { launch(); }

}
