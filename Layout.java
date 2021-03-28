import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Layout {
    public void addTabPane() {
        JFrame frame = MMIS.getFrame();
        JTabbedPane tabbed_pane = new JTabbedPane();
        frame.add(tabbed_pane);

        JPanel new_panel = newPresciption();  
        tabbed_pane.addTab("New Prescription", new_panel);

        JPanel list_panel = new JPanel();
        tabbed_pane.addTab("Prescriptions", list_panel);
    }

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
            text_field.setMaximumSize( text_field.getPreferredSize() );
            l.setLabelFor(text_field);
            p.add(text_field);
        }  

        JButton add_button = new JButton("Add Presciption");
        add_button.addActionListener(new AddListener(field_list, num_pairs));
        p.add(add_button);

        return p;
    }
}

class AddListener implements ActionListener{
    JTextField[] field_list;
    int num_pairs;

    public AddListener(JTextField[] f, int n) {
        field_list = f;
        num_pairs = n;
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < num_pairs; i++) {
            System.out.println(field_list[i].getText());
            if (field_list[i].getText().equals("")) {
                return;
            }
        }
        return;
    }
}