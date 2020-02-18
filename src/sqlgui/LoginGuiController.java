/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author a3yko
 */
public class LoginGuiController extends Switchable implements Initializable {

    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }
    @FXML 
    private void handleOpenAbout(ActionEvent event){
       Alert about = new Alert(Alert.AlertType.INFORMATION);
       about.setHeaderText("About Application");
       about.setContentText("2019 CS3330 Project\n\n"
               + "Created by: Adrian Atanasov\n\n"
               + "SQL Query Tool V0.1 was created as a simple way run queries against MariaDB Databases");
       about.show();
      
    }
    
    @FXML
    private void handleGoToGui(ActionEvent event){
       ConnectToDatabase.setUsername(username.getText());
       ConnectToDatabase.setPassword(password.getText());
       ServerConnection sql = new ServerConnection();
       if(ConnectToDatabase.getBool())
       
       Switchable.switchTo("SqlEdit");
       else
       {
           Alert error = new Alert(AlertType.ERROR);
           error.setTitle("Wrong Credentials");
           error.showAndWait();
       }
    }
}
