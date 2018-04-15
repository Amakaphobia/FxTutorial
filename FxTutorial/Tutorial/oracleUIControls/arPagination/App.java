package oracleUIControls.arPagination;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {

	final String[] textPages = new String[]{
	        "The apple is the pomaceous fruit of the apple tree, species Malus "
	        + "domestica in the rose family (Rosaceae). It is one of the most "
	        + "widely cultivated tree fruits, and the most widely known of "
	        + "the many members of genus Malus that are used by humans. "
	        + "The tree originated in Western Asia, where its wild ancestor, "
	        + "the Alma, is still found today.",
	        "The hawthorn is a large genus of shrubs and trees in the rose family,"
	        + "Rosaceae, native to temperate regions of the Northern Hemisphere "
	        + "in Europe, Asia and North America. The name hawthorn was "
	        + "originally applied to the species native to northern Europe, "
	        + "especially the Common Hawthorn C. monogyna, and the unmodified "
	        + "name is often so used in Britain and Ireland.",
	        "The ivy is a flowering plant in the grape family (Vitaceae) native to "
	        + " eastern Asia in Japan, Korea, and northern and eastern China. "
	        + "It is a deciduous woody vine growing to 30 m tall or more given "
	        + "suitable support,  attaching itself by means of numerous small "
	        + "branched tendrils tipped with sticky disks.",
	        "The quince is the sole member of the genus Cydonia and is native to "
	        + "warm-temperate southwest Asia in the Caucasus region. The "
	        + "immature fruit is green with dense grey-white pubescence, most "
	        + "of which rubs off before maturity in late autumn when the fruit "
	        + "changes color to yellow with hard, strongly perfumed flesh.",
	        "Aster (syn. Diplopappus Cass.) is a genus of flowering plants "
	        + "in the family Asteraceae. The genus once contained nearly 600 "
	        + "species in Eurasia and North America, but after morphologic "
	        + "and molecular research on the genus during the 1990s, it was "
	        + "decided that the North American species are better treated in a "
	        + "series of other related genera. After this split there are "
	        + "roughly 180 species within the genus, all but one being confined "
	        + "to Eurasia."
	    };

	private Pagination p1= new Pagination() {
		{
			this.setStyle("-fx-border-color: red;");
			AnchorPane.setTopAnchor(this, 10d);
			AnchorPane.setRightAnchor(this, 10d);
			AnchorPane.setBottomAnchor(this, 10d);
			AnchorPane.setLeftAnchor(this, 10d);
		}
	};

	private Parent test5() {
		AnchorPane root = (AnchorPane) this.test4();
		this.p1.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

		return root;
	}


	final List<String> fonts = Font.getFamilies();

	@SuppressWarnings("unused")
	private Parent test4() {
		this.p1.setPageCount(this.fonts.size()/this.itemsPerPage4());
		this.p1.setPageFactory(index -> this.createPage4(index));

		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(this.p1);
		return root;
	}

	private int itemsPerPage4() {
		return 15;
	}

	public VBox createPage4(int pageIndex) {
        VBox box = new VBox(5);
        box.setPadding(new Insets(5));

        int page = pageIndex * this.itemsPerPage4();
        for (int i = page; i < page + this.itemsPerPage4(); i++) {
            Label font = new Label(this.fonts.get(i));
            box.getChildren().add(font);
        }
        return box;
    }

	@SuppressWarnings("unused")
	private Parent test3() {
		this.p1.setPageCount(this.textPages.length);
		this.p1.setPageFactory(index -> {
			if(index >= this.textPages.length)
				return null;
			else
				return this.createPage3(index);
		});

		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(this.p1);
		return root;
	}

	private VBox createPage3(int pageIndex) {
		VBox box = new VBox(5);
        int page = pageIndex * this.itemsPerPage3();
        for (int i = page; i < page + this.itemsPerPage3(); i++) {
            TextArea text = new TextArea(this.textPages[i]);
            text.setWrapText(true);
            box.getChildren().add(text);
        }
        return box;
	}


	private int itemsPerPage3() {
		return 1;
	}

	@SuppressWarnings("unused")
	private Parent test2() {
		this.p1.setPageFactory((index) -> this.createPage2(index));
		this.p1.setPageCount(28);

		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(this.p1);
		return root;

	}

	private VBox createPage2(int pageIndex) {
		VBox box = new VBox(5);
        int page = pageIndex * this.itemsPerPage2();
        for (int i = page; i < page + this.itemsPerPage2(); i++) {
            VBox element = new VBox();
            element.setPadding(new Insets(5));
            element.setStyle("-fx-border-color: blue;");
            Hyperlink link = new Hyperlink("Item " + (i+1));
            link.setVisited(true);
            Label text = new Label("Search results\nfor "+ link.getText());
            element.getChildren().addAll(link, text);
            box.getChildren().add(element);
        }
        return box;
	}
	private int itemsPerPage2() {
		return 8;
	}

	final Pagination pagination = new Pagination(5, 1);

	@SuppressWarnings("unused")
	private Parent test1() {
		return this.pagination;
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {

//		Scene sc = new Scene(test2());
//		Scene sc = new Scene(test3());
		Scene sc = new Scene(this.test5());
//		sc.setFill(Color.OLDLACE);

		sc.getStylesheets().add("oracleUIControls/arPagination/css.css");
		primaryStage.setScene(sc);
//		primaryStage.setWidth(350);
//		primaryStage.setHeight(400);
		primaryStage.setTitle("Paginations");
		primaryStage.show();
	}

	public static void main(String[] args) { launch(); }



}
