package app.gameScreen.questionPanel.text;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

@SuppressWarnings("javadoc")
public class QuestionPanelController implements Initializable{

	@FXML private StackPane textPanel;
	@FXML private PointyBackGround backGroundPoly;
	@FXML private Label textLabel;

	private final StringProperty text = new SimpleStringProperty();
	public final StringProperty textProperty() { return this.text; }
	public final String getText() { return this.textProperty().get(); }
	public final void setText(final String text) { this.textProperty().set(text); }

	public QuestionPanelController() {
		super();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.textLabel.textProperty().bind(this.text);
	}
}
