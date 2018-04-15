package oracleUIControls.atCssStudio;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

@SuppressWarnings("javadoc")
public class PageData {

    private StringProperty name;
    public StringProperty nameProperty() { return this.name; }
    public String getName() { return this.name.get(); }
    public void setName(String name) { this.name.set(name); }

    private StringProperty description;
    public StringProperty descriptionProperty() { return this.description; }
    public String getDescription() { return this.description.get(); }
    public void setDescription(String description) { this.description.set(description); }

    private StringProperty binName;
    public StringProperty binNameProperty() { return this.binName; }
    public String getBinName() { return this.binName.get(); }
    public void setBinName(String binName) { this.binName.set(binName); }

    private ObjectProperty<Color> color;
    public ObjectProperty<Color> colorProperty() { return this.color; }
    public Color getColor() { return this.color.get(); }
    public void setColor(Color color) { this.color.set(color); }

    public PageData(String name, String description, String binNames, String ColorName) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.binName = new SimpleStringProperty(binNames);
        this.color = new SimpleObjectProperty<>(Color.web(ColorName));
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj == this) return true;
    	if(obj == null) return false;
    	if(!(obj instanceof PageData)) return false;

    	return this.name.get().equals(((PageData)obj).name.get());
    }


    public static final PageData[] PAGES = new PageData[] {
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
}
