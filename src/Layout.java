import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.ArrayList;
import java.util.List;

// NOTE: I know we don't want to use * for imports but this makes 
// the double click events work and I can't find what exact imports
// make it work because every stupid website tells me to use * so
// unless you can figure out which specific event libraries to import,
// we're stuck using *
import java.awt.event.*; 

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Layout {
   private JPanel listPanel;
   private JTabbedPane tabbed_pane;
   private JLabel err;
   

   public Layout() {
      listPanel = new JPanel();
      listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.X_AXIS));
   }

   // create a tabbed pane with two tabs 
   public void addTabPane() {
      JFrame frame = MIMS.getFrame();
      tabbed_pane = new JTabbedPane();
      frame.add(tabbed_pane);
   
      JPanel new_panel = newPresciption();  
      tabbed_pane.addTab("New Prescription", new_panel);
   
      JPanel list_panel = listPanel;
      tabbed_pane.addTab("Prescriptions", list_panel);

      JPanel order_panel = Order.getPanel();
      tabbed_pane.addTab("Order", order_panel);

      tabbed_pane.addChangeListener(
         new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
               if (tabbed_pane.getSelectedIndex() == 1) {
                  Prescription[] p = Save.deserializeArray().toArray(new Prescription[0]);
                  Object[][] data = new Object[5][p.length];
                  List<Prescription> prescription_list = new ArrayList<Prescription>();
                  for (int i = 0; i < p.length; i++) {
                     data[0][i] = p[i].getName()[0];
                     data[1][i] = p[i].getName()[1];
                     data[2][i] = p[i].getDrug();
                     data[3][i] = p[i].getQuantity();
                     data[4][i] = p[i].getTimestamp();
                     prescription_list.add(p[i]);
                  }
                  PrescriptionTableModel<String> model = new PrescriptionTableModel<String>(prescription_list);
                  JTable table = new JTable(model);
                  table.setRowHeight(30);
                  table.getTableHeader().setReorderingAllowed(false);
                  for (int i = 0; i < 5; i++) {
                     table.getColumnModel().getColumn(i).setPreferredWidth(100);
                  }

                  //Check for double click
                  table.addMouseListener(new MouseAdapter() {
                     public void mouseClicked(MouseEvent me) {
                        if (me.getClickCount() == 2) {     // to detect doble click events
                           JTable target = (JTable)me.getSource();
                           int row = target.getSelectedRow(); // select a row
                           int column = 2;
                           String drug = table.getValueAt(row, column).toString();
                           System.out.println(drug); // This can be removed once you get the order tab working
                           //TODO Open order tab with the drug selected from the row
                        }
                     }
                  });

                  JPanel temp = (JPanel) tabbed_pane.getSelectedComponent();
                  temp.removeAll();
                  temp.add(new JScrollPane(table));
               }
            }
         
         });
   }

   // panel for making a new prescription
   public JPanel newPresciption() {
      JPanel p = new JPanel();
      p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
   
      // Presciptionts have a name, quantity, and drug 
      String[] labels = {"First Name: ", "Last Name: ", "Drug: ", "Quantity: "};
      int num_pairs = labels.length;
      JTextField[] field_list = new JTextField[num_pairs];
   
      //populate the panel.
      for (int i = 0; i < num_pairs; i++) {
         JLabel l = new JLabel(labels[i], JLabel.TRAILING);
         p.add(l);
         JTextField text_field = new JTextField(20);
         
         field_list[i] = text_field;
         text_field.setMaximumSize(text_field.getPreferredSize());
         l.setLabelFor(text_field);
         p.add(text_field);
      }  
   
      JButton add_button = new JButton("Add Presciption");
      // when add is pressed, then a new prescription is added to the database
      add_button.addActionListener(
         new ActionListener() {
            
         
            public void actionPerformed(ActionEvent e) {
               for (int i = 0; i < num_pairs; i++) {
                  if (field_list[i].getText().equals("")) {
                     return;
                  }
               }

               String first_name = field_list[0].getText();
               String last_name = field_list[1].getText();
               String drug = field_list[2].getText();
               String quantity = field_list[3].getText();
               String time_stamp = Save.timeStamp();
            
            // if the quantity is not an integer then do not set the presciption
               JPanel pane = (JPanel) tabbed_pane.getSelectedComponent();
               try {
                  Integer.parseInt(field_list[3].getText());
               } catch (NumberFormatException ex) {
                  if (err == null) {
                     err = new JLabel("Could not add Prescription");
                     pane.add(err);
                     MIMS.getFrame().revalidate();
                  }
                  return;
                  // ex.printStackTrace();
               }
               if (err != null) {
                  System.out.println(err);
                  pane.remove(err);
                  MIMS.getFrame().repaint();
                  err = null;
               }
               Prescription p = new Prescription(first_name, last_name, drug, quantity, time_stamp);
               Save.addPrescription(p);
            }
         });
   
      p.add(add_button);
      return p;
   }
}