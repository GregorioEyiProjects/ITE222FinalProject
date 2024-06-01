package ITE222FinalProject.FrontEndV2.login;

import ITE222FinalProject.FrontEndV2.HomePage.HOME;
import ITE222FinalProject.FrontEndV2.FieldsValidation.LoginFieldsValidator;
import ITE222FinalProject.FrontEndV2.register.Register;
import ITE222FinalProject.backEnd.SignIn.SignInImplementation;

import javax.swing.*;
import java.io.IOException;

public class Login {

    private static String userName, password;

    public void createLoginPanel() throws IOException {

        Object[] options = { "Login", "Register" };

        int choice = JOptionPane.showOptionDialog(
                null,
                "Welcome to Coursera",
                "Select an option",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {

            showFields();

        }else if (choice == 1) {
            JOptionPane.showMessageDialog(null, "Going to the REGISTER form...");
            Register register = new Register();
            register.createRegisterPanel();
        }else {
            System.out.println("Cancel was pressed!");
        }

    }

    public void showFields() throws IOException {

        userName = JOptionPane.showInputDialog("Enter your username");
        if (userName == null){
            new Login().createLoginPanel();
        }else {
            password = JOptionPane.showInputDialog("Enter your password");
            if (password == null) {
                new Login().createLoginPanel();
            } else {

                LoginFieldsValidator fieldsValidator = new LoginFieldsValidator();
                boolean fieldValidate = fieldsValidator.validateNameAndPassword(userName, password);

                if (fieldValidate){

                    //Check if the user exist in the FILE, if no, ... or showMainPage if the user exist
                    boolean studentExist = studentAuth(userName, password);
                    if (studentExist){
                        JOptionPane.showMessageDialog(null, "Welcome back "+ userName);
                        HOME mainPage = new HOME(userName, password);
                        mainPage.createHomePanel();
                    }else {
                        JOptionPane.showMessageDialog(null, "Account not found with these credentials!");
                        createLoginPanel();
                    }

                }else {
                    JOptionPane.showMessageDialog(null, "The length is too short!");
                    new Login().createLoginPanel();
                }
            }
        }

    }

    private static boolean studentAuth(String userName, String password) {
        SignInImplementation signIn = new SignInImplementation();
        return signIn.studentAuth(userName, password); //Returning the true or false from the back-end if the Student exist
    }

}
