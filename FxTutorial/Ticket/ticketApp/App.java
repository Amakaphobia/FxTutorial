package ticketApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(this.getClass().getResource("TicketMainView.fxml"));
		Scene sc = new Scene(root);
		stage.setScene(sc);
		stage.setTitle("Preis Berechnung");
		stage.show();
	}

}
