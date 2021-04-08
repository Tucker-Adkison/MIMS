import java.io.Serializable;

public class Prescription implements Serializable{
   /**
    *
    */
   private static final long serialVersionUID = 1L;
   private String first_name;
   private String last_name;
   private String drug;
   private int quantity;
   private String time_stamp;

   public Prescription(String fn, String ln, String d, String q, String ts) {
      first_name = fn;
      last_name = ln;
      drug = d;
      quantity = Integer.parseInt(q);
      time_stamp = ts;
   } 

   public String toString() {
      return first_name + " " + last_name + " " + drug + " " + String.valueOf(quantity);
   }

   // getters 
   public String[] getName() {
      return new String[] {first_name, last_name};
   }

   public String getDrug() {
      return drug;
   }

   public int getQuantity() {
      return quantity;
   }

   public String getTimestamp() {
      return time_stamp;
   }

   // setters 
   public void setName(String fn, String ln) {
      first_name = fn;
      last_name = ln;
   }

   public void setDrug(String d) {
      drug = d;
   } 

   public void setQuantity(int q) {
      quantity = q;
   }
}
