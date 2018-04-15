package datastructure;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SuppressWarnings("javadoc") // TODO
public class Frage {

	public static final List<Frage> FRAGENLISTE = Arrays.asList(
			new Frage("Whats Kaladin", new String[]{"Windrunner", "Edgedancer", "Elsecaller", "Bondsmith"}, 0),
			new Frage("Whats Kelsier", new String[] {"Mistborn", "Ferrochemist", "Twinborn", "Misting"}, 0),
			new Frage("Whats the Lightweavers Name", new String[] {"Jasnah", "Shallan", "Azure", "Vin"}, 1),
			new Frage("How does Lift access investiture", new String[] {"Love", "Hate", "Food", "Color"}, 2),
			new Frage("Who wrote the Diagram", new String[] {"Dalinar", "Navani", "Jasnah", "Taravangian"}, 3)
	);

	private String frage;
	public String getFrage() { return this.frage; }

	private int money;
	public int getMoney()  { return this.money; }

	private final Antwort[] antworten = new Antwort[4];
	public Antwort[] getAntworten() { return this.antworten; }

	public Frage(String frage, String[] antworten, int correctIndex, int money) {
		this.initializeStrings(frage, antworten, correctIndex);
		this.money = money;
	}

	public Frage(String frage, String[] antworten, int correctIndex) {
		super();
		this.initializeStrings(frage, antworten, correctIndex);
		Random rr = new Random();
		this.money = rr.nextInt(100);
	}

	private void initializeStrings(String frage, String[] antworten, int correctIndex) {
		this.frage = frage;
		boolean isCorrect = false;
		for(int i = 0; i<antworten.length; i++)	{
			isCorrect = i == correctIndex;
			this.antworten[i] = new Antwort(antworten[i], isCorrect);
		}
	}

	@Override
	public String toString() {
		return String.format(
			"%s \n%s \n%s \n%s \n%s \n%s",
			this.frage,
			this.antworten[0],
			this.antworten[1],
			this.antworten[2],
			this.antworten[3],
			this.money);
	}
}
