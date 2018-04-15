package app.gameScreen.punktePanel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import app.gameScreen.GameControl;
import datastructure.Frage;

@SuppressWarnings("javadoc")	// TODO
public class PunkteAnzeigeControl implements Initializable{

	@FXML private VBox punktePanel;
	@FXML private Label wonMoney;

	private List<Label> labels;

	private GameControl gc;
	public void setGameControl(GameControl gc) { this.gc = gc; }
	public GameControl getGameControl() { return this.gc; }

	private IntegerProperty punkte;
	public int getPunkte() { return this.punkte.get(); }
	public IntegerProperty punkteProperty() { return this.punkte; }

	private IntegerProperty money;

	public int getMoney() { return this.money.get(); }

	public PunkteAnzeigeControl() {
		super();
		this.labels = new ArrayList<>();

		this.punkte = new SimpleIntegerProperty(0);
		this.punkte.addListener(
			(ov, oldVal, newVal) -> {
				this.labels.get(9-oldVal.intValue())
					.getStyleClass()
					.add("PunkteSchildDone");
		});
		this.money = new SimpleIntegerProperty(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.labels.addAll(
			IntStream.rangeClosed(0, 9)
				.map(i -> 10 - i)
				.mapToObj(i -> {
					Label l = new Label(String.valueOf(i));
					l.getStyleClass().add("PunkteSchildOpen");
					return l;
				})
				.collect(Collectors.toList()));

		this.punktePanel.getChildren().addAll(this.labels);
		this.money.addListener(
			(ov, oldVal, newVal) -> this.wonMoney.setText("Geld: " +newVal));
	}

	public void looseGame() {
		this.labels.stream()
			.forEach(l -> l.getStyleClass().add("PunkteSchildWrong"));
	}

	public void increment(Frage frage) {
		this.punkte.set(this.punkte.get() + 1);
		this.money.set(this.money.get() + frage.getMoney());
	}
}
