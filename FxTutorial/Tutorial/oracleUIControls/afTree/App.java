package oracleUIControls.afTree;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class App extends Application {
	
	private ObservableList<TreeItem<TreeItemInt>> colList;
	private ObservableList<Employee> employees;
	private TreeItem<TreeItemInt> rootNode;
	private Employee added;
	
	
	private Parent customContent() {		
		this.rootNode = 
		        new TreeItem<TreeItemInt>(new Department("MyCompany Human Resources"), this.getColoredImage(Color.AQUAMARINE));
		
		rootNode.setExpanded(true);
		this.employees = this.getEmps();
		this.updateTree();
			
		
		TreeView<TreeItemInt> tv = new TreeView<TreeItemInt>(rootNode);
		tv.setEditable(true);
		tv.setCellFactory(treeView -> new TextFieldTreeCellImpl());
		tv.setOnEditCommit(e -> {
			this.updateTree();
			
			if(added != null) {
				rootNode.getChildren().stream()
					.filter(Tii -> Tii.getValue().equals(added.getDep()))
					.findFirst()
					.ifPresent(Tii -> Tii.setExpanded(true));
				added = null;
			}
		});
		
		
		Button btnPrinter = new Button("Print Data");
		btnPrinter.setOnAction(e -> 
			this.employees.stream()
					.forEach(System.out::println));
		
		Button btnToggle = new Button("Toggle");
		btnToggle.setOnAction(e -> rootNode.setExpanded(!rootNode.isExpanded()));
		
		HBox buttonkiste = new HBox();
		buttonkiste.getChildren().addAll(btnPrinter, btnToggle);
		
		VBox Root = new VBox();
		Root.getChildren().addAll(tv, buttonkiste);
		
		return Root;
	}
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Tree");
		Scene sc = new Scene(customContent(),300,250);
		
		stage.setScene(sc);
		
		stage.show();
	}	
		
	private void updateTree() {
		this.colList = 
				FXCollections.observableArrayList(
					this.employees.stream()
						.collect(Collectors.groupingBy(emp -> emp.getDep()))
						.entrySet().stream()
						.map(e -> {
							TreeItem<TreeItemInt> depNode = new TreeItem<>(e.getKey(), this.getColoredImage(Color.FIREBRICK));
							depNode.getChildren().addAll(
								e.getValue().stream()
									.map(emp -> {
										TreeItem<TreeItemInt> ti = new TreeItem<>(emp);
										return ti;
									})
									.collect(toList())
								);					
							return depNode;
						})
						.collect(toList()));
		
		this.rootNode.getChildren().clear();
		this.rootNode.getChildren().addAll(this.colList);
	}
	
	private Node getColoredImage(Color c) {
		Rectangle rec = new Rectangle(10,10);
		rec.setFill(c);
		
		return rec;
	}
	
	private final class TextFieldTreeCellImpl extends TreeCell<TreeItemInt> {
		 
	        private TextField textField;
	        private ContextMenu addMenu = new ContextMenu();
	         
	        public TextFieldTreeCellImpl() {
	            MenuItem addMenuItem = new MenuItem("Add Employee");
	            
	            addMenu.getItems().add(addMenuItem);
	            addMenuItem.setOnAction(t -> {
	            	added = new Employee("New Employee",(Department) getUserData());
	            	employees.add(added);
	            	
                });
	        }
	        
	        @Override
	        public void startEdit() {
	            super.startEdit();
	 
	            if (textField == null) {
	                createTextField();
	            }
	            
	            setText(null);
	            setGraphic(textField);
	            textField.selectAll();
	        }
	 
	        @Override
	        public void cancelEdit() {
	            super.cancelEdit();
	            setText(getItem().getAsString());
	            setGraphic(getTreeItem().getGraphic());
	        }
	 
	        @Override
	        public void updateItem(TreeItemInt item, boolean empty) {
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
	                    setGraphic(getTreeItem().getGraphic());
                    	setUserData(item);
	                    if ( !getTreeItem().isLeaf() && getTreeItem().getParent() != null){
	                    	setContextMenu(addMenu);
	                    }
	                }
	            }
	        }
	        
	        @Override
	        public void commitEdit(TreeItemInt newValue) {
	        	super.commitEdit(newValue);
	        	this.setText(newValue.getAsString());
	        }
	 
	        private void createTextField() {
	            textField = new TextField(getString());
	            textField.setOnKeyReleased(( t) -> {
	                    if (t.getCode() == KeyCode.ENTER) {
	                        commitEdit( (TreeItemInt)getUserData() );
	                        ((TreeItemInt)getUserData()).setTheString(textField.getText());
	                    } else if (t.getCode() == KeyCode.ESCAPE) {
	                        cancelEdit();
	                    }
	                
	            });
	        }
	 
	        private String getString() {
	            return getItem() == null ? "" : getItem().getAsString();
	        }
	    }
	
	private interface TreeItemInt {
		public abstract String getAsString();
		public abstract void setTheString(String s);
	}
	
	private ObservableList<Employee> getEmps(){
		
		ObservableList<Employee> ll =
			FXCollections.observableArrayList(
				 Arrays.<Employee>asList(
		            new Employee("Ethan Williams", "Sales Department"),
		            new Employee("Emma Jones", "Sales Department"),
		            new Employee("Michael Brown", "Sales Department"),
		            new Employee("Anna Black", "Sales Department"),
		            new Employee("Rodger York", "Sales Department"),
		            new Employee("Susan Collins", "Sales Department"),
		            new Employee("Mike Graham", "IT Support"),
		            new Employee("Judy Mayer", "IT Support"),
		            new Employee("Gregory Smith", "IT Support"),
		            new Employee("Jacob Smith", "Accounts Department"),
		            new Employee("Isabella Johnson", "Accounts Department")));
		ll.addListener(new ListChangeListener<Employee>(){
         	@Override
			public void onChanged(Change<? extends Employee> c) {
				updateTree();
				
			}
        });
			  
		 return ll;
	}

	public static class Employee implements TreeItemInt{
		 
        private final SimpleStringProperty name;
        private final Department dep;
        
        private Employee(String name, Department dep) {
        	this.name = new SimpleStringProperty(name);
        	this.dep = dep;
        }
 
        private Employee(String name, String department) {
            this.name = new SimpleStringProperty(name);
            this.dep = new Department(department);
        }
 
        public String getName() { return name.get(); } 
        public void setName(String fName) { name.set(fName); }        
        public Department getDep() { return this.dep; }
//        public void setDep(Department dep) { this.dep = dep; }
        
        @Override
        public boolean equals(Object obj) {
			if(obj == this) return true;
        	if(obj == null) return false;
        	if(!(obj instanceof Employee)) return false;
        	
        	Employee other = (Employee) obj;
        	return this.name.equals(other.name);
        }
        
        @Override
        public int hashCode() {
        	return this.name.get().hashCode();
        }
        
        @Override
        public String toString() {
        	return String.format("%s of %s", this.getName(), this.dep.getDepartment() );
        }

		@Override
		public String getAsString() {
			return this.getName();
		}

		@Override
		public void setTheString(String s) {
			this.setName(s);
		}
    }
	
	public static class Department implements TreeItemInt {

        private final SimpleStringProperty department;
		
        public Department(String department) {
        	this.department = new SimpleStringProperty(department);
		}

		public String getDepartment() {
            return department.get();
        }
 
        public void setDepartment(String fName) {
            department.set(fName);
        }
        
        @Override
        public boolean equals(Object obj) {
        	if(obj == this) return true;
        	if(obj == null) return false;
        	if(!(obj instanceof Department)) return false;
        	
        	return this.department.get().equals(((Department)obj).department.get());
        }
        
        @Override
        public int hashCode() {
        	return this.department.get().hashCode();
        }
        
        @Override
        public String toString() {
        	return getAsString();
        }
        
		@Override
		public String getAsString() {
			return this.getDepartment();
		}

		@Override
		public void setTheString(String s) {
			this.setDepartment(s);
		}
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
