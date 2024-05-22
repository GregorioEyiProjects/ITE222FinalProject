package ITE222FinalProject.frontEnd.homePage;

import ITE222FinalProject.backEnd.SignIn.SignInImplementation;
import ITE222FinalProject.backEnd.classes.Course;
import ITE222FinalProject.frontEnd.login.LoginWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class HomePage extends JFrame {

    private JList<String> optionList;
    private JLabel welcomeLabel;
    private JLabel descriptionLabel;
    private JTextArea infoTextArea;

    private java.util.List<Course> listOfCoursesToPrint = new ArrayList<>();

    private SignInImplementation signIn = SignInImplementation.getInstance();

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

        /* Add text area to display information  - Start*/

        infoTextArea = new JTextArea(600, 10);
        infoTextArea.setEditable(false);

        /*//Filling the list with the info coming
        listOfCoursesToPrint = signIn.getStudentCoursesFound();

        //Adding the student info to the textArea
        for (Course item: listOfCoursesToPrint){
            infoTextArea.append(item.getCourseInfo());
        }*/

        JScrollPane scrollPane = new JScrollPane(infoTextArea); // Wrap the text area in a scroll pane

        // Set preferred size for the scroll pane
        int scrollPaneWidth = 400; // Set the desired width
        int scrollPaneHeight = 300; // Set the desired height
        scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));

        // Set border for the scroll pane
        Color borderColor = Color.BLACK; // Change the color here
        int borderThickness = 3; // Change the thickness here
        Border border = BorderFactory.createLineBorder(borderColor, borderThickness);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        /* Add text area to display information -end */


        /*Place the info before adding it to the panel*/

        panel.add(scrollPane, BorderLayout.CENTER);

        // Add panel to frame
        getContentPane().add(panel);

        // Add action listener for option selection
        optionList.addListSelectionListener(e -> {
            String selectedOption = optionList.getSelectedValue();
            if (selectedOption != null) {
                //JOptionPane.showMessageDialog(HomePage.this, "Selected option: " + selectedOption);
                welcomeLabel.setText(selectedOption);
                if (selectedOption.equals("Logout")){
                    setVisible(false);
                    infoTextArea.setText("");
                    LoginWindow lw = new LoginWindow();
                    lw.setVisible(true);
                }
            }
        });

        retrieveStudentCourses();
    }

    private void retrieveStudentCourses() {
        boolean authenticated = signIn.isAuthenticated();

        if (authenticated) {
            listOfCoursesToPrint = signIn.getStudentCoursesFound();
            infoTextArea.setText(""); // Clear the existing content
            for (Course item : listOfCoursesToPrint) {
                infoTextArea.append(item.getCourseInfo());
            }
        } else {
            // Handle authentication failure
            JOptionPane.showMessageDialog(this, "Courses not added yet!");
        }
    }

}
