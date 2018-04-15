package app.gameScreen.questionPanel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import app.gameScreen.GameControl;
import app.gameScreen.questionPanel.text.QuestionPanelController;
import datastructure.Antwort;
import datastructure.Frage;

@SuppressWarnings("javadoc") // TODO
public class QuestionControl implements Initializable{

	@FXML private StackPane frage;
	@FXML private QuestionPanelController frageController;
	@FXML private StackPane antworta;
	@FXML private QuestionPanelController antwortaController;
	@FXML private StackPane antwortb;
	@FXML private QuestionPanelController antwortbController;
	@FXML private StackPane antwortc;
	@FXML private QuestionPanelController antwortcController;
	@FXML private StackPane antwortd;
	@FXML private QuestionPanelController antwortdController;

	@FXML private Button btnAnswer;
	@FXML private GridPane questionGrid;

	private List<Frage> alleFragen = Frage.FRAGENLISTE;
	private List<StackPane> antwortList = new ArrayList<>();
	private List<QuestionPanelController> controllerList = new ArrayList<>();

	private ObjectProperty<Frage> currentQuestion;
	public Frage getCurrentQuestion() { return this.currentQuestion.get(); }
	public ObjectProperty<Frage> currentQuestionProperty() { return this.currentQuestion; }

	private Antwort loggedIn;

	private GameControl gc;
	public void setGameControl(GameControl gc) { this.gc = gc; }

	public QuestionControl() {
		super();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.antwortList.add(this.antworta);
		this.antwortList.add(this.antwortb);
		this.antwortList.add(this.antwortc);
		this.antwortList.add(this.antwortd);

		this.controllerList.add(this.antwortaController);
		this.controllerList.add(this.antwortbController);
		this.controllerList.add(this.antwortcController);
		this.controllerList.add(this.antwortdController);

		this.currentQuestion = new SimpleObjectProperty<>();
		this.currentQuestion.addListener((ov, oldVal, newVal) -> this.setQuestion(newVal));

		this.currentQuestion.set(this.getRandomQuestion());

		this.questionGrid.setPrefHeight(400);
		this.questionGrid.setMinHeight(GridPane.USE_PREF_SIZE);
		this.questionGrid.setMaxHeight(GridPane.USE_PREF_SIZE);
		this.questionGrid.setPrefWidth(800);
		this.questionGrid.setMinWidth(GridPane.USE_PREF_SIZE);
		this.questionGrid.setMaxWidth(GridPane.USE_PREF_SIZE);
	}

	public void bindButton(BooleanProperty binding) { this.btnAnswer.disableProperty().bind(binding); }
	public void bindGrid(BooleanProperty binding) { this.questionGrid.visibleProperty().bind(binding); }

	private void setQuestion(Frage neueFrage) {
		StackPane sp;
		QuestionPanelController qpc;
		Antwort a;
		for(int i= 0; i< this.antwortList.size(); i++) {
			sp = this.antwortList.get(i);

			a = neueFrage.getAntworten()[i];
			sp.setUserData(a);
			qpc = this.controllerList.get(i);
			qpc.setText(a.getText());
		}

		this.frageController.setText(neueFrage.getFrage());
	}

	@FXML
	public void onEnter(ActionEvent e) {
		if(this.loggedIn == null) return;

		if(this.loggedIn.isCorrect())
			this.onCorrect();
		else
			this.onWrong();
		this.loggedIn = null;
		e.consume();
	}

	private void onWrong() {
		this.gc.loseGame();
	}

	private void onCorrect() {
		this.gc.increment(this.currentQuestion.get());
		this.currentQuestion.set(this.getRandomQuestion());
	}

	private Frage getRandomQuestion() {
		int size = this.alleFragen.size();
		if(size < 1) return null;

		Random rr = new Random();
		int index = rr.nextInt(size);
		return this.alleFragen.get(index);
	}

	@FXML
	public void onAnswerClicked(MouseEvent event) {
		if(this.gc.isPlayAble()) {
			if(event.getButton() != MouseButton.PRIMARY) return;

			StackPane l = (StackPane) event.getSource();
			this.loggedIn = (Antwort) l.getUserData();
		}
		event.consume();
	}

}
