package ITE222FinalProject.FrontEndV2.register;

import ITE222FinalProject.FrontEndV2.HomePage.HOME;
import ITE222FinalProject.FrontEndV2.FieldsValidation.RegisterFieldsValidator;
import ITE222FinalProject.FrontEndV2.login.Login;
import ITE222FinalProject.backEnd.signUp.SignUpImplementation;

import javax.swing.*;
import java.io.IOException;

public class Register {
    private static String userName, password, email, dateOfBirth, passwordRepeat;



    public void createRegisterPanel() throws IOException {

        String newEmail;
        String newDOB;

        userName = JOptionPane.showInputDialog("Enter your username");
        if (userName == null){ // username is null
            new Login().createLoginPanel();
        }else { // username is not null

            password = JOptionPane.showInputDialog("Enter your password");
            if (password == null){ // password is null
                new Login().createLoginPanel();
            }else{ // password is not null

                passwordRepeat = JOptionPane.showInputDialog("Repeat the password, please");
                if (passwordRepeat == null){ // passwordRepeat is null
                    new Login().createLoginPanel();
                }else {// passwordRepeat is not null

                    //compare both password are the same and continue.
                    if (password.equals(passwordRepeat)){ // same password

                        email = JOptionPane.showInputDialog("Enter your email");
                        boolean validEmail = validateEmail2(email);
                        if (!validEmail){
                            boolean wrongEmail = true;
                            while (wrongEmail){
                                JOptionPane.showMessageDialog(null, "Wrong email format!");
                                newEmail = JOptionPane.showInputDialog("Enter the email again");
                                wrongEmail = validateEmail2(newEmail); // it returns true is the email en correct
                                if (!wrongEmail){
                                    wrongEmail = true;
                                }else {
                                    dateOfBirth = JOptionPane.showInputDialog("Enter your date of birth. Format: **-**-****");
                                    boolean validDOB =  validateDOB(dateOfBirth);
                                    if (validDOB){
                                        validateFields(userName, password, email, dateOfBirth);
                                    }else {
                                        JOptionPane.showMessageDialog(null, "Wrong date of birth format");
                                    }
                                    break;
                                }
                            }
                        }else {
                            dateOfBirth = JOptionPane.showInputDialog("Enter your date of birth. Format: **-**-****");
                            boolean validDOB =  validateDOB(dateOfBirth);
                            if (validDOB){ // if at first it is correct
                                validateFields(userName, password, email, dateOfBirth);
                            }else { // if not

                                boolean wrongDOB = true;
                                while (wrongDOB){
                                    JOptionPane.showMessageDialog(null, "Wrong date of birth format");
                                    newDOB = JOptionPane.showInputDialog("Enter the date of birth again");
                                    wrongDOB = validateDOB(newDOB); // it returns true is the email en correct
                                    if (!wrongDOB){
                                        wrongDOB = true;
                                    }else {
                                        validateFields(userName, password, email, newDOB);
                                        break;
                                    }
                                }

                            }

                        }

                    }else if (passwordRepeat !=null){ //  not same password
                        JOptionPane.showMessageDialog(null, "The password does not match");
                        new Login().createLoginPanel();
                    }

                }
            }
        }

    }

    private static void validateFields(String userName, String password, String email, String dateOfBirth) throws IOException {
        RegisterFieldsValidator fieldsValidator = new RegisterFieldsValidator();
        boolean validData = fieldsValidator.validateData(userName, password, email, dateOfBirth);
        if (validData){
            boolean result = addStudentToTheFile(userName, password, email, dateOfBirth); // adding the student to the file.
            if (result){
                JOptionPane.showMessageDialog(null, "Successful registration!");
                HOME mainPage = new HOME(userName, password);
                mainPage.createHomePanel();
            }else{
                JOptionPane.showMessageDialog(null, "Something went wrong!");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Data no valid");
            new Login();
        }
    }

    private static boolean addStudentToTheFile(String userName, String password, String email, String dateOfBirth) {
        SignUpImplementation singUp = new SignUpImplementation();
        return singUp.addStudent(userName, email, password, dateOfBirth);
    }

    private boolean validateEmail2(String email){
        // Check there is ONE @ symbol somewhere in the string
        if (email.indexOf('@') != email.lastIndexOf('@') ||email.indexOf('@') == -1) {
            return false;
            // if both occurences are not the same, multiple @ within the string: returns false
        }

        // Splits the email using the index of the @ and ensures neither of them are empty
        String local = email.substring(0, email.indexOf("@"));
        String domain = email.substring(email.indexOf("@")+1);
        if (local.isBlank() || domain.isBlank()) {
            return false;
        }

        // Check the dot is somewhere in the domain part of the email
        int dot_position = domain.indexOf(".");
        if (dot_position == -1 || dot_position == 0 || dot_position == domain.length() -1) {
            // Ensures a dot is present, is right after the @ and that it is not at the end.
            return false;
        }

        // Checks there aren't consecutive dots
        if (email.contains("..")) {
            return false;
        }

        // Checks there is one character before the dot and two or more after the dot.
        if ((domain.substring(0, dot_position)).isEmpty() || (domain.substring(dot_position+1)).length() < 2) {
            return false;
        }
        return true;
    }

    public static boolean validateDOB(String DoB) {

        // Splitting the date into three components using '-' as a separator.
        String[] components = DoB.split("-");

        // Checks there are only three components
        if (components.length != 3) {
            return false;
        }
        // Assigns the variable outside of the try blocks


        int day, month, year;
        // Converts each components into string type, returning false if it can't.
        try {
            day = Integer.parseInt(components[0]);
            month = Integer.parseInt(components[1]);
            year = Integer.parseInt(components[2]);
        } catch (NumberFormatException e) {
            return false;
            // The user did not enter a valid date format
        }

        // Validates the range of each date components
        if (day < 1 || day > 31) {
            return false;
            // Checks the day component is valid
        }
        if (month < 1 || month > 12) {
            return false;
            // Checks the month component is valid
        }

        if (year < 1900 || year > 2024) {
            return false;
            // Checks the year component is valid
        }


        return true;
    }

}
