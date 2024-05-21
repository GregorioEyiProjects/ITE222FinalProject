package ITE222FinalProject.backEnd;

import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;
import ITE222FinalProject.backEnd.data.db.StudentFileModification;
import ITE222FinalProject.backEnd.data.db.StudentsFileCreation;
import ITE222FinalProject.frontEnd.login.LoginWindow;

public class Coursera {
    public static void main(String[] args) {
        createStudentFile();
    }

    private static void createStudentFile() {
        StudentsFileCreation sf = new StudentsFileCreation();
        //sf.createStudentFile();
        StudentFileModification sfm = new StudentFileModification();
        //sfm.addStudentToTheFile();

        GetStudentInfoFromFile students = new GetStudentInfoFromFile();
        students.readStudentFile();

        //Launching the Login JFrame here
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);


    }
}
