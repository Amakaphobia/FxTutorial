package oracleUIControls.aeTable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

@SuppressWarnings("javadoc")
public class Table extends Group
{
	public class Person{
		private final SimpleStringProperty firstName;
	    private final SimpleStringProperty lastName;
	    private final SimpleStringProperty email;
	 
	    private Person(String fName, String lName, String email) {
	        this.firstName = new SimpleStringProperty(fName);
	        this.lastName = new SimpleStringProperty(lName);
	        this.email = new SimpleStringProperty(email);
	    }
	 
	    public String getFirstName() {
	        return firstName.get();
	    }
	    public void setFirstName(String fName) {
	        firstName.set(fName);
	    }
	        
	    public String getLastName() {
	        return lastName.get();
	    }
	    public void setLastName(String fName) {
	        lastName.set(fName);
	    }
	    
	    public String getEmail() {
	        return email.get();
	    }
	    public void setEmail(String fName) {
	        email.set(fName);
	    }
	    
	    @Override
	    public String toString() {
	    	return String.format("Person: %s %s (Email: %s)", this.getFirstName(), this.getLastName(), this.getEmail());
	    }
	}
	
	public Table() {	
		
		final Label label = new Label("Adressbuch");
		label.setFont(new Font("Hack", 20));
		
		TableView<Person> table = new TableView<>();
		table.setEditable(true);
		table.setMaxWidth(350);
		table.setPrefSize(350,500);
		
	    Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory = p -> new EditingCell();

		TableColumn<Person, String> tabFirstNameCol = new TableColumn<>("First Name");
		TableColumn<Person, String> tabLastNameCol = new TableColumn<>("Last Name");
		TableColumn<Person, String> tabEmailCol = new TableColumn<>("Email");
		
		tabFirstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		
		//tabFirstNameCol.setCellFactory(TextFieldTableCell.forTableColumn()); 		
		//Use this for commit on focus change
		tabFirstNameCol.setCellFactory(cellFactory);
		
		tabFirstNameCol.setOnEditCommit(e -> 
			e.getTableView()
			 .getItems()
			 .get(
				e.getTablePosition()
				 .getRow()
			).setFirstName(e.getNewValue()));
		
		
		
		tabLastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
//		tabLastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		tabLastNameCol.setCellFactory(cellFactory);
		tabLastNameCol.setOnEditCommit(e -> 
			e.getTableView()
			 .getItems()
			 .get(
				e.getTablePosition()
				 .getRow()
			).setLastName(e.getNewValue()));
		
		tabEmailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
//		tabEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		tabEmailCol.setCellFactory(cellFactory);
		tabEmailCol.setOnEditCommit(e -> 
			e.getTableView()
			 .getItems()
			 .get(
				e.getTablePosition()
				 .getRow()
			).setEmail(e.getNewValue()));
		
		table.getColumns().add(tabFirstNameCol);
		table.getColumns().add(tabLastNameCol);
		table.getColumns().add(tabEmailCol);
		
		ObservableList<Person> Data = this.buildList();
		table.setItems(Data);

		TextField enterFirstName = new TextField();
		enterFirstName.setPrefColumnCount(10);
		enterFirstName.setPromptText("First Name");
		
		TextField enterLastName = new TextField();
		enterLastName.setPrefColumnCount(10);
		enterLastName.setPromptText("Last Name");
		
		TextField enterEmail = new TextField();
		enterEmail.setPrefColumnCount(10);
		enterEmail.setPromptText("Email");
		
		Button btnAddPerson = new Button("Add Person");
		btnAddPerson.setOnAction(e -> {
			if(enterFirstName.getText().isEmpty() || enterLastName.getText().isEmpty() || enterEmail.getText().isEmpty())
				return;
			Data.add(new Person(enterFirstName.getText(), enterLastName.getText(), enterEmail.getText()));
		});
		
		Button btnPrintData = new Button("Print");
		btnPrintData.setOnAction(e -> {
			Person p = table.getSelectionModel().getSelectedItem();
			System.out.println(p != null ? p : "Harry Potter");
		});
		
		HBox hb = new HBox();
		hb.setSpacing(5);
		hb.setPadding(new Insets(10,10,10,10));
		hb.getChildren().addAll(enterFirstName, enterLastName, enterEmail, btnAddPerson, btnPrintData);
		
		VBox vb = new VBox();
		vb.setSpacing(5);
		vb.setPadding(new Insets(10,10,10,10));
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(label, table, hb);
		
		this.getChildren().add(vb);
	}
	
	private ObservableList<Person> buildList(){
		return FXCollections.observableArrayList(
					new Person("Harry","Dresden","hd@wiz.de"),
					new Person("Kaladin", "Stormblessed", "windrunner@honor.de"),
					new Person("Jasnah", "Kholin","angry@Scientist.de")
				);
	}
	
	class EditingCell extends TableCell<Person, String> {
		 
        private TextField textField;
 
        public EditingCell() {
        }
 
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText(getItem());
            setGraphic(null);
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
 
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                }
            });
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}

