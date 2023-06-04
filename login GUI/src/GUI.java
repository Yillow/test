import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI implements ActionListener {
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel label;
    private static JLabel userLabel;
    private static JLabel passwordLabel;
    private static JLabel success;
    private static JButton button;
    private static JButton createPass;
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static ArrayList<String> usernameArray;
    private static ArrayList<String> passwordArray; 

    public static void main(String[] args) {
        panel = new JPanel();
        frame = new JFrame();

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Password GUI");
        frame.add(panel);

        panel.setLayout(null);
        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(10, 90, 80, 25);
        button.addActionListener(new GUI());
        panel.add(button);

        createPass = new JButton("Register a username/password?");
        usernameArray = new ArrayList<String>();
        passwordArray = new ArrayList<String>();
        createPass.setBounds(100, 90, 200, 25); // Adjusted position and size
        createPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = JOptionPane.showInputDialog(frame, "Enter new username:");
                String password = JOptionPane.showInputDialog(frame, "Enter new password:");

                if (user != null && password != null && !user.isEmpty() && !password.isEmpty()) {
                    System.out.println("New User: " + user + " Password: " + password);
                    success.setText("New user created!");
                    usernameArray.add(user);
                    passwordArray.add(password);
                } else {
                    success.setText("Invalid username or password.");
                }

                
            }
        });
        panel.add(createPass);

        success = new JLabel("");
        success.setBounds(10, 120, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        System.out.println("User: " + user + " Password: " + password);
    
        // create a way to hold onto the registered username and password
        boolean isLoginSuccessful = false;
        for (int i = usernameArray.size() - 1; i >= 0; i--) {
            if (user.equals(usernameArray.get(i)) && password.equals(passwordArray.get(i))) {
                isLoginSuccessful = true;
                break;
            }
        }
    
        if (isLoginSuccessful) {
            success.setText("Logged in successfully!");
            openWebsite("https://upcomingvideogame1.wordpress.com/");
        } else {
            success.setText("Invalid username or password.");
        }
    }
    private void openWebsite(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
