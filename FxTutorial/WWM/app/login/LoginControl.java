package app.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import app.ParentFactory;

@SuppressWarnings("javadoc") // TODO
public class LoginControl
{
	private Stage root;
	private ParentFactory factory;
	public void setFactory(ParentFactory factory) { this.factory = factory; }

	public LoginControl() {
		super();
	}

	public LoginControl(Stage root) {
		super();
		this.root = root;
	}

	@FXML
	public void initialize() {
	}

	@FXML
	public void onStart(ActionEvent event) {
		this.root.getScene().setRoot(this.factory.getGameParent(ParentFactory.GAME));
		event.consume();
	}

}
