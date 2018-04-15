package ticketApp;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;

import ticketModel.CalendarTable;
import ticketModel.TimeModel;
import ticketModel.TimeModel.Tag;

@SuppressWarnings("javadoc")
public class MainViewController implements Initializable{

	@FXML private CalendarTable calendart;
	@FXML private Label st1Count;
	@FXML private Label st2Count;
	@FXML private Label st1Cost;
	@FXML private Label st2Cost;
	@FXML private TextField TFCost1;
	@FXML private TextField TFCost2;
	@FXML private TextField TFMP2;
	@FXML private Label calcGesamt;
	@FXML private Label aufforderung;

	public MainViewController() {
		super();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TimeModel model = new TimeModel();
		model.getWochen()
			.forEach(w -> this.calendart.getItems().add(w));

		this.TFCost1.setOnAction(e -> {
			if(!TFCost1.getText().contains("€"))
				TFCost1.setText(TFCost1.getText().concat("€"));
		});

		this.TFCost2.setOnAction(e -> {
			if(!TFCost2.getText().contains("€"))
				TFCost2.setText(TFCost1.getText().concat("€"));
		});

		this.onBtnCalcClicked(new ActionEvent());
	}

	@FXML
	void onBtnChangeClicked(ActionEvent event) {
		event.consume();
	}

	@FXML
	void onBtnEvenClicked(ActionEvent event) {
		this.calendart.getItems()
			.stream()
			.forEach(cw -> cw.toggleEvenWeek());
		this.calendart.refresh();
		event.consume();
	}

	@FXML
	public void onBtnCalcClicked(ActionEvent event) {
		int st1Fahrten = 2 * this.countDaysBy(t -> t.isWorkDay());
		int st2Fahrten = 2 * this.countDaysBy(t -> t.computeSchoolDay());
		this.st1Count.setText(String.valueOf(st1Fahrten));
		this.st2Count.setText(String.valueOf(st2Fahrten));

		double st1TicketsNoetig = Math.ceil(st1Fahrten / 4);
		double st2TicketsNoetig = Math.ceil(st2Fahrten / 4);

		double st1Cost = st1TicketsNoetig * Double.parseDouble(this.TFCost1.getText().substring(0, this.TFCost1.getText().length()-1));
		double st2Cost = st2TicketsNoetig * Double.parseDouble(this.TFCost2.getText().substring(0, this.TFCost2.getText().length()-1));

		this.st1Cost.setText(String.valueOf(st1Cost));
		this.st2Cost.setText(String.valueOf(st2Cost));

		double gesamt = st1Cost + st2Cost;
		this.calcGesamt.setText(String.valueOf(gesamt));

		double monat = Double.parseDouble(this.TFMP2.getText().substring(0, this.TFMP2.getText().length()-1));
		String text = monat < gesamt ? "Monatskarte" : "VierFahrtenKarte";
		this.aufforderung.setText(text);
		this.aufforderung.setEffect(new Glow());

		event.consume();
	}

	private int countDaysBy(Predicate<Tag> countBedingung) {
		final LocalDate jetzt = LocalDate.now();
		return
			this.calendart.getItems()
				.stream()
				.mapToInt(woche ->
					(int) woche.getTage()
						.stream()
						.filter(t -> t.getDatum().compareTo(jetzt) > 0)
						.filter(t -> countBedingung.test(t))
						.count())
				.sum();
	}
}
