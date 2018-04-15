package oracleUIControls.adColorList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

@SuppressWarnings("javadoc")
public class ColorList extends VBox
{
	private static final ObservableList<String> DATA = FXCollections.observableArrayList(
            "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");
	
	private final ListView<String> liste;
	private final Label chosenText;
	private final ObjectProperty<Color> chosen;
	
	public ColorList() {
		super();
		
		this.chosen = new SimpleObjectProperty<>();
		
		this.liste = new ListView<>();
		this.liste.setItems(DATA);
		this.liste.setCellFactory(e -> new ColorRect());
		VBox.setVgrow(liste, Priority.ALWAYS);
		
		this.getChildren().add(this.liste);
		
		this.chosenText = new Label();
		this.chosenText.textProperty()
			.bind(
				this.liste.getSelectionModel()
					.selectedItemProperty());
		
		this.chosenText.textFillProperty()
			.bind(
				this.chosen);
		
		this.liste.getSelectionModel().selectedItemProperty().addListener(
			(ov, alt, neu) ->	
				this.chosen.set(Color.web(neu)));
				
		
		this.getChildren().add(this.chosenText);
	}
	
	
	private class ColorRect extends ListCell<String>{
		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			
			Rectangle rec = new Rectangle(70,12);
			if(item != null) {
				Color c = Color.web(item);
				rec.setFill(c);
				setText(item);
				setTextFill(c);
				setGraphic(rec);
			}
		}
	}
}
