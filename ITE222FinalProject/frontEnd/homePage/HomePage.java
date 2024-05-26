package ITE222FinalProject.frontEnd.homePage;

import ITE222FinalProject.backEnd.SignIn.SignInImplementation;
import ITE222FinalProject.backEnd.classes.Course;
import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;
import ITE222FinalProject.backEnd.data.db.StudentsCoursesFile;
import ITE222FinalProject.backEnd.data.db.UpdateOrDeleteStudentField;
import ITE222FinalProject.frontEnd.login.LoginWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends JFrame implements MouseListener {

    private JList<String> optionList;
    private JLabel welcomeLabel;
    private JLabel descriptionLabel;
    private JTextArea infoTextArea;

    private java.util.List<Course> listOfCoursesToPrint = new ArrayList<>();

    //private SignInImplementation signIn = SignInImplementation.getInstance();
    private SignInImplementation signIn = new SignInImplementation();

    private String nameComing, passwordComing;

    private JPanel menuOptionPanel;
    private JPanel panel;

    private String storedStudentEmail_, _studentEmailStored;

    private String newStudentUserName, newStudentPasswordRepeat;

    public HomePage(String studentName, String password) {

        this.nameComing = studentName;
        this.passwordComing = password;

        /*Home page JFrame default setting*/
        setTitle("E-Learning Platform");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
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
        panel = new JPanel(new BorderLayout());
        menuOptionPanel = new JPanel(new BorderLayout());

        // Add components to the panel
        menuOptionPanel.add(optionList, BorderLayout.WEST);
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(descriptionLabel, BorderLayout.CENTER);
        panel.add(menuOptionPanel, BorderLayout.WEST);

        /* Add text area to display information  - Start*/

        infoTextArea = new JTextArea(600, 10);
        infoTextArea.setEditable(false);
        infoTextArea.addMouseListener(this);

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

            if (!e.getValueIsAdjusting()) {  // Check if the selection is stable
                String selectedOption = optionList.getSelectedValue();

                if (selectedOption != null) {

                    //JOptionPane.showMessageDialog(HomePage.this, "Selected option: " + selectedOption);
                    welcomeLabel.setText(selectedOption);

                    if (selectedOption.equals("Logout")) {
                        setVisible(false);
                        infoTextArea.setText("");
                        StudentsCoursesFile scf = new StudentsCoursesFile();
                        scf.readStudentCourseFile();
                        LoginWindow lw = new LoginWindow();
                        lw.setVisible(true);

                    } else if (selectedOption.equals("My Courses")) {

                        displayStudentCourses();

                    } else if (selectedOption.equals("Browse Courses")) {

                        //To retrieve the student email
                        _studentEmailStored = getStudentEmail();
                        //display the courses first before showing the dialog
                        Course c = new Course();
                        infoTextArea.setText("");
                        infoTextArea.append("Click on 'BUY' to buy a course" + "\n");
                        for (Course course : c.getListOfCourses()) {
                            infoTextArea.append(course.getCourseInfo2());
                        }

                    } else if (selectedOption.equals("View Profile")) {

                        if (this.newStudentUserName == null && this.newStudentPasswordRepeat == null) {
                            displayStudentInfo();
                        } else if (this.newStudentUserName != null && this.newStudentPasswordRepeat == null) {
                            displayStudentInfoAfterModification(this.newStudentUserName, this.passwordComing);
                        } else if (this.newStudentUserName == null && this.newStudentPasswordRepeat != null) {
                            displayStudentInfoAfterModification(this.nameComing, this.newStudentPasswordRepeat);
                        }

                        displayStudentInfo();

                    }

                }
            }

        });

        //Display data for the first time
        retrieveStudentCourses();

    }


    //Methods
    private void displayStudentCourses() {
        //I read the file again
        //StudentsCoursesFile scf = StudentsCoursesFile.getInstance();
        StudentsCoursesFile scf = new StudentsCoursesFile();
        scf.readStudentCourseFile();

        String email = getStudentEmail();

        System.out.println("Is this the email: "+ email);

        //I load the all the student in this HashMap
        HashMap<String, String> coursesLoadedFromFile = scf.getStudentsEmailAndCoursesLoaded();

        boolean emailFound = false;

        if (!coursesLoadedFromFile.isEmpty()) {

            infoTextArea.setText("");
            infoTextArea.append("Click on 'DELETE' do delete a course" + "\n");

            for (Map.Entry<String, String> courses : coursesLoadedFromFile.entrySet()) {

                String key = courses.getKey();
                String keyValue = courses.getValue();

                if (key.endsWith("_gmail")) {

                    String storedStudentEmail = keyValue;

                    if (storedStudentEmail.equals(email)) {

                        emailFound = true;

                        String storedStudentCourseCode = coursesLoadedFromFile.get(key.replace("_gmail", "_courseCode"));

                        signIn.createCourse2(storedStudentCourseCode);
                        List<Course> listOfcCourse = signIn.getStudentCoursesFound2(); //Returns a List

                        if (!listOfcCourse.isEmpty()) {
                            for (Course course : listOfcCourse) {
                                infoTextArea.append(course.getCourseInfo());
                            }
                        }

                    }
                }

            }

        } else {
            infoTextArea.setText("Courses not added yet!");
        }

        if (!emailFound) {
            infoTextArea.setText("Courses not added yet!");
        }

    }

    private void retrieveStudentCourses() {

        SignInImplementation sign_In = new SignInImplementation();
        sign_In.studentAuth(this.nameComing, this.passwordComing);

        boolean authenticated = sign_In.isAuthenticated();
        if (authenticated) {
            listOfCoursesToPrint = sign_In.getStudentCoursesFound();
            if (!listOfCoursesToPrint.isEmpty()) {
                infoTextArea.setText("");
                infoTextArea.append("Click on 'DELETE' do delete a course" + "\n");
                for (Course item : listOfCoursesToPrint) {
                    infoTextArea.append(item.getCourseInfo());
                }
            } else {
                infoTextArea.setText("Courses not added yet!");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int start = infoTextArea.getSelectionStart();
        int end = infoTextArea.getSelectionEnd();
        boolean courseWasNotFound = true;

        String selectedText = infoTextArea.getText().substring(start, end);

        if (selectedText.equals("BUY")) {

            Course c = new Course();

            String codeCourseChosen = JOptionPane.showInputDialog("Enter the codeCourse you would like to buy");
            System.out.println("Option for the course chosen: " + codeCourseChosen);

            //TO display a single Course
            if (codeCourseChosen != null) {

                for (Course course : c.getListOfCourses()) {
                    if (course.getCourseCode().equals(codeCourseChosen)) {
                        infoTextArea.setText("");
                        infoTextArea.append(c.getCourse(
                                course.getName(),
                                course.getCourseCode(),
                                course.getInstructor(),
                                course.getHours(),
                                course.getTopic(),
                                course.getPublicationDate(),
                                course.getWeeklyHours(),
                                course.getPrice()
                        ));

                        courseWasNotFound = false;
                        break;
                    }
                }

            } else {
                System.out.println("Cancel was pressed");
            }


            if (!courseWasNotFound) {
                String[] addOrCancelValue = {"Buy", "Cancel"};
                int result = JOptionPane.showOptionDialog(
                        this,
                        "Would you like to buy the course?",
                        "Add course",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        addOrCancelValue,
                        addOrCancelValue[0]);

                System.out.println("Option result: " + result);

                if (result == JOptionPane.YES_OPTION) {
                    System.out.println("Buy");
                    sendCourseToBeAdded(codeCourseChosen);
                } else if (result == JOptionPane.NO_OPTION) {
                    System.out.println("Cancel");
                }
            } else {
                // nothing for now
                //System.out.println("Cancel was pressed");
                JOptionPane.showMessageDialog(this, "Course not found!");
            }

        } else if (selectedText.equals("UPDATE")) {

            try {
                showFields();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } else if (selectedText.equals("DELETE")) {

            deleteCourse();

        } else {
            // nothing for now
            System.out.println("Exit of the JOptionPane");
        }


    }

    private void deleteCourse() {

        String courseCodeToDelete = JOptionPane.showInputDialog("Enter the courseCode");
        String storedStudentEmail;

        if (courseCodeToDelete != null) {

            UpdateOrDeleteStudentField uodsf = new UpdateOrDeleteStudentField();

            //To be able to retrieve the email
            GetStudentInfoFromFile gsiff = new GetStudentInfoFromFile();
            gsiff.readStudentFile();

            HashMap<String, String> studentsInfoComingFromFile = gsiff.getStudentsListLoaded();

            for (Map.Entry<String, String> entry : studentsInfoComingFromFile.entrySet()) {

                String key = entry.getKey();
                String keyValue = entry.getValue();

                if (key.endsWith("_userName")) {

                    String storedUserName = keyValue;
                    String storedStudentPassword = studentsInfoComingFromFile.get(key.replace("_userName", "_password"));

                    if (storedUserName.equals(this.nameComing) && storedStudentPassword.equals(this.passwordComing)) {

                        storedStudentEmail = studentsInfoComingFromFile.get(key.replace("_userName", "_email"));

                        try { //A try cast cuz I am trying to read and modify a file

                            boolean delete = uodsf.deleteStudentCourse(storedStudentEmail, courseCodeToDelete);
                            if (delete) {
                                JOptionPane.showMessageDialog(this, "Successfully delete!");
                                displayStudentCourses();
                            } else {
                                JOptionPane.showMessageDialog(this, "Course not found!");
                            }

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(this, "The course was not found!");
                            throw new RuntimeException(ex);
                        }

                        break;
                    }
                }

            }

        } else {
            System.out.println("Does not want to delete");
        }
    }

    private void showFields() throws IOException {

        String[] fieldsOptions = {"Username", "Password"};
        int result = JOptionPane.showOptionDialog(
                this,
                "Press the option you want to perform",
                "Update Field",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                fieldsOptions,
                fieldsOptions[0]);

        System.out.println("Option result: " + result);

        switch (result) {
            case 0: //  = JOptionPane.YES_OPTION
                newStudentUserName = JOptionPane.showInputDialog("Enter the new username");
                boolean validStudentUsername = usernameValidation(newStudentUserName);
                if (validStudentUsername) {
                    UpdateOrDeleteStudentField usf = new UpdateOrDeleteStudentField();
                    usf.updatingStudentFile(storedStudentEmail_, newStudentUserName, this.passwordComing);
                    JOptionPane.showMessageDialog(this, "Username successfully update!");
                    displayStudentInfoAfterModification(newStudentUserName, this.passwordComing);
                } else {
                    JOptionPane.showMessageDialog(this, "Username no valid!");
                }
                break;
            case 1: // = JOptionPane.NO_OPTION
                String newStudentPassword = JOptionPane.showInputDialog("Enter the new password");
                newStudentPasswordRepeat = JOptionPane.showInputDialog("Please, enter the password again");
                if (newStudentPassword.equals(newStudentPasswordRepeat)) {
                    boolean validPassword = passwordValidation(newStudentPasswordRepeat);
                    if (validPassword) {
                        UpdateOrDeleteStudentField usf = new UpdateOrDeleteStudentField();
                        usf.updatingStudentFile(storedStudentEmail_, this.nameComing, newStudentPasswordRepeat);
                        JOptionPane.showMessageDialog(this, "Password successfully update!");
                        displayStudentInfoAfterModification(this.nameComing, newStudentPasswordRepeat);
                    } else {
                        JOptionPane.showMessageDialog(this, "The length is too short");
                    }
                }

                break;

            default:
                System.out.println("Exit");
        }


    }

    private void displayStudentInfo() {

       // getStudentEmail();

        //Get the students from the list
        GetStudentInfoFromFile gsiff = new GetStudentInfoFromFile();
        gsiff.readStudentFile();

        //I load the students coming into this hashMap
        HashMap<String, String> studentsInfoComingFromFile = gsiff.getStudentsListLoaded();

        System.out.println(studentsInfoComingFromFile.size());

        //Trying to find similarities
        for (Map.Entry<String, String> entry : studentsInfoComingFromFile.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            if (key.endsWith("_userName")) {
                String storedName = value;
                storedStudentEmail_ = studentsInfoComingFromFile.get(key.replace("_userName", "_email"));
                String storedPassword = studentsInfoComingFromFile.get(key.replace("_userName", "_password"));
                String storedDateOfBirth = studentsInfoComingFromFile.get(key.replace("_userName", "_dateOfBirth"));
                //I check if they match
                if (nameComing.equals(storedName) && passwordComing.equals(storedPassword)) {
                    displayStudentInfoIntoTextArea(storedName, storedStudentEmail_, storedDateOfBirth);
                    break;
                }

            }

        }
    }

    private String getStudentEmail() {

        String returnThisEmail = "";
        GetStudentInfoFromFile gsiff = new GetStudentInfoFromFile();
        gsiff.readStudentFile();
        HashMap<String, String> studentsInfoComingFromFile = gsiff.getStudentsListLoaded();
        for (Map.Entry<String, String> entry : studentsInfoComingFromFile.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.endsWith("_userName")) {
                String storedUsername = value;
                String storedPass = studentsInfoComingFromFile.get(key.replace("_userName", "_password"));
                if (storedUsername.equals(this.nameComing) && storedPass.equals(this.passwordComing)){
                    returnThisEmail = studentsInfoComingFromFile.get(key.replace("_userName", "_email"));
                    break;
                }
            }

        }
        return returnThisEmail;
    }

    private void displayStudentInfoAfterModification(String newStudentName, String newStudentPassword) {

        //Get the students from the list
        GetStudentInfoFromFile gsiff = new GetStudentInfoFromFile();
        gsiff.readStudentFile();

        //I load the students coming into this hashMap
        HashMap<String, String> studentsInfoComingFromFile = gsiff.getStudentsListLoaded();

        System.out.println(studentsInfoComingFromFile.size());

        //Trying to find similarities
        for (Map.Entry<String, String> entry : studentsInfoComingFromFile.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            if (key.endsWith("_userName")) {
                String storedName = value;
                storedStudentEmail_ = studentsInfoComingFromFile.get(key.replace("_userName", "_email"));
                String storedPassword = studentsInfoComingFromFile.get(key.replace("_userName", "_password"));
                String storedDateOfBirth = studentsInfoComingFromFile.get(key.replace("_userName", "_dateOfBirth"));
                //I check if they match
                if (newStudentName.equals(storedName) && newStudentPassword.equals(storedPassword)) {
                    displayStudentInfoIntoTextArea(storedName, storedStudentEmail_, storedDateOfBirth);
                    break;
                }

            }

        }
    }

    private void displayStudentInfoIntoTextArea(String storedName, String storedEmail, String storedDateOfBirth) {
        System.out.println("----------------- Student Info ----------------");
        String output =
                "\nUsername: " + storedName
                        + "\nEmail: " + storedEmail
                        + "\nDate of birth: " + storedDateOfBirth;
        infoTextArea.setText("");
        infoTextArea.append("Click on 'UPDATE' to update a field" + "\n");
        infoTextArea.append(output);
    }

    private void sendCourseToBeAdded(String courseCode) {

        System.out.println("Course code: " + courseCode);
        String storedStudentEmail = "";
        String storedStudentPassword = "";

        GetStudentInfoFromFile gsiff = new GetStudentInfoFromFile();
        gsiff.readStudentFile();

        HashMap<String, String> studentsInfoComingFromFile = gsiff.getStudentsListLoaded();

        System.out.println(studentsInfoComingFromFile.size());

        for (Map.Entry<String, String> entry : studentsInfoComingFromFile.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.endsWith("_userName")) {
                String storedName = value;
                storedStudentPassword = studentsInfoComingFromFile.get(key.replace("_userName", "_password"));
                if (storedName.equals(nameComing) && storedStudentPassword.equals(passwordComing)) {
                    storedStudentEmail = studentsInfoComingFromFile.get(key.replace("_userName", "_email"));
                    break;
                }
            }
        }
        System.out.println("Stored email: " + storedStudentEmail);

        //writing the student course to the file
        //StudentsCoursesFile sgcf = StudentsCoursesFile.getInstance();
        StudentsCoursesFile sgcf = new StudentsCoursesFile();
        sgcf.writeCourseIntoStudentCourseFile(storedStudentEmail, courseCode);
        JOptionPane.showMessageDialog(this, "Purchase made successfully!");
    }

    private boolean passwordValidation(String newStudentPasswordRepeat) {
        return newStudentPasswordRepeat.length() > 6;
    }

    private boolean usernameValidation(String newStudentUserName) {
        return newStudentUserName.length() > 4;
    }

    //Getters
    public JTextArea getInfoTextArea() {
        return infoTextArea;
    }

    // I left these methods empty as I only care about click events, and I can't delete them cuz the implementation requires those methods to be implemented.
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
