
package sqlgui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/* Referenced the Following to create this\
https://stackoverflow.com/questions/23020857/connect-to-mariadb-from-java-application-in-netbeans-on-linux-mageia
https://codereview.stackexchange.com/questions/172573/simple-mvc-crud-with-jdbc
*/

public class ConnectToDatabase extends Switchable {
    
    private static String dbPassword;
    private static String dbUsername;
    private static final String SERVER = "104.166.193.47:3306";
    private static final String DATABASE = "wbsitecs3380";
    private static boolean check;
    
    
     /* Takes the predefined data for Server,Database,Username,Password and passes them 
      * into the url that it then uses to connect to the database
      */
      public static Connection createConnection() {
        String url = "jdbc:mariadb://" + SERVER + "/" + DATABASE + "?user=" + dbUsername + "&password=" + dbPassword;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            setBoolT();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToDatabase.class.getName()).log(Level.SEVERE, null, ex);
            setBoolF();
        }
        return conn;
    }
      
      //sets check to true if the connection was successful;
      public static void setBoolT(){
          check = true;
      }
      
      //sets check to false if the connection was unsuccessful;
      public static void setBoolF(){
          check = false;
      }
      
      public static boolean getBool(){
          return check;
      }
      
      public static void setUsername(String username){
          dbUsername = username;
      }
      
      public static void setPassword(String password){
          dbPassword = password;
      }
      
      public static String getServer(){
          return SERVER;
      }
      
      public static String getDatabase(){
          return DATABASE;
      }

}
