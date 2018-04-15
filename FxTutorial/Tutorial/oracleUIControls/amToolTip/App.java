package oracleUIControls.amToolTip;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {
	
	final private static String[] rooms = new String[]{
		        "Accommodation (BB)",
		        "Half Board",
		        "Late Check-out",
		        "Extra Bed"
		    };
    final private static Integer[] rates = new Integer[]{
        100, 20, 10, 30
    };
    final private CheckBox[] cbs = new CheckBox[rooms.length];
    
    private IntegerProperty sum = new SimpleIntegerProperty(0);
    
    final private Label total = new Label("Total: $0") {
    	{
    		setFont(new Font("Arial", 20));
    		textProperty().bind((new SimpleStringProperty("Total: $")).concat(sum.asString()));
    	}
    };
    
    
    

	private Parent customContent1() {
		
		for (int i = 0; i < rooms.length; i++) {
            final CheckBox cb = cbs[i] = new CheckBox(rooms[i]);
            final Integer rate = rates[i];
            final Tooltip tooltip = new Tooltip("$" + rates[i].toString());
            tooltip.setFont(new Font("Arial", 16));
            
            cb.setTooltip(tooltip);
            cb.selectedProperty().addListener((ov, oldVal, newVal) -> {
                    if (cb.isSelected()) {
                    	sum.set(sum.get() + rate);
                    } else {
                    	sum.set(sum.get() - rate);
                    }
            });
        }
		
		VBox vbox = new VBox();
        vbox.getChildren().addAll(cbs);
        vbox.setSpacing(5);
        HBox hbox = new HBox();
        hbox.getChildren().add(vbox);
        hbox.getChildren().add(total);
        hbox.setSpacing(40);
        hbox.setPadding(new Insets(20, 10, 10, 20));
        
        Group root = new Group();
        root.getChildren().add(hbox);
		
		return root;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setScene(new Scene(customContent1()));
		primaryStage.setWidth(300);
        primaryStage.setHeight(150);
		primaryStage.setTitle("ToolTip");
		primaryStage.show();
	}

	public static void main(String[] args) { launch(); }

}
