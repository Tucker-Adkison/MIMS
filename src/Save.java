import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.util.ArrayList;

public class Save {
   /**
    * This method takes an array of prescriptions and then writes it
    * to a file called "prescriptions.ser"
    */
   public static <T> void serializeArray(ArrayList<T> prescriptions, String file) {
      try {
         FileOutputStream fileOut = new FileOutputStream(file);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(prescriptions);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in /MIMS\n");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }

   /**
    * This method deserializes our "prescription.ser" file and returns
    * the prescriptions in an array
    */
   @SuppressWarnings("unchecked")
   public static <T> ArrayList<T> deserializeArray(String file) {
      ArrayList<T> prescriptions = null;
      try {
         FileInputStream fileIn = new FileInputStream(file);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         
         prescriptions = (ArrayList<T>) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException | ClassNotFoundException e) {
         if (e instanceof ClassNotFoundException)
            System.out.println("Prescription class not found");
         return new ArrayList<T>();
      }
      // System.out.println("Deserialized Prescriptions...");
      // for (int i = 0; i < prescriptions.length; i++) {
      //    System.out.println("First Name: " + prescriptions[i].getName()[0]);
      //    System.out.println("Last Name: " + prescriptions[i].getName()[1]);
      //    System.out.println("Drug: " + prescriptions[i].getDrug());
      //    System.out.println("Quantity: " + prescriptions[i].getQuantity());
      //    System.out.println();
      // }
      return prescriptions;
   }

   public static void addLogin(String login) {
      ArrayList<String> list = deserializeArray("data/login.ser");
      if (list == null) {
         list = new ArrayList<String>();
      }
      list.add(login);
      serializeArray(list, "data/login.ser");
   }

   /**
    * This method deserializes our "perscriptions.ser" file, stores it
    * in an array, adds the new prescription to the array, and finally
    * serializes the array into the original file
    */
   public static void addPrescription(Prescription prescription) {
      ArrayList<Prescription> list = deserializeArray("data/prescriptions.ser");
      if (list == null) {
         list = new ArrayList<Prescription>();
      }
      list.add(prescription);
      serializeArray(list, "data/prescriptions.ser");
   }

   public static void deletePrescription(Prescription p) {
      ArrayList<Prescription> list = deserializeArray("data/prescriptions.ser");
      if (list == null) {
         return;
      }
      list.remove(p);
      serializeArray(list, "data/prescriptions.ser");
   }

   public static String timeStamp() {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      return dtf.format(now);
   }
}