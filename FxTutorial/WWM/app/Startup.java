package app;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import app.login.LoginControl;

/**
 * Startup Class for WWM.
 * @author David
 *
 */
public class Startup extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception {

		ParentFactory parentFactory = new ParentFactory();
		LoginControl lc = new LoginControl(primaryStage);
		lc.setFactory(parentFactory);

		Parent root = parentFactory.getGameParent(ParentFactory.LOGIN, lc);

		primaryStage.setTitle("Wer wird Millionär");

		primaryStage.setScene(new Scene(root));
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	/**
	 * Main
	 * @param args args
	 */
	public static void main(String[] args) { launch(); }
}
