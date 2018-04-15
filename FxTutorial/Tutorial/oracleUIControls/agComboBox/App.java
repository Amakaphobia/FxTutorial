package oracleUIControls.agComboBox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {

	private String address = "";
	
	private Parent customContent() {
		//email list
		final ComboBox<String> cB= new ComboBox<>();
		cB.getItems().addAll(
			"jacob.smith@example.com",
            "isabella.johnson@example.com",
            "ethan.williams@example.com",
            "emma.jones@example.com",
            "michael.brown@example.com");
		
		 cB.setPromptText("Email address");
	     cB.setEditable(true);        
	     cB.valueProperty().addListener((observable, oldValue, newValue) -> {
					address = newValue;
	     });
		
		//priolist
		final ComboBox<String> priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().addAll(
            "Highest",
            "High",
            "Normal",
            "Low",
            "Lowest",
            null);
        priorityComboBox.setValue("Normal");
        priorityComboBox.setCellFactory((LV) -> {
        	return new ListCell<String>() {
        		{
        			//was geht hier ab?
        			super.setPrefWidth(100);
        		}
        		@Override
        		protected void updateItem(String item, boolean empty) {
        			if(item == null) return;
        			
        			super.updateItem(item, empty);
        				this.setText(item);
        				if(item.contains("High"))
        					this.setTextFill(Color.RED);
        				else if(item.contains("Low"))
        					this.setTextFill(Color.GREEN);
        				else
        					this.setTextFill(Color.BLACK);	
        		}
        	};
        });
        
        //kram
        final Button button = new Button ("Send");
        final Label notification = new Label ();
        final TextField subject = new TextField("");
        final TextArea text = new TextArea ("");
        
        button.setOnAction(e -> {
                if (cB.getValue() != null && 
                    !cB.getValue().toString().isEmpty())
                {
                	notification.setText("Your message was successfully sent"
                							+ " to " + address);   
                    cB.setValue(null);
                    
                    if(priorityComboBox.getValue() != null && 
                       !priorityComboBox.getValue().toString().isEmpty())
                    			priorityComboBox.setValue(null);
                    
                    subject.clear();
                    text.clear();
                }
                else 
                    notification.setText("You have not selected a recipient!"); 
        });
        
        //layout
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        
        grid.add(new Label("To: "), 0, 0);
        grid.add(cB, 1, 0);
        grid.add(new Label("Priority: "), 2, 0);
        grid.add(priorityComboBox, 3, 0);
        grid.add(new Label("Subject: "), 0, 1);
        grid.add(subject, 1, 1, 3, 1);            
        grid.add(text, 0, 2, 4, 1);
        grid.add(button, 0, 3);
        grid.add (notification, 1, 3, 3, 1);
		
        //Root Node
        Group root = new Group();
        root.getChildren().add(grid);
        
        
		return root;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = new Scene(customContent());
		primaryStage.setTitle("ComboBox");
		primaryStage.setScene(sc);
		primaryStage.show();
		
	}


	public static void main(String[] args) {	launch(); }
}
