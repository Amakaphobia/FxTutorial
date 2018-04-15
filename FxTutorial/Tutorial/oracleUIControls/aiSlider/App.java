package oracleUIControls.aiSlider;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {
	
	final private Image cap = new Image(getClass().getResourceAsStream("cappuccino.png"));

    final private Slider opacityLevel = new Slider(0, 1, 1);    
    final private Slider sepiaTone = new Slider(0, 1, 0);
    final private Slider scaling = new Slider (0.5, 1, 1);
    final private Label opacityCaption = new Label("Opacity Level:");
    final private Label sepiaCaption = new Label("Sepia Tone:");
    final private Label scalingCaption = new Label("Scaling Factor:");
    final private Label opacityValue = new Label(Double.toString(opacityLevel.getValue()));
    final private Label sepiaValue = new Label(Double.toString(sepiaTone.getValue()));
    final private Label scalingValue = new Label(Double.toString(scaling.getValue()));
    
    final private static Color textColor = Color.WHITE;
    final private static SepiaTone sepiaEffect = new SepiaTone() {
    	{
    		setLevel(0);
    	}
    };
        
	private Parent customContent() {
		
		//Image--------------------------------------------------------------------------
		
		final ImageView iv = new ImageView(cap);
		iv.setEffect(sepiaEffect);
		GridPane.setConstraints(iv, 0, 0);
		GridPane.setColumnSpan(iv, 3);
		
		//Opacity--------------------------------------------------------------------------
		
		opacityCaption.setTextFill(textColor);
        GridPane.setConstraints(opacityCaption, 0, 1);
        
        opacityLevel.valueProperty().addListener((ov, alt, neu) -> {
        	iv.setOpacity(neu.doubleValue());
        	opacityValue.setText(String.format("%.2f", neu));
        });
        GridPane.setConstraints(opacityLevel, 1, 1);
        
        opacityValue.setTextFill(textColor);
        GridPane.setConstraints(opacityValue, 2, 1);
		
        //Sepia--------------------------------------------------------------------------
        
        sepiaCaption.setTextFill(textColor);
        GridPane.setConstraints(sepiaCaption, 0, 2);
        
        sepiaTone.valueProperty().addListener((ov, alt, neu) -> {
        	sepiaEffect.setLevel(neu.doubleValue());
        	sepiaValue.setText(String.format("%.2f", neu));
        });
        GridPane.setConstraints(sepiaTone, 1, 2);
        
        sepiaValue.setTextFill(textColor);
        GridPane.setConstraints(sepiaValue, 2, 2);
        
        //Scale--------------------------------------------------------------------------
        
        scalingCaption.setTextFill(textColor);
        GridPane.setConstraints(scalingCaption, 0, 3);
        
        scaling.valueProperty().addListener((ov, alt, neu) -> {
        	iv.setScaleX(neu.doubleValue());
        	iv.setScaleY(neu.doubleValue());
        	scalingValue.setText(String.format("%.2f", neu));
        });
        GridPane.setConstraints(scaling, 1, 3);
        
        scalingValue.setTextFill(textColor);
        GridPane.setConstraints(scalingValue, 2, 3);
        
        //Grid--------------------------------------------------------------------------
        
        GridPane g = new GridPane();
		g.setPadding(new Insets(10));
		g.setVgap(10);
		g.setHgap(70);        
		g.getChildren().addAll(iv, 
				opacityCaption, 
				opacityLevel, 
				opacityValue, 
				sepiaCaption, 
				sepiaTone, 
				sepiaValue,
				scalingCaption,
				scaling,
				scalingValue);
		
		StackPane root = new StackPane();
		
		Rectangle rec = new Rectangle();
		rec.heightProperty().bind(g.heightProperty());
		rec.widthProperty().bind(g.widthProperty());	
		rec.setFill(Color.BLACK);
		
		root.getChildren().addAll(rec, g);
		
		return root;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = new Scene(customContent());
		primaryStage.setScene(sc);
		primaryStage.sizeToScene();
		primaryStage.setTitle("Slider");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
