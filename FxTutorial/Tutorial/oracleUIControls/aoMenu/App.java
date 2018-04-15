package oracleUIControls.aoMenu;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {
	
	final String[] viewOptions = new String[] {
	        "Title", 
	        "Binomial name", 
	        "Picture", 
	        "Description"
	    };
	
	@SuppressWarnings("unchecked")
	final Entry<String, Effect>[] effects = new Entry[] {
	        new SimpleEntry<String, Effect>("Sepia Tone", new SepiaTone()),
	        new SimpleEntry<String, Effect>("Glow", new Glow()),
	        new SimpleEntry<String, Effect>("Shadow", new DropShadow())
	    };
	
	final Label name = new Label();
    final Label binName = new Label();
    final Label description = new Label();
    final Rectangle rec = new Rectangle(40,40);
    private int curIndex = -1;
 
	private Parent Test() {
		name.setFont(new Font("Verdana Bold", 22));
        binName.setFont(new Font("Arial Italic", 10));
        description.setWrapText(true);
        description.setTextAlignment(TextAlignment.JUSTIFY);
 
        shuffle();
        
        VBox innerVb = new VBox();
        innerVb.setAlignment(Pos.CENTER);
        innerVb.setSpacing(10);
        innerVb.setPadding(new Insets(0, 10, 0, 10));
        innerVb.getChildren().addAll(name, binName, rec, description);
        
	 	MenuBar menuBar = new MenuBar();
	 	
        // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem add = new MenuItem("Shuffle");
        add.setOnAction(t -> {
                shuffle();
                innerVb.setVisible(true);
        });        
        
        MenuItem clear = new MenuItem("Clear");
        clear.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        clear.setOnAction(t -> 
        	innerVb.setVisible(false));
        
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(t ->
            System.exit(0));
     
        menuFile.getItems().addAll(add, clear, new SeparatorMenuItem(), exit);
        
        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");
 
        //Menu Effect
        Menu menuEffect = new Menu("Picture Effect");
        final ToggleGroup groupEffect = new ToggleGroup();
        for (Entry<String, Effect> effect : effects) {
            RadioMenuItem itemEffect = new RadioMenuItem(effect.getKey());
            itemEffect.setUserData(effect.getValue());
            itemEffect.setToggleGroup(groupEffect);
            menuEffect.getItems().add(itemEffect);
        }
        //No Effects menu
        final MenuItem noEffects = new MenuItem("No Effects");
	         noEffects.setOnAction(t -> {
	                 rec.setEffect(null);
	                 groupEffect.getSelectedToggle().setSelected(false);
	        });
	    noEffects.setDisable(true);
        //Processing menu item selection
        groupEffect.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
	        if (groupEffect.getSelectedToggle() != null) {
	            Effect effect = 
	                (Effect) groupEffect.getSelectedToggle().getUserData();
	            rec.setEffect(effect);
                noEffects.setDisable(false);
	        }
                
         });
        
        //Adding items to the Edit menu
        menuEdit.getItems().addAll(menuEffect, noEffects);
        
        // --- Menu View
        Menu menuView = new Menu("View");
        
        CheckMenuItem titleView = createMenuItem ("Title", name);                                                       
        CheckMenuItem binNameView = createMenuItem ("Binomial name", binName);        
        CheckMenuItem picView = createMenuItem ("Picture", rec);        
        CheckMenuItem descriptionView = createMenuItem ("Description", description);     
        menuView.getItems().addAll(titleView, binNameView, picView, descriptionView);
 
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        
        

        VBox vb = new VBox();
        vb.getChildren().addAll(menuBar, innerVb);
        
		return vb;
	}

	private static CheckMenuItem createMenuItem (String title, final Node node){
	    CheckMenuItem cmi = new CheckMenuItem(title);
	    cmi.setSelected(true);
	    cmi.selectedProperty().addListener((ov, oldVal, newVal) -> {
	            node.setVisible(newVal);
	    });
	    return cmi;
	}
	
	private void shuffle() {
        int i = curIndex;
        while (i == curIndex) {
            i = (int) (Math.random() * pages.length);
        }
        rec.setFill(pages[i].image);
        name.setText(pages[i].name);
        binName.setText("(" + pages[i].binNames + ")");
        description.setText(pages[i].description);
        curIndex = i;
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = new Scene(Test());
		sc.setFill(Color.OLDLACE);
		
		primaryStage.setScene(sc);
		primaryStage.setWidth(350);
		primaryStage.setHeight(400);
		primaryStage.setTitle("Menu");
		primaryStage.show();
	}
	
	final PageData[] pages = new PageData[] {
	        new PageData("Apple",
	            "The apple is the pomaceous fruit of the apple tree, species Malus "
	            + "domestica in the rose family (Rosaceae). It is one of the most "
	            + "widely cultivated tree fruits, and the most widely known of "
	            + "the many members of genus Malus that are used by humans. "
	            + "The tree originated in Western Asia, where its wild ancestor, "
	            + "the Alma, is still found today.",
	            "Malus domestica",
	            "red"),
	        new PageData("Hawthorn",
	            "The hawthorn is a large genus of shrubs and trees in the rose "
	            + "family, Rosaceae, native to temperate regions of the Northern "
	            + "Hemisphere in Europe, Asia and North America. "
	            + "The name hawthorn was "
	            + "originally applied to the species native to northern Europe, "
	            + "especially the Common Hawthorn C. monogyna, and the unmodified "
	            + "name is often so used in Britain and Ireland.",
	            "Crataegus monogyna",
	            "green"),
	        new PageData("Ivy",
	            "The ivy is a flowering plant in the grape family (Vitaceae) native to"
	            + " eastern Asia in Japan, Korea, and northern and eastern China. "
	            + "It is a deciduous woody vine growing to 30 m tall or more given "
	            + "suitable support,  attaching itself by means of numerous small "
	            + "branched tendrils tipped with sticky disks.",
	            "Parthenocissus tricuspidata",
	            "yellow"),
	        new PageData("Quince",
	            "The quince is the sole member of the genus Cydonia and is native to "
	            + "warm-temperate southwest Asia in the Caucasus region. The "
	            + "immature fruit is green with dense grey-white pubescence, most "
	            + "of which rubs off before maturity in late autumn when the fruit "
	            + "changes color to yellow with hard, strongly perfumed flesh.",
	            "Cydonia oblonga",
	            "blue")
	    };

    private class PageData {
        public String name;
        public String description;
        public String binNames;
        public Color image;
        
        public PageData(String name, String description, String binNames, String ColorName) {
            this.name = name;
            this.description = description;
            this.binNames = binNames;
            this.image = Color.web(ColorName);
        }
    }

	public static void main(String[] args) { launch(); }


}
