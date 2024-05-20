package LogIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;

    public loginWindow() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 150);
        setLocationRelativeTo(null);

        // Create components
        JLabel usernameLabel = new JLabel("      Username:");
        JLabel passwordLabel = new JLabel("      Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        // Create panel
        JPanel panel = new JPanel(new GridLayout(4, 2));
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
                    JOptionPane.showMessageDialog(loginWindow.this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(loginWindow.this, "Invalid username or password.");
                }
            }
        });
        
        //create account button
        createAccountButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Handle create account logic
        		JOptionPane.showMessageDialog(loginWindow.this, "Create Account button clicked!");
        	}
        
        });
    }

    private boolean authenticate(String username, String password) {
        // Perform authentication logic here (e.g., check against a database, API, etc.)
        // Return true if authentication is successful, false otherwise
        // This is just a placeholder example
        return username.equals("admin") && password.equals("password");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                loginWindow loginWindow = new loginWindow();
                loginWindow.setVisible(true);
            }
        });
    }
}
