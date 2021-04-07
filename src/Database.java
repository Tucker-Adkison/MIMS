import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
   private Connection conn;
   //TODO create methods to add and get from the database
   public Database(String db_name, String username, String password) throws SQLException{
      conn = DriverManager.getConnection(db_name, username, password);
      conn.close();
   }
   
    /**
     * Connect to the our prescription database
     */
   private Connection connect() {
     // SQLite connection string
      String url = ""; //EVENTUALLY we will want to put in the URL of our database once it's up and running
      conn = null;
      try {
         conn = DriverManager.getConnection(url);
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return conn;
   }
   
   public void addPrescription(Prescription p) {
      String[] name = p.getName();
      String drug = p.getDrug();
      int quantity = p.getQuantity();
      //Prepare a statement to send to sqlite
      String sql = "INSERT INTO prescriptions(first_name,last_name,drug,quantity) VALUES(?,?,?,?)";
      //Try to connect to database
      try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setString(1, name[0]);
         pstmt.setString(2, name[1]);
         pstmt.setString(3, drug);
         pstmt.setInt(4, quantity);
         pstmt.executeUpdate();
      //If the connection failed
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
   }
   
   public Prescription getPrescription(int id) {
      String sql = "SELECT id, first_name, last_name, drug, quantity FROM prescriptions";
      String fn, ln, d, q;
      boolean found = false;
      try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
        // loop through the result set
         while (rs.next()) {
         //We have found our prescription
            if (rs.getInt("id") == id) {
               fn = rs.getString("first_name");
               ln = rs.getString("last_name");
               d = rs.getString("drug");
               q = Integer.toString(rs.getInt("quantity"));
               //Create prescription based on database entry, then return it
               Prescription p = new Prescription(fn, ln, d, q);
               return p;
            }
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return null;
   }
}


