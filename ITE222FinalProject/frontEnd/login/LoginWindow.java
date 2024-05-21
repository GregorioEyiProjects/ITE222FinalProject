package ITE222FinalProject.frontEnd.login;

import ITE222FinalProject.backEnd.SignIn.SignInImplementation;
import ITE222FinalProject.frontEnd.homePage.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    JButton createAccountButton;

    public LoginWindow() {

        /*Login JFrame default setting*/
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);

        // Create components
        JLabel bannerLabel = new JLabel("Welcome to Coursera.", SwingConstants.CENTER);
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create Account");

        // Create panel
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(bannerLabel);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(createAccountButton);

        // Add panel to frame
        getContentPane().add(panel);

        // Add login button action listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Perform login authentication
                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Login successful!");
                    setVisible(false);
                    HomePage hp = new HomePage();
                    hp.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Invalid username or password.");
                }
            }
        });

        // Create account button
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create account logic
                JOptionPane.showMessageDialog(LoginWindow.this, "Create Account button clicked!");
                //Navigate to the register JFrame
            }
        });

    }

    private boolean authenticate(String userName, String password) {

        SignInImplementation signIn = new SignInImplementation();
        return signIn.studentAuth(userName, password);  //Returning the result from the back-end.

        /*// Usernames and passwords
        String[] validUsernames = {"matt", "gregorio", "marco"};
        String[] validPasswords = {"password", "password", "password"};

        // Check if provided username and password match any of the valid combinations
        for (int i = 0; i < validUsernames.length; i++) {
            if (username.equals(validUsernames[i]) && password.equals(validPasswords[i])) {
                return true; // Authentication successful
            }
        }*/// Authentication failed
    }

}
