/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


/**
 * FXML Controller class
 *
 * @author Adrian Atanasov
 */
public class SqlGuiCont extends Switchable implements Initializable {


    @FXML
    private TextArea input;
    
    @FXML
    private Label serverinfo;
    
    @FXML
    private Label dbinfo;
    
    @FXML
    private TextArea output;
 
    // Third Collection used 
    private Stack<Stack<String>> temp = new Stack<Stack<String>>();
    
    private final ServerConnection SQL = new ServerConnection();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serverinfo.setText(ConnectToDatabase.getServer());
        dbinfo.setText(ConnectToDatabase.getDatabase());
    }
    
    @FXML
    private void handleGoToLogin(ActionEvent event) throws SQLException {
        Switchable.switchTo("LoginGui");
        SQL.logout();
    }
    
    //Outputs results of query to the output section of the screen
    @FXML
    private void handleQuery(ActionEvent event){
        SQL.setsqlStatment(input.getText());
        output.clear();
        temp = SQL.runSql();
        output.setEditable(false);
        int j = 0;
        try{
        while(!temp.isEmpty()){
            while(!temp.get(j).isEmpty()){
            output.appendText(" "+ temp.get(j).pop() + " ");
            }
            output.appendText("\n");
            output.appendText("---------------------\n");
            j++;
        }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Query Completed");
            alert.show();
        }
    }
}
