import java.io.*;

/*********************************************************************
 * THIS CLASS IS A SAMPLE OF HOW WE WOULD SERIALIZE/DESERIALIZE DATA *
 * SOME FORM OF THIS COULD BE USED IN OUR DATABASE OBJECT            *
 *********************************************************************/
public class test {
   public static void main(String [] args) {
      Prescription p = new Prescription("John", "Smith", "Tylenol", "5");
      //Serialization
      try {
         FileOutputStream fileOut =
            new FileOutputStream("prescriptions.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(p);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /MIMS");
      } catch (IOException i) {
         i.printStackTrace();
      }

      //Deserialization
      p = null;
      try {
         FileInputStream fileIn = new FileInputStream("prescriptions.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         p = (Prescription) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         System.out.println("Prescription class not found");
         c.printStackTrace();
         return;
      }
      
      System.out.println("Deserialized Prescriptions...");
      System.out.println("First Name: " + p.getName()[0]);
      System.out.println("Last Name: " + p.getName()[1]);
      System.out.println("Drug: " + p.getDrug());
      System.out.println("Quantity: " + p.getQuantity());
   }
}
