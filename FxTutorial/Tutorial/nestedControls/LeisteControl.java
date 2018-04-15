package nestedControls;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

@SuppressWarnings("javadoc")
public class LeisteControl implements Initializable
{
	@FXML
	private VBox LeistenContainer;

	private int current = 0;

	public LeisteControl() {
		super();
	}

	public void setBg() {
		Label toSet =
			(Label)((VBox) this.LeistenContainer
				.getChildren()
				.get(0))
				.getChildren()
				.get(this.current++);
		toSet.setFont(new Font("Hack", 50));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
