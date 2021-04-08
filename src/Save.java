import java.io.*;

public class Save {

   /**
    * This method takes an array of prescriptions and then writes it
    * to a file called "prescriptions.ser"
    */
   public void serializeArray(Prescription[] prescriptions) throws FileNotFoundException{
      try {
         FileOutputStream fileOut =
            new FileOutputStream("prescriptions.ser");
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
   public Prescription[] deserializeArray() {
      Prescription[] prescriptions = null;
      try {
         FileInputStream fileIn = new FileInputStream("prescriptions.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         prescriptions = (Prescription[]) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return new Prescription[0];
      } catch (ClassNotFoundException c) {
         System.out.println("Prescription class not found");
         c.printStackTrace();
         return new Prescription[0];
      }
      System.out.println("Deserialized Prescriptions...");
      for (int i = 0; i < prescriptions.length; i++) {
         System.out.println("First Name: " + prescriptions[i].getName()[0]);
         System.out.println("Last Name: " + prescriptions[i].getName()[1]);
         System.out.println("Drug: " + prescriptions[i].getDrug());
         System.out.println("Quantity: " + prescriptions[i].getQuantity());
         System.out.println();
      }
      return prescriptions;
   }

   /**
    * This method deserializes our "perscriptions.ser" file, stores it
    * in an array, adds the new prescription to the array, and finally
    * serializes the array into the original file
    */
   public void addPrescription(Prescription prescription) throws FileNotFoundException {
      Prescription[] list = deserializeArray();
      //If array is full, double the size
      if (list[list.length-1] != null) {
         Prescription[] temp = list;
         list = new Prescription[list.length*2];
         System.arraycopy(temp, 0, list, 0, temp.length);
      }
      //Find first null entry in array and add new prescription to it
      for (int i = 0; i < list.length; i++) {
         if (list[i] == null) {
            list[i] = prescription;
            serializeArray(list);
            return;
         }
      }
      System.out.println("ERROR: Failed to add prescription.");
   }
}