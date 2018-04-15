package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import tile.Tile;
import tile.TileState;

@SuppressWarnings("javadoc")
public class MainControl implements Initializable 
{

	@FXML private TilePane MainMap;
	@FXML private Text PunkteAnzeige;
	
	private StringProperty PunkteString;
	private int pts;
	
	public MainControl() {
		this.pts = -1;
		this.PunkteString = new SimpleStringProperty();
		this.pointinc();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.PunkteAnzeige.textProperty().bind(this.PunkteString);		
	}

	private void pointinc() {
		this.pts += 1;
		this.PunkteString.setValue(String.valueOf(this.pts));
	}

	@FXML 
	public void handleTileClick(MouseEvent e) {
		Tile t = ((Tile)e.getSource());
		TileState newState = t.getState().upgrade();
		if(newState == TileState.A)
			this.pointinc();
		t.setState(newState);
	}
}
