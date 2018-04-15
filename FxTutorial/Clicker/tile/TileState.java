package tile;
import java.util.Random;
import java.util.function.Supplier;

import javafx.scene.paint.Color;

@SuppressWarnings("javadoc")
public enum TileState 
{
	A(
		"A",
		Color.RED,
		Color.BLACK,
		() -> getB()),
	B(
		"B",
		Color.BLUE,
		Color.BLACK,
		() -> getC()),
	C(
		"C",
		Color.GREEN,
		Color.BLACK,
		() -> getA());
	
	private String type;
	private Color foreground;
	private Color background;
	private Supplier<TileState> upgrader;
	
	TileState(String type, Color foreground, Color background, Supplier<TileState> upgrader){
		this.type = type;
		this.foreground = foreground;
		this.background = background;
		this.upgrader = upgrader;
	}
	
	public static TileState getRandom() {
		Random rr = new Random();
		int dice = rr.nextInt(3);
		TileState state = null;
		switch(dice) {
			case 0:
				state = A;
				break;
			case 1:
				state = B;
				break;
			case 2:
				state = C;
				break;
		}
		return state;
	}
	
	public String getType() { return this.type; }
	public Color getForeground() { return this.foreground; }
	public Color getBackground() { return this.background; }
	public TileState upgrade() { return this.upgrader.get(); }
	
	private static TileState getA() { return A; }
	private static TileState getB() { return B; }
	private static TileState getC() { return C; }
	
	@Override
	public String toString() {
		return String.format("TileState: %s", this.type);
	}
	
}
