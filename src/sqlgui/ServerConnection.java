/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgui;

import java.sql.*;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author a3yko
 */
public class ServerConnection {
    private final Connection CONN = ConnectToDatabase.createConnection();
    private String getSql;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private int columnCount;
            
    public ServerConnection() {
        
    }
    
    public void setsqlStatment(String sql){
        this.getSql = sql;
    }
    
    
    //Function can both load and store data from the database based on the query given.
    //Second Collection used
    @SuppressWarnings("empty-statement")
    public Stack<Stack<String>> runSql(){
       Stack<Stack<String>> query = new Stack<Stack<String>>();
        int i = 0;
        try{ps = CONN.prepareStatement(this.getSql);
            rs = ps.executeQuery();
            columnCount = rs.getMetaData().getColumnCount(); 
            query.push(new Stack<String>());
            for(int j = 1; j <= columnCount; j++){
            query.get(i).push(rs.getMetaData().getColumnName(j));
            }
            i++;
            while(rs.next()){
            query.push(new Stack<String>());
             for(int x = 1; x <= columnCount; x++){
             query.get(i).push(rs.getString(x));
             } 
             i++;
            }
        } catch (SQLException ex) {
        Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
     return query;
    }
    public void logout() throws SQLException{
        rs.close();
    }
  
}