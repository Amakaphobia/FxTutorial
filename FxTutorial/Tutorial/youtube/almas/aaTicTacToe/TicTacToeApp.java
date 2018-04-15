package youtube.almas.aaTicTacToe;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("javadoc")
public class TicTacToeApp extends Application {
	
	private final static int SIZEX = 600;
	private final static int SIZEY = 600;

	private final static int ROWCOUNT = 3;
	private final static int COLUMNCOUNT = 3;
	
	private Pane root;
	
	private List<Combo> combos = new ArrayList<>();
	private Tile[][] board = new Tile[COLUMNCOUNT][ROWCOUNT];
	
	private boolean turnX = true;
	private boolean playable = true;
	
	@Override
	public void start(Stage stage) throws Exception {
		generateCustomContent();
		stage.setTitle("TicTacToe");
		stage.setScene(new Scene(this.root));
		combos = generateCombos();
		stage.show();
	}
	
	private Optional<Combo> checkForWin() {
		return
			combos.stream()
				.filter(Combo::isComplete)
				.findAny();
	}
	
	private void playWinAnimation(Combo Winner) {
		Line line = new Line();
		this.root.getChildren().add(line);
		line.setStartX(Winner.tiles[0].getCenterX());
		line.setStartY(Winner.tiles[0].getCenterY());
		
		line.setEndX(Winner.tiles[0].getCenterX());
		line.setEndY(Winner.tiles[0].getCenterY());
		
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(
			new KeyFrame(
				Duration.seconds(1.5),
				new KeyValue(
					line.endXProperty(), 
					Winner.tiles[2].getCenterX()),
				new KeyValue(
					line.endYProperty(), 
					Winner.tiles[2].getCenterY())
			)
		);
		tl.play();
	}
	
	private class Combo{
		private Tile[] tiles;
		
		public Combo(Tile...tiles ) {
			this.tiles = tiles;
		}
		
		public boolean isComplete() {
			if(tiles[0].getValue().isEmpty()) return false;
			
			return tiles[0].equalValue(tiles[1]) && tiles[0].equalValue(tiles[2]);
		}
	}

	private class Tile extends StackPane{
		private Text text;
		
		public Tile() {
			text = new Text();
			text.setFont(Font.font(72));
			
			Rectangle border = new Rectangle(SIZEX/COLUMNCOUNT, SIZEY/ROWCOUNT);
			border.setFill(null);
			border.setStroke(Color.BLACK);
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(border, text);
			
			setOnMouseClicked(e ->{
				if(!playable) return;
				
				if(e.getButton() == MouseButton.PRIMARY && turnX) {
					drawX();
					turnX = false;
				}else if(e.getButton() == MouseButton.SECONDARY && !turnX) {
					drawO();
					turnX = true;
				}
				
				checkForWin()
					.ifPresent(com -> {
						playable = false;
						playWinAnimation(com);
					});
			});
		}
		
		public Tile(int x, int y) {
			this();
			this.setTranslateX(x);
			this.setTranslateY(y);
		}
		
		public boolean equalValue(Tile other) {
			return this.text.getText().equals(other.text.getText());
		}
		
		public double getCenterX() {
			return getTranslateX() + (getWidth()/2);
		}
		public double getCenterY() {
			return getTranslateY() + (getHeight()/2);
		}
		
		public String getValue() { return this.text.getText(); }
		private void drawX() { text.setText("X"); }
		private void drawO() { text.setText("O"); }
	}
	
	

	private void generateCustomContent() {
		this.root = new Pane();
		this.root.setPrefSize(SIZEX, SIZEY);

		this.root.getChildren().addAll(
			IntStream.range(0, ROWCOUNT)
				.mapToObj(y ->
					IntStream.range(0, COLUMNCOUNT)
						.mapToObj(x ->{
							Tile t = 
							new Tile(
								x*(SIZEX/COLUMNCOUNT),
								y*(SIZEY/ROWCOUNT));
							
							board[x][y] = t;
							
							return t;
						}))
				.flatMap(ele -> ele)
				.collect(toList()));	
	}
	
	private List<Combo> generateCombos(){
		List<Combo> ll = new ArrayList<>();
		
		//Vertical
		ll.addAll(
			IntStream.range(0, COLUMNCOUNT)
				.mapToObj(x -> new Combo(board[x][0], board[x][1],board[x][2]))
				.collect(toList()));
		//Horizontal
		ll.addAll(
				IntStream.range(0, COLUMNCOUNT)
					.mapToObj(y -> new Combo(board[0][y], board[1][y],board[2][y]))
					.collect(toList()));
		//Diagonal
		ll.add(new Combo(board[0][0], board[1][1],board[2][2]));
		ll.add(new Combo(board[0][2], board[1][1],board[2][0]));
		
		return ll;
	}
	
	public static void main(String[] args) { launch(args); }

}
