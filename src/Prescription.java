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

   public Prescription(String fn, String ln, String d, String q) {
      first_name = fn;
      last_name = ln;
      drug = d;
      quantity = Integer.parseInt(q);
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
