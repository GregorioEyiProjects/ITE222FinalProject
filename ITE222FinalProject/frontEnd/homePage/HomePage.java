package ITE222FinalProject.frontEnd.homePage;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    private JList<String> optionList;
    private JLabel welcomeLabel;
    private JLabel descriptionLabel;

    public HomePage() {

        /*Home page JFrame default setting*/
        setTitle("E-Learning Platform");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create components
        welcomeLabel = new JLabel("Welcome to Coursera E-Learning Platform!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        descriptionLabel = new JLabel("Learn Anything, Anytime, Anywhere, Clothing Optional!", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Create option menu
        String[] options = {"My Courses", "Browse Courses", "View Profile", "-------------------------------------", "Logout"};
        optionList = new JList<>(options);
        optionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        optionList.setSelectedIndex(0);
        optionList.setPreferredSize(new Dimension(150, getHeight()));

        // Create panel
        JPanel panel = new JPanel(new BorderLayout());
        JPanel menuOptionPanel = new JPanel(new BorderLayout());

        // Add components to the panel
        menuOptionPanel.add(optionList, BorderLayout.WEST);
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(descriptionLabel, BorderLayout.CENTER);
        panel.add(menuOptionPanel, BorderLayout.WEST);

        // Add panel to frame
        getContentPane().add(panel);

        // Add action listener for option selection
        optionList.addListSelectionListener(e -> {
            String selectedOption = optionList.getSelectedValue();
            if (selectedOption != null) {
                JOptionPane.showMessageDialog(HomePage.this, "Selected option: " + selectedOption);
                // replace the current content of the "TextArea" with the option clicked
            }
        });

    }

    public static void main(String[] args) {

        /*
        HomePage hp = new HomePage();
        hp.setVisible(true);
*/
  /* SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });*/
    }

}
