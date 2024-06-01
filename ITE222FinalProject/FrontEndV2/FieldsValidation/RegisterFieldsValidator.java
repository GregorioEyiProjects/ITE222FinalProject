package ITE222FinalProject.FrontEndV2.FieldsValidation;

import javax.swing.*;

public class RegisterFieldsValidator {

    public boolean validateData(String userName, String password, String email, String dateOfBirth) {
        boolean validEmail = validateEmail(email);
        boolean validDateOfBirth = validateDateOfBirth(dateOfBirth);
        return userName.length() >= 3 && password.length() >= 6 && validEmail && validDateOfBirth;
    }

    private boolean validateDateOfBirth(String dateOfBirth) {
        return dateOfBirth.length()>=5;
    }

    private boolean validateEmail(String email) {
        return email.length()>5;
    }



}
