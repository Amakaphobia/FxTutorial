package oracleUIControls.akHyperlink;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {
	
	final static Color[] colorNames = new Color[]{
	        Color.RED,
	        Color.YELLOW,
	        Color.BLUE,
	        Color.GREEN
    };
	final static String[] captions = new String[]{
	        "Products",
	        "Education",
	        "Partners",
	        "Support"
	};
	
	final static String[] urls = new String[]{
	        "http://www.oracle.com/us/products/index.html",
	        "http://education.oracle.com/",
	        "http://www.oracle.com/partners/index.html",
	        "http://www.oracle.com/us/support/index.html"
	 };
	
	final Rectangle selectedImage = new Rectangle(40,40);
    final ScrollPane list = new ScrollPane();
    final Hyperlink[] hpls = new Hyperlink[captions.length];

    private Parent customContent2() {
		selectedImage.setLayoutX(100);
		selectedImage.setLayoutY(10);
		
		final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
 
        for (int i = 0; i < captions.length; i++) {
            final Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
            final Rectangle image = new Rectangle(40, 40);
            image.setFill(colorNames[i]);
            hpl.setGraphic(image);
            hpl.setFont(Font.font("Arial", 14));
            final String url = urls[i];
 
            hpl.setOnAction(e -> 
                    webEngine.load(url));
        }
              
        HBox hbox = new HBox();
        hbox.getChildren().addAll(hpls);
 
        VBox vbox = new VBox();
        
        vbox.getChildren().addAll(hbox, browser);
        VBox.setVgrow(browser, Priority.ALWAYS);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10));
		
		Group root = new Group();
        root.getChildren().addAll(vbox);
        
        return root;
	}
    
	@SuppressWarnings("unused")
	private Parent customContent1() {
		selectedImage.setLayoutX(100);
		selectedImage.setLayoutY(10);
		
		for (int i = 0; i < captions.length; i++) {
            final Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
            final int index = i;
            hpl.setOnAction(e ->  
            	selectedImage.setFill(colorNames[index]));
            
        }
	    
		Button btn = new Button("Klick me");
		btn.setOnAction(e -> {
			for (int i = 0; i < captions.length; i++) {
                hpls[i].setVisited(false);
                selectedImage.setFill(null);            
            }
		});
		
		VBox vbox = new VBox();
        vbox.getChildren().addAll(hpls);
        vbox.getChildren().add(btn);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10));
		
		Group root = new Group();
        root.getChildren().addAll(vbox, selectedImage);
        
        return root;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(customContent2()));
		primaryStage.setTitle("Hyperlink");
		primaryStage.setHeight(550);
		primaryStage.setWidth(570);
		primaryStage.show();
	}

	

	public static void main(String[] args) {
		launch();
	}

}
