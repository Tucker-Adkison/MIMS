import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Layout {
    private JPanel listPanel;
    private JTabbedPane tabbed_pane;
    private JTextField db_username;
    private JTextField db_password;
    private String db_password_s;
    private String db_username_s;
    private Database db;

    public Layout() {
        listPanel = new JPanel();
    }

    

    public void addTabPane() {
        JFrame frame = MIMS.getFrame();
        tabbed_pane = new JTabbedPane();
        frame.add(tabbed_pane);

        JPanel new_panel = newPresciption();  
        tabbed_pane.addTab("New Prescription", new_panel);

        JPanel list_panel = listPanel;
        tabbed_pane.addTab("Prescriptions", list_panel);

        tabbed_pane.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                if (tabbed_pane.getSelectedIndex() == 1) {
                    // TODO add a JList tabbed pane with prescriptions from the database
                    return;
                }
            }
            
        });
    }

    public void addMenuPane() {
        JFrame frame = MIMS.getFrame();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Settings");
        menuBar.add(menu);
        
        JPanel settings = new JPanel();
        settings.setLayout(new BoxLayout(settings, BoxLayout.PAGE_AXIS));
        
        JLabel username_l = new JLabel("Username", JLabel.TRAILING);
        settings.add(username_l);
        db_username = new JTextField(20);
        db_username.setMaximumSize(db_username.getPreferredSize());
        settings.add(db_username);

        JLabel password_l = new JLabel("Password", JLabel.TRAILING);
        settings.add(password_l);
        db_password = new JTextField(20);
        db_password.setMaximumSize(db_password.getPreferredSize());
        settings.add(db_password);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db_username_s = db_username.getText();
                db_password_s = db_password.getText();
                // JLabel submitted = new JLabel("Submitted");
                // settings.add(submitted);
                tabbed_pane.remove(settings);
            }
        });

        settings.add(submit);

        menu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                tabbed_pane.addTab("Database", settings);
            }
            public void menuDeselected(MenuEvent e) {

            }
            public void menuCanceled(MenuEvent e) {
            
            }

        });
        frame.setJMenuBar(menuBar);
        frame.revalidate();
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
            text_field.setMaximumSize(text_field.getPreferredSize());
            l.setLabelFor(text_field);
            p.add(text_field);
        }  

        JButton add_button = new JButton("Add Presciption");
        // action listener for add_button
        add_button.addActionListener(new ActionListener() {
            
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
                
                // if the quantity is not an integer then do not set the presciption
                try {
                    Integer.parseInt(field_list[3].getText());
                    //TODO connect to the database and add a presciption to the database
                    db = new Database("database", db_username_s, db_password_s);
                } catch (NumberFormatException | SQLException ex){
                    JLabel err;
                    if (ex instanceof NumberFormatException)   
                        err = new JLabel("Could not add to the database");
                    else 
                        err = new JLabel("Could connect to the database");
                }
            }
        });

        p.add(add_button);
        return p;
    }
}
