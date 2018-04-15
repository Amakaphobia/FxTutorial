package ticketModel;

import java.time.DayOfWeek;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

@SuppressWarnings({"javadoc","unused"})
public class CalendarTable extends TableView<TimeModel.CalendarWeek>
{
	private TimeModel model;
	private ObservableList<TimeModel.Tag> inc;

	@SuppressWarnings("unchecked")
	public CalendarTable() {
		super();

		Callback
			<TableColumn<TimeModel.CalendarWeek, StackPane>,
			TableCell<TimeModel.CalendarWeek, StackPane>> cb =
				t -> new CalendarCell();

		TableColumn<TimeModel.CalendarWeek, StackPane> colMon = new TableColumn<>("Montag");
		colMon.setCellFactory(cb);
		colMon.setCellValueFactory(e -> this.getCD(e, DayOfWeek.MONDAY));
		colMon.prefWidthProperty().bind(this.widthProperty().divide(7d));

		TableColumn<TimeModel.CalendarWeek, StackPane> colDie = new TableColumn<>("Dienstag");
		colDie.setCellFactory(cb);
		colDie.setCellValueFactory(e -> this.getCD(e, DayOfWeek.TUESDAY));
		colDie.prefWidthProperty().bind(this.widthProperty().divide(7d));

		TableColumn<TimeModel.CalendarWeek, StackPane> colMit = new TableColumn<>("Mittwoch");
		colMit.setCellFactory(cb);
		colMit.setCellValueFactory(e -> this.getCD(e, DayOfWeek.WEDNESDAY));
		colMit.prefWidthProperty().bind(this.widthProperty().divide(6d));

		TableColumn<TimeModel.CalendarWeek, StackPane> colDon = new TableColumn<>("Donnerstag");
		colDon.setCellFactory(cb);
		colDon.setCellValueFactory(e -> this.getCD(e, DayOfWeek.THURSDAY));
		colDon.prefWidthProperty().bind(this.widthProperty().divide(7d));

		TableColumn<TimeModel.CalendarWeek, StackPane> colFre = new TableColumn<>("Freitag");
		colFre.setCellFactory(cb);
		colFre.setCellValueFactory(e -> this.getCD(e, DayOfWeek.FRIDAY));
		colFre.prefWidthProperty().bind(this.widthProperty().divide(7d));

		TableColumn<TimeModel.CalendarWeek, StackPane> colSam = new TableColumn<>("Samstag");
		colSam.setCellFactory(cb);
		colSam.setCellValueFactory(e -> this.getCD(e, DayOfWeek.SATURDAY));
		colSam.prefWidthProperty().bind(this.widthProperty().divide(7.5d));

		TableColumn<TimeModel.CalendarWeek, StackPane> colSon = new TableColumn<>("Sonntag");
		colSon.setCellFactory(cb);
		colSon.setCellValueFactory(e -> this.getCD(e, DayOfWeek.SUNDAY));
		colSon.prefWidthProperty().bind(this.widthProperty().divide(7.5d));

		this.getColumns().addAll(colMon, colDie, colMit, colDon, colFre, colSam, colSon);

		this.setFixedCellSize(30);
		this.prefHeightProperty().bind(
			Bindings.size(this.getItems())
					.multiply(this.getFixedCellSize()));

		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


	}

	public ObjectProperty<StackPane> getCD(CellDataFeatures<TimeModel.CalendarWeek, StackPane> input, DayOfWeek dow){
		TimeModel.CalendarWeek cw = input.getValue();
		TimeModel.Tag tag = null;
		switch(dow) {
		case MONDAY:
			tag = cw.montagProperty().get();
			break;
		case TUESDAY:
			tag = cw.dienstagProperty().get();
			break;
		case WEDNESDAY:
			tag = cw.mittwochProperty().get();
			break;
		case THURSDAY:
			tag = cw.donnerstagProperty().get();
			break;
		case FRIDAY:
			tag = cw.freitagProperty().get();
			break;
		case SATURDAY:
			tag = cw.samstagProperty().get();
			break;
		case SUNDAY:
			tag = cw.sonntagProperty().get();
			break;
		}

		Color c = tag == null? null :tag.getColor();

		Text txt = new Text(tag == null? "" : tag.getName());

		StackPane sp = new StackPane(txt);
		sp.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY	, Insets.EMPTY)));
		return new SimpleObjectProperty<>(sp);
	}

	@Override
    public void resize(double width, double height) {
        super.resize(width, height);
        Pane header = (Pane) this.lookup("TableHeaderRow");
        header.setMinHeight(0);
        header.setPrefHeight(0);
        header.setMaxHeight(0);
        header.setVisible(false);
    }

	public class CalendarCell extends TableCell<TimeModel.CalendarWeek, StackPane>{

		public CalendarCell() {
			super();
			this.getStyleClass().add("ccell");
		}
		@Override
		protected void updateItem(StackPane item, boolean empty) {
			super.updateItem(item, empty);
			this.setGraphic(item);
		}
	}
}
