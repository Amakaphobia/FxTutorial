package nestedControls;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

@SuppressWarnings("javadoc")
public class MainControl implements Initializable
{
	@FXML private VBox leiste;
	@FXML private LeisteControl leisteController;

	public MainControl() {
		super();
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		this.leisteController = new LeisteControl();

	}



	@FXML public void hauRein(ActionEvent event) {
		this.leisteController.setBg();
	}

}
