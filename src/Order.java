import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import static javax.swing.JOptionPane.showMessageDialog;

public class Order {

    public static JPanel getPanel(String drug, String name) {
        JPanel order_panel = new JPanel();
        GroupLayout layout = new GroupLayout(order_panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        order_panel.setLayout(layout);

        // horizontal grouping 
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        

        // vertical grouping 
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        
        JLabel prescription_label = new JLabel("Prescription");
        JLabel credit_label = new JLabel("Credit Card Number:");
        JLabel date_label = new JLabel("Experation Date:");
        JLabel name_label = new JLabel("Name On Card:");
        JLabel quantity_label = new JLabel("Quantity:");

        // XXXX-XXXX-XXXX-XXXX
        JTextField credit_number = new JTextField(19);
        credit_number.setText("XXXX-XXXX-XXXX-XXXX");
        credit_number.setMaximumSize(credit_number.getPreferredSize());

        JTextField prescription = new JTextField(20);
        prescription.setText(drug);
        prescription.setMaximumSize(prescription.getPreferredSize());

        DateFormat format = new SimpleDateFormat("MM/yy");
        JFormattedTextField experation = new JFormattedTextField(format);
        experation.setText("02/03");
        experation.setMaximumSize(experation.getPreferredSize());

        JTextField name_field = new JTextField(20);
        name_field.setText(name);
        name_field.setMaximumSize(name_field.getPreferredSize());

        JTextField quantity = new JTextField(3);
        quantity.setText("1");
        quantity.setMaximumSize(quantity.getPreferredSize());

        JButton order_button = new JButton("Order");

        order_panel.add(credit_number);
        order_panel.add(prescription);
        order_panel.add(experation);
        order_panel.add(name_field);
        order_panel.add(order_button);

        order_button.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean notFound = true;
                Prescription[] list = Save.deserializeArray("data/prescriptions.ser").toArray(new Prescription[0]);
                for (int i= 0; i < list.length; i++) {
                    if (prescription.getText().equalsIgnoreCase(list[i].getDrug())) {
                        notFound = false;
                        break;
                    }
                }
                if (credit_number.getText().replaceAll("\\s+","").isEmpty() || 
                    prescription.getText().replaceAll("\\s+","").isEmpty() ||
                    experation.getText().replaceAll("\\s+","").isEmpty() ||
                    name_field.getText().replaceAll("\\s+","").isEmpty()) {
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

        hGroup.addGroup(layout.createParallelGroup()
        .addComponent(prescription_label).addComponent(credit_label)
        .addComponent(date_label).addComponent(name_label)
        .addComponent(quantity_label));
        hGroup.addGroup(layout.createParallelGroup()
        .addComponent(prescription).addComponent(credit_number)
        .addComponent(experation).addComponent(name_field)
        .addComponent(quantity).addComponent(order_button));
        layout.setHorizontalGroup(hGroup);

        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(prescription_label).addComponent(prescription));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(credit_label).addComponent(credit_number));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(date_label).addComponent(experation));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(name_label).addComponent(name_field));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(quantity_label).addComponent(quantity)); 
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(order_button));
        layout.setVerticalGroup(vGroup);

        return order_panel;
    }
}
