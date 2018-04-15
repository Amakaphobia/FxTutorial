package oracleUIControls.alHTML;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {
	
	private final String INITIAL_TEXT = "<html><body>Lorem ipsum dolor sit "
		    + "amet, consectetur adipiscing elit. Nam tortor felis, pulvinar "
		    + "in scelerisque cursus, pulvinar at ante. Nulla consequat"
		    + "congue lectus in sodales. Nullam eu est a felis ornare "
		    + "bibendum et nec tellus. Vivamus non metus tempus augue auctor "
		    + "ornare. Duis pulvinar justo ac purus adipiscing pulvinar. "
		    + "Integer congue faucibus dapibus. Integer id nisl ut elit "
		    + "aliquam sagittis gravida eu dolor. Etiam sit amet ipsum "
		    + "sem.</body></html>";
	
	@SuppressWarnings("unused")
	private Parent customContent3() {
		VBox root = new VBox();     
        root.setPadding(new Insets(8, 8, 8, 8));
        root.setSpacing(5);
        root.setAlignment(Pos.BOTTOM_LEFT);
 
        final HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(245);
        htmlEditor.setHtmlText(INITIAL_TEXT);
        
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
     
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("noborder-scroll-pane");
        scrollPane.setStyle("-fx-background-color: white");
        scrollPane.setContent(browser);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(180);
 
        Button showHTMLButton = new Button("Load Content in Browser");
        root.setAlignment(Pos.CENTER);
        showHTMLButton.setOnAction(e -> webEngine.loadContent(htmlEditor.getHtmlText()));
        
        root.getChildren().addAll(htmlEditor, showHTMLButton, scrollPane);
        
        return root;
	}
	
	@SuppressWarnings("unused")
	private Parent customContent2() {
		VBox root = new VBox();      
        root.setPadding(new Insets(8, 8, 8, 8));
        root.setSpacing(5);
        root.setAlignment(Pos.BOTTOM_LEFT);
              
        final HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(245);
        htmlEditor.setHtmlText(INITIAL_TEXT);       
 
        final TextArea htmlCode = new TextArea();
        htmlCode.setWrapText(true);
    
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(htmlCode);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(180);
 
        Button showHTMLButton = new Button("Produce HTML Code");
        root.setAlignment(Pos.CENTER);
        showHTMLButton.setOnAction(e -> 
                htmlCode.setText(htmlEditor.getHtmlText()));
        
        root.getChildren().addAll(htmlEditor, showHTMLButton, scrollPane);
        
        return root;
	}
		
	@SuppressWarnings("unused")
	private Parent customContent1() {
		final GridPane grid = new GridPane();
	        grid.setVgap(5);
	        grid.setHgap(10);
	              
        final ChoiceBox<String> sendTo = 
           new ChoiceBox<>(
    		   FXCollections.observableArrayList(
				   "To:", "Cc:", "Bcc:"));
        sendTo.setPrefWidth(100);                
        GridPane.setConstraints(sendTo, 0, 0);
        grid.getChildren().add(sendTo);
        
        final TextField tbTo = new TextField();
        tbTo.setPrefWidth(400);
        GridPane.setConstraints(tbTo, 1, 0);
        grid.getChildren().add(tbTo);
        
        final Label subjectLabel = new Label("Subject:");
        GridPane.setConstraints(subjectLabel, 0, 1);
        grid.getChildren().add(subjectLabel);
        
        final TextField tbSubject = new TextField();
        tbTo.setPrefWidth(400);
        GridPane.setConstraints(tbSubject, 1, 1);
        grid.getChildren().add(tbSubject);
        
        final HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(370);
        htmlEditor.setHtmlText(INITIAL_TEXT);
        
        Button btn = new Button("Send");
        btn.setOnAction(e -> System.out.println("Send"));

        final Label htmlLabel = new Label();
        htmlLabel.setWrapText(true);
        
		final VBox vb = new VBox();
		vb.setPadding(new Insets(8));
		vb.setSpacing(5);
		vb.setAlignment(Pos.BOTTOM_LEFT);
		vb.getChildren().addAll(grid, htmlEditor, btn);
		return vb;
	}
	
	@SuppressWarnings("unused")
	private Parent test() {
		HTMLEditor ed = new HTMLEditor();
		ed.setStyle(
			      "-fx-font: 12 cambria;"
			    + "-fx-border-color: brown; "
			    + "-fx-border-style: dotted;"
			    + "-fx-border-width: 2;"
			);
		ed.setHtmlText(INITIAL_TEXT);
		ed.setPrefHeight(254);
		return ed;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

//		primaryStage.setScene(new Scene(customContent1()));
		primaryStage.setScene(new Scene(customContent2()));
//		primaryStage.setScene(new Scene(customContent3()));
//		primaryStage.setScene(new Scene(test()));
		primaryStage.setTitle("HTML");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
