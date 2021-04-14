import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
    private JFrame frame;
    private Layout layout;

    public Login() {
        frame = MIMS.getFrame();
        layout = new Layout();
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

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                String username = user_field.getText();
                char[] password = pass_field.getPassword();

                if (username != ""){
                    try {
                        Scanner scanner = new Scanner(new File("../data/login.txt"));
                        while (scanner.hasNextLine()) {
                            String[] line = scanner.nextLine().split("\\s+");
                            char[] input_pass = line[1].toCharArray();
                            if (line[0].equals(username) && Arrays.equals(password, input_pass)) {
                                frame.remove(lPanel);
                                layout.addTabPane();
                                frame.repaint();
                                frame.revalidate();
                                return;
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        lPanel.add(submit);
        frame.repaint();
        frame.revalidate();
    }

}
