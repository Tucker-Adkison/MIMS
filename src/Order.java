import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import static javax.swing.JOptionPane.showMessageDialog;

public class Order {

    public static JPanel getPanel() {
        JPanel order_panel = new JPanel();
        order_panel.setLayout(new BoxLayout(order_panel, BoxLayout.PAGE_AXIS));
        
        // XXXX-XXXX-XXXX-XXXX
        JTextField credit_number = new JTextField(19);
        credit_number.setText("XXXX-XXXX-XXXX-XXXX");
        credit_number.setMaximumSize(credit_number.getPreferredSize());
        JTextField prescription = new JTextField(20);
        prescription.setText("Tylenol");
        prescription.setMaximumSize(prescription.getPreferredSize());
        DateFormat format = new SimpleDateFormat("mm-yy");
        JFormattedTextField experation = new JFormattedTextField(format);
        experation.setText("02/03");
        experation.setMaximumSize(experation.getPreferredSize());
        JTextField name = new JTextField(20);
        name.setText("Firstname Lastname");
        name.setMaximumSize(name.getPreferredSize());

        JButton order_button = new JButton("Order");
        order_button.setAlignmentX(0.5f);

        order_panel.add(credit_number);
        order_panel.add(prescription);
        order_panel.add(experation);
        order_panel.add(name);
        order_panel.add(order_button);

        order_button.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean notFound = true;
                Prescription[] list = Save.deserializeArray("../data/prescriptions.ser").toArray(new Prescription[0]);
                for (int i= 0; i < list.length; i++) {
                    if (prescription.getText().equalsIgnoreCase(list[i].getDrug())) {
                        notFound = false;
                        break;
                    }
                }
                if (credit_number.getText().replaceAll("\\s+","").isEmpty() || 
                    prescription.getText().replaceAll("\\s+","").isEmpty() ||
                    experation.getText().replaceAll("\\s+","").isEmpty() ||
                    name.getText().replaceAll("\\s+","").isEmpty()) {
                        showMessageDialog(null, "ERROR: One or more fields is empty.");
                        return;
                }
                if (notFound) {
                    showMessageDialog(null, "ERROR: Prescription not found.");
                    return;
                }
                showMessageDialog(null, "Your order has been placed.");
            }
         });

        return order_panel;
    }
}
