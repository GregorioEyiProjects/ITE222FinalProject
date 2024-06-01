package ITE222FinalProject.FrontEndV2.HomePage;

import ITE222FinalProject.FrontEndV2.Extras.GetStudentInfo;
import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;
import ITE222FinalProject.backEnd.data.db.UpdateOrDeleteStudentField;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewProfileClass {

    private String storedStudentEmail, storedPassword, storedDateOfBirth;

    public void readStudentFile2(String nameComing, String passwordComing) throws IOException {
        GetStudentInfo gsi = new GetStudentInfo();
        storedStudentEmail = gsi.getEmail(nameComing, passwordComing);
        storedPassword = gsi.getStoredPassword_();
        storedDateOfBirth = gsi.getStoredDateOfBirth_();
        displayStudentInfo(nameComing, storedStudentEmail, storedDateOfBirth, passwordComing);
    }


    private void displayStudentInfo(String storedName, String storedStudentEmail, String storedDateOfBirth, String password) throws IOException {
        String output =
                "Username: " + storedName
            + "\nEmail: " + storedStudentEmail
            + "\nDate of birth: " + storedDateOfBirth
            + "\n\nEnter 1 or 2 to update a field"
            + "\n1. Username"
            + "\n2. Password";
        String option = JOptionPane.showInputDialog(null, output);

        if (option==null){
            new HOME(storedName, password).createHomePanel();
        }else {
            int option2 = Integer.parseInt(option);
            //Validate that 'option2' it's a number and if not,do not call the 'updateField()' method
            updateField(option2, storedName, storedPassword, storedDateOfBirth);
        }

    }

    private void updateField(int option, String newStudentNameComing , String newPasswordComing, String storedDateOfBirthComing) throws IOException {
        switch (option) {
            case 1:
                String newStudentUserName = JOptionPane.showInputDialog("Enter the new username");
                if (newStudentUserName==null){
                    displayStudentInfo(newStudentNameComing, storedStudentEmail, storedDateOfBirthComing, newPasswordComing);
                }else {
                    if (newStudentUserName.isEmpty()){
                        JOptionPane.showMessageDialog(null, "The field can not be empty");
                        updateField(1, newStudentNameComing, newPasswordComing, storedDateOfBirthComing);
                    }else {
                        boolean validStudentUsername = usernameValidation(newStudentUserName);
                        if (validStudentUsername) {
                            UpdateOrDeleteStudentField usf = new UpdateOrDeleteStudentField();
                            usf.updatingStudentFile(storedStudentEmail, newStudentUserName, newPasswordComing);
                            JOptionPane.showMessageDialog(null, "Username successfully update!");
                            displayStudentInfoAfterModification(newStudentUserName, storedStudentEmail, storedDateOfBirth, newPasswordComing);
                        } else {
                            JOptionPane.showMessageDialog(null, "Username no valid!");
                        }
                    }
                }
                break;

            case 2:

                String newStudentPassword = JOptionPane.showInputDialog("Enter the new password");
                if (newStudentPassword==null){
                    displayStudentInfo(newStudentNameComing, storedStudentEmail, storedDateOfBirthComing, newPasswordComing);
                }else{
                    if (newStudentPassword.equals(" ")){
                        JOptionPane.showMessageDialog(null, "The field can not be empty");
                        updateField(1, newStudentNameComing, newPasswordComing, storedDateOfBirthComing);
                    }else {
                        boolean validStudentPassword = passwordValidation(newStudentPassword);
                        if (validStudentPassword) {
                            UpdateOrDeleteStudentField usf = new UpdateOrDeleteStudentField();
                            usf.updatingStudentFile(storedStudentEmail, newStudentNameComing, newStudentPassword);
                            JOptionPane.showMessageDialog(null, "Password successfully update!");
                            displayStudentInfoAfterModification(newStudentNameComing, storedStudentEmail, storedDateOfBirth, newStudentPassword);
                        } else {
                            JOptionPane.showMessageDialog(null, "Password no valid!");
                        }
                    }
                }

                break;

            default:
                JOptionPane.showMessageDialog(null, "Option not valid!");
                new HOME(newStudentNameComing, newPasswordComing).createHomePanel();
                break;
        }
    }

    private boolean passwordValidation(String newStudentPassword) {
        return newStudentPassword.length() >= 6;
    }

    private boolean usernameValidation(String newStudentUserName) {
        return newStudentUserName.length()>=3;
    }

    private void displayStudentInfoAfterModification(String newStudentUserName, String email, String dateOfBirth, String password) throws IOException {
        String output =
                "Username: " + newStudentUserName
                + "\nEmail: " + email
                + "\nDate of birth: " + dateOfBirth;
        JOptionPane.showMessageDialog(null, output);
        new HOME(newStudentUserName, password).createHomePanel();

    }


}
