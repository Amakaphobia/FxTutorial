package oracleUIControls.ajProgress;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {

	private Parent customContent1() {
		
		final Float[] values = new Float[] {-1.0f, 0f, 0.6f, 1.0f};		
		final Label [] labels = new Label[values.length];		
		final ProgressBar[] pbs = new ProgressBar[values.length];
		final ProgressIndicator[] pins = new ProgressIndicator[values.length];
		final HBox hbs [] = new HBox [values.length];
		
		for (int i = 0; i < values.length; i++) {
            final Label label = labels[i] = new Label();
            label.setText("progress:" + values[i]);
 
            final ProgressBar pb = pbs[i] = new ProgressBar();
            pb.setProgress(values[i]);
 
            final ProgressIndicator pin = pins[i] = new ProgressIndicator();
            pin.setProgress(values[i]);
            final HBox hb = hbs[i] = new HBox();
            hb.setSpacing(5);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(label, pb, pin);
        }
		
		final VBox vb = new VBox();
        vb.setSpacing(5);
        vb.getChildren().addAll(hbs);
		
        Group root = new Group();
        root.getChildren().add(vb);
        return root;
	}

	private Parent customContent2() {

		final Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(50);
                 
        final ProgressBar pb = new ProgressBar(0);
        pb.progressProperty().bind(
        		slider.valueProperty()
        			.divide(50));
        
        final ProgressIndicator pi = new ProgressIndicator(0);
        pi.progressProperty().bind(
        		slider.valueProperty()
        			.divide(50));
        
        
        final HBox hb = new HBox();
        hb.setPadding(new Insets(10));
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(slider, pb, pi);
	        
		Group root = new Group();
        root.getChildren().add(hb);
        return root;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Progress");
		primaryStage.show();
		int count = 0;
		boolean usedA = false;
		LocalDateTime last = null;
		LocalDateTime now = null;
		
		do {
			now = LocalDateTime.now();
			if(last == null || last.until(now, ChronoUnit.SECONDS) >= 1) {
				if(!usedA) {
					primaryStage.setScene(new Scene(customContent1()));
					count += 1;
				}else {
					primaryStage.setScene(new Scene(customContent2()));
					count += 1;
				}
				last = LocalDateTime.now();
				usedA = !usedA;
			}
			
		}while(count < 9);
	}

	

	public static void main(String[] args) {
		launch();
	}

}
