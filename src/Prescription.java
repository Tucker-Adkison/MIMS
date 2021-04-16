import java.io.Serializable;

public class Prescription implements Serializable{
   /**
    *
    */
   private static final long serialVersionUID = 1L;
   private String first_name;
   private String last_name;
   private String drug;
   private float price;
   private String time_stamp;

   public Prescription(String fn, String ln, String d, String q, String ts) {
      first_name = fn;
      last_name = ln;
      drug = d;
      price = Float.parseFloat(q);
      time_stamp = ts;
   } 

   public String toString() {
      return first_name + " " + last_name + " " + drug + " " + String.valueOf(price) + " " + time_stamp;
   }

   // getters 
   public String[] getName() {
      return new String[] {first_name, last_name};
   }

   public String getDrug() {
      return drug;
   }

   public String getPrice() {
      return "$" + price;
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

   public void setPrice(float q) {
      price = q;
   }
}
