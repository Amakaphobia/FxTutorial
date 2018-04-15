package oracleUIControls.acFXMLLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

@SuppressWarnings("javadoc")
public class LoginControl {
	
	@FXML private Text actiontarget;
	@FXML private TextField namefield;
	@FXML private PasswordField passwordField;
    
    @FXML 
    protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Click " 
        					 +this.namefield.getText() +" " 
        					 +this.passwordField.getText());
    }
}
