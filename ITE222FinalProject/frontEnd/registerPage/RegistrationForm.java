package ITE222FinalProject.frontEnd.registerPage;

import ITE222FinalProject.backEnd.signUp.SignUpImplementation;
import ITE222FinalProject.frontEnd.homePage.HomePage;
import ITE222FinalProject.frontEnd.login.LoginWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {

    private JTextField nameOrUserName;

    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField dateOfBirthField;

    public RegistrationForm(){

        setTitle("Coursera Account Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the banner panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(Color.BLUE);

        // Create the banner label
        JLabel bannerLabel = new JLabel("Coursera Account Registration Form");
        bannerLabel.setForeground(Color.WHITE);
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bannerPanel.add(bannerLabel);

        // Create the content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create panel
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        // Add components to the panel
        addFormField(panel, "Name", nameOrUserName = new JTextField());
        addFormField(panel, "Email Address", emailField = new JTextField());
        addFormField(panel, "Password", passwordField = new JPasswordField());
        addFormField(panel, "Confirm Password", confirmPasswordField = new JPasswordField());
        addFormField(panel, "Date of Birth", dateOfBirthField = new JTextField());

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginWindow lw = new LoginWindow();
                lw.setVisible(true);
            }
        });


        // Increase button size
        Font buttonFontRegister = registerButton.getFont();
        Font buttonFontLogin = loginButton.getFont();
        Font largeButtonFontRegister = new Font(buttonFontRegister.getName(), buttonFontRegister.getStyle(), buttonFontRegister.getSize() + 5);
        Font largeButtonFontLogin = new Font(buttonFontLogin.getName(), buttonFontLogin.getStyle(), buttonFontLogin.getSize() + 5);
        registerButton.setFont(largeButtonFontRegister);
        loginButton.setFont(largeButtonFontLogin);


        // Create a new panel for the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        // Add the components to the content panel
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.add(Box.createVerticalStrut(10), BorderLayout.PAGE_END); // Add vertical spacing
        contentPanel.add(buttonPanel, BorderLayout.PAGE_END); // Align at the bottom

        // Create a main panel to hold the banner panel and content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(bannerPanel, BorderLayout.PAGE_START);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        getContentPane().add(mainPanel);
    }

    private void addFormField(JPanel panel, String label, JTextField textField) {
        JLabel formLabel = new JLabel();
        if (!label.equals("Middle Initial")) {
            String labelText = label + ": ";
            String requiredText = "(Required Field)";
            formLabel.setText("<html><font color='black'>" + labelText + "</font><font color='red' size='2'>" + requiredText + "</font></html>");
        } else {
            formLabel.setText(label + ": ");
        }
        panel.add(formLabel);
        panel.add(textField);
    }

    private void registerUser() {
        String name = nameOrUserName.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String dateOfBirth = dateOfBirthField.getText();

        // Perform validation and registration logic here
        // You can add your own implementation to handle the registration process

        // Example validation for demonstration purposes
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || dateOfBirth.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
        } else {
            SignUpImplementation singUp = new SignUpImplementation();
            boolean validData = singUp.addStudent(name, email, password, dateOfBirth);
            if (validData){
                HomePage hp = new HomePage(name, password);
                hp.getInfoTextArea().setText("Learn anything, anytime, anywhere, clothing optional");
                JOptionPane.showMessageDialog(this, "Registration successful!");
                setVisible(false);
                hp.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(this, "The information provided is not complete");

            }


        }

    }

}
