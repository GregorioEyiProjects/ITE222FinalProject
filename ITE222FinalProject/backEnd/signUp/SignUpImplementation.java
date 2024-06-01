package ITE222FinalProject.backEnd.signUp;

import ITE222FinalProject.backEnd.data.db.StudentFileModification;

import javax.swing.*;

public class SignUpImplementation implements SignUp{
    @Override
    public boolean addStudent(String userName, String email, String password, String dateOfBirth) {
        boolean validData = validatingStudentInfo(userName, email, dateOfBirth);
        if (validData){
            StudentFileModification sfm = new StudentFileModification();
            sfm.addStudentToTheFile(userName, email, password, dateOfBirth);
            return true;
        }
        return false;
    }

    private boolean validatingStudentInfo(String userName, String email, String dateOfBirth) {
        return userName.length() >= 3 && email.length() > 5 && dateOfBirth.length() >= 5;
    }

}
