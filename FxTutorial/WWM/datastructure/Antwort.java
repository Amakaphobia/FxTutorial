package datastructure;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("javadoc")
public class Antwort {

	private StringProperty text;
	public StringProperty textProperty() { return this.text; }
	public String getText() { return this.text.get(); }
	public void setText(String text) { this.text.set(text); }

	private boolean isCorrect;
	public boolean isCorrect() { return this.isCorrect; }

	public Antwort(String text, boolean isCorrect) {
		super();
		this.text = new SimpleStringProperty(text);
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return String.format("%s : %s", this.getText(), this.isCorrect);
	}


}
