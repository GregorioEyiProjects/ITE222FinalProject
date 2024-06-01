package ITE222FinalProject.FrontEndV2.FieldsValidation;

public class LoginFieldsValidator {

    public boolean validateNameAndPassword(String userName, String password) {
        return userName.length() >= 3 && password.length() >= 6;
    }
}
