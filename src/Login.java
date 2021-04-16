import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class Login {
    private JFrame frame;
    private Layout layout;
    

    public Login() {
        frame = MIMS.getFrame();
        layout = new Layout();
    }

    // make a menu pane which adds a "database tab" for logging into the database 
    public JMenuBar loginMenuPane(JTabbedPane tabbed_pane) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Add User");

        menuBar.add(menu);
        
        // create the JPanel to add to the "database tab"
        JPanel settings = new JPanel();
        settings.setLayout(new BoxLayout(settings, BoxLayout.PAGE_AXIS));
        
        JLabel username_l = new JLabel("Username", JLabel.TRAILING);
        settings.add(username_l);
        JTextField login_username = new JTextField(20);
        login_username.setMaximumSize(login_username.getPreferredSize());
        settings.add(login_username);
    
        JLabel password_l = new JLabel("Password", JLabel.TRAILING);
        settings.add(password_l);
        JPasswordField login_password = new JPasswordField(20);
        login_password.setMaximumSize(login_password.getPreferredSize());
        settings.add(login_password);
    
        JButton submit = new JButton("Submit");
        // get username and password fields if submit is pressed
        // delete the database tab from the tabbed pane
        submit.addActionListener(
            new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login_username_s = login_username.getText();
                char[] login_password_s = login_password.getPassword();

                if (!login_username_s.equals("") && login_password_s.length > 0) {
                    String login_info = login_username_s +  " " + String.valueOf(login_password_s);
                    Save.addLogin(login_info);
                }

                // JLabel submitted = new JLabel("Submitted");
                // settings.add(submitted);
                tabbed_pane.remove(settings);
                }
            });
    
        settings.add(submit);
    
        // if the menuitem is pressed, then add database tab
        menu.addMenuListener(
            new MenuListener() {
                public void menuSelected(MenuEvent e) {
                    tabbed_pane.addTab("Add User", settings);
                }
                public void menuDeselected(MenuEvent e) {
                
                }
                public void menuCanceled(MenuEvent e) {
                
                }
            
            });
        return menuBar;
    }

    public void addLoginScreen() {
        JPanel lPanel = new JPanel();
        lPanel.setLayout(new BoxLayout(lPanel, BoxLayout.PAGE_AXIS));

        ImageIcon icon = new ImageIcon("../data/icon.png");
        Image image = icon.getImage();
        Image scaled = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon newimg = new ImageIcon(scaled);
        JLabel imageLabel = new JLabel(newimg);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        lPanel.add(imageLabel);

        JTextField user_field = new JTextField(20);
        JPasswordField pass_field = new JPasswordField(20);
        user_field.setMaximumSize(user_field.getPreferredSize());
        pass_field.setMaximumSize(user_field.getPreferredSize());
        user_field.setText("");
        pass_field.setText("");

        JLabel user_label = new JLabel("Username");
        user_label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        user_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        lPanel.add(user_label);
        lPanel.add(user_field);
        JLabel pass_label = new JLabel("Password");
        pass_label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pass_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        lPanel.add(pass_label);
        lPanel.add(pass_field);

        JLabel empty_label = new JLabel();
        empty_label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        lPanel.add(empty_label);

        frame.add(lPanel);

        user_field.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cont(user_field, pass_field, lPanel);
            }
        });

        pass_field.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cont(user_field, pass_field, lPanel);;
            }
        });

        JButton submit = new JButton("Login");
        submit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                cont(user_field, pass_field, lPanel);;
            }
        });

        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        lPanel.add(submit);
        frame.repaint();
        frame.revalidate();
    }

    public void cont(JTextField user_field, JPasswordField pass_field, JPanel lPanel) {
        String username = user_field.getText();
        char[] password = pass_field.getPassword();

        if (username != ""){
            ArrayList<String> users = Save.deserializeArray("../data/login.ser");
            String[] admin = new String[] {"**", "&&"};
            for (String user : users) {
                String[] line = user.split("\\s+");
                char[] input_pass = line[1].toCharArray();
                if (line[0].equals(username) && Arrays.equals(password, input_pass) || 
                    admin[0].equals(username) && Arrays.equals(password, admin[1].toCharArray())) {
                    frame.remove(lPanel);
                    JTabbedPane pane = layout.addTabPane();
                    frame.setJMenuBar(this.loginMenuPane(pane));
                    frame.repaint();
                    frame.revalidate();
                    return;
                }
            }
        }
        showMessageDialog(null, "ERROR: Invalid Login");
    }

}
