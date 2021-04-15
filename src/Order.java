import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Order {

    public static JPanel getPanel() {
        JPanel order_panel = new JPanel();
        order_panel.setLayout(new BoxLayout(order_panel, BoxLayout.PAGE_AXIS));
        
        // XXXX-XXXX-XXXX-XXXX
        JTextField credit_number = new JTextField(19);
        credit_number.setText("XXXX-XXXX-XXXX-XXXX");
        credit_number.setMaximumSize(credit_number.getPreferredSize());
        DateFormat format = new SimpleDateFormat("mm-yy");
        JFormattedTextField experation = new JFormattedTextField(format);
        experation.setText("02/03");
        experation.setMaximumSize(experation.getPreferredSize());
        JTextField name = new JTextField(20);
        name.setText("Firstname Lastname");
        name.setMaximumSize(name.getPreferredSize());

        order_panel.add(credit_number);
        order_panel.add(experation);
        order_panel.add(name);

        return order_panel;
    }
}
