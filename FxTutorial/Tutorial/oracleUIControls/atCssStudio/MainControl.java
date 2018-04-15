package oracleUIControls.atCssStudio;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

@SuppressWarnings("javadoc")
public class MainControl implements Initializable
{
	@FXML MenuItem viewNameMen;
	@FXML MenuItem viewFancyName;
	@FXML MenuItem viewImage;
	@FXML MenuItem viewDescription;

	@FXML RadioMenuItem sepiaMen;
	@FXML RadioMenuItem glowMen;
	@FXML RadioMenuItem shadowMen;
	@FXML RadioMenuItem nullMen;

	@FXML VBox innerVb;

	@FXML Label name;
	@FXML Label binName;
	@FXML Rectangle image;
	@FXML Label description;

	private PageData[] Data = PageData.PAGES;
	private PageData current;


	private ToggleGroup effectGroup;

	public MainControl() {
		super();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.effectGroup = new ToggleGroup();

		this.sepiaMen.setUserData(new SepiaTone(80));
		this.sepiaMen.setToggleGroup(this.effectGroup);
		this.glowMen.setUserData(new Glow(80));
		this.glowMen.setToggleGroup(this.effectGroup);
		this.shadowMen.setUserData(new DropShadow(5, Color.BLUE));
		this.shadowMen.setToggleGroup(this.effectGroup);
		this.nullMen.setUserData(null);
		this.nullMen.setToggleGroup(this.effectGroup);

		this.viewNameMen.setUserData(this.name);
		this.viewFancyName.setUserData(this.binName);
		this.viewImage.setUserData(this.image);
		this.viewDescription.setUserData(this.description);

		this.showRandom();
	}

	@FXML
	private void showRandom() {
		this.shuffle();
		this.displayData();
	}

	@FXML
	public void onClear(ActionEvent event) {
		final boolean test = !this.innerVb.isVisible();

		this.innerVb.setVisible(test);

		MenuItem me = (MenuItem) event.getSource();
		me.setText(test?"Hide":"Show");
	}

	@FXML
	public void onView(ActionEvent event) {
		MenuItem me = (MenuItem) event.getSource();
		Node n = (Node) me.getUserData();
		n.setVisible(!n.isVisible());
	}

	@FXML public void onExit() { System.exit(0);}

	@FXML
	public void onEffect(ActionEvent event) {
		MenuItem me = (MenuItem)event.getSource();
		Effect ef = (Effect)(me).getUserData();
		this.image.setEffect(ef);
		if(ef == null)
			this.nullMen.setDisable(true);
		else
			this.nullMen.setDisable(false);
	}

	private void displayData() {
		this.image.setFill(this.current.getColor());
		this.name.setText(this.current.getName());
		this.binName.setText(this.current.getBinName());
		this.description.setText(this.current.getDescription());
	}

	private void shuffle() {
		PageData newVal;
		do
			newVal = this.Data[(int) (Math.random() * this.Data.length)];
		while(newVal.equals(this.current));
		this.current = newVal;
	}


}
