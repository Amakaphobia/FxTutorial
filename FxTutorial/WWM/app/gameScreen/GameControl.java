package app.gameScreen;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import app.gameScreen.punktePanel.PunkteAnzeigeControl;
import app.gameScreen.questionPanel.QuestionControl;
import datastructure.Frage;

@SuppressWarnings("javadoc")// TODO
public class GameControl implements Initializable{

	@FXML private AnchorPane gameScreen;
	@FXML private GridPane questionPanel;
	@FXML private QuestionControl questionPanelController;
	@FXML private VBox punktePanel;
	@FXML private PunkteAnzeigeControl punktePanelController;

	private BooleanProperty playAble = new SimpleBooleanProperty(true);
	public BooleanProperty playAbleProperty() { return this.playAble; }
	public boolean isPlayAble() { return this.playAble.get(); }
	public void setPlayAble(boolean won) { this.playAble.set(won); }

	private BooleanProperty isWon = new SimpleBooleanProperty(false);
	public BooleanProperty isWonProperty() { return this.isWon; }
	public boolean isWon() { return this.isWon.get(); }
	public void setWon(boolean won) { this.isWon.set(won); }

	public GameControl() {
		super();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.questionPanelController.setGameControl(this);
		this.punktePanelController.setGameControl(this);
		this.punktePanelController.punkteProperty().addListener(
			(ov, oldVal, newVal) ->{
				if(newVal.intValue() >= 10)
					this.winGame();
		});

		this.questionPanelController.bindButton(this.isWon);
		this.questionPanelController.bindGrid(this.playAble);
	}

	public void increment(Frage f) {
		this.punktePanelController.increment(f);
	}

	private void winGame() {
		this.isWon.set(true);
		this.playAble.set(false);
		this.endGame();
	}

	public void loseGame() {
		this.playAble.set(false);
		this.punktePanelController.looseGame();
		this.endGame();
	}
	private void endGame() {
		System.out.println("TADADADADA " + this.isWon);
	}
}
