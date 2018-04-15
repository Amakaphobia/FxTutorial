package tile;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

@SuppressWarnings("javadoc")
public class Tile extends StackPane
{
	private Text text;
	private TileState state;
	public void setState(TileState state) {
		this.state = state;
		this.text.setText(state.getType());
		this.text.setFill(state.getForeground());
		this.setBackground(new Background(new BackgroundFill(state.getBackground(), CornerRadii.EMPTY, Insets.EMPTY)));
	}
	public TileState getState() { return this.state; }
	
	public Tile() {
		this.onBeforeInit();
		this.setState(TileState.getRandom());
	}
	
	public Tile(String id, TileState state) {
		this.onBeforeInit();
		this.setState(state);
	}
	
	private void onBeforeInit() {
		this.text = new Text();
		this.getChildren().add(this.text);
	}
	
	@Override
	public String toString() {
		return String.format("Tile: %s",  this.state.toString());
	}
}
