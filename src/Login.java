import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.BoxLayout;
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

        JTextField user_field = new JTextField(20);
        JPasswordField pass_field = new JPasswordField(20);
        user_field.setMaximumSize(user_field.getPreferredSize());
        pass_field.setMaximumSize(user_field.getPreferredSize());
        user_field.setText("");
        pass_field.setText("");

        JLabel user_label = new JLabel("Username");
        user_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        lPanel.add(user_label);
        lPanel.add(user_field);
        JLabel pass_label = new JLabel("Password");
        pass_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        lPanel.add(pass_label);
        lPanel.add(pass_field);
        frame.add(lPanel);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                String username = user_field.getText();
                char[] password = pass_field.getPassword();

                if (username != "" && password.length != 1){
                    try {
                        Scanner scanner = new Scanner(new File("login.txt"));
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
