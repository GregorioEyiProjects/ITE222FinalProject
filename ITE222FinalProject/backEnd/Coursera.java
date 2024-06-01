package ITE222FinalProject.backEnd;

import ITE222FinalProject.FrontEndV2.login.Login;
import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;
import ITE222FinalProject.backEnd.data.db.StudentFileModification;
import ITE222FinalProject.backEnd.data.db.StudentsCoursesFile;
import ITE222FinalProject.backEnd.data.db.StudentsFileCreation;
import ITE222FinalProject.frontEnd.login.LoginWindow;

import java.io.IOException;

public class Coursera {
    public static void main(String[] args) throws IOException {
        //launchFirstWindow();
        launchSecondWindow();
    }

    private static void launchFirstWindow() {
        //Launching the Login JFrame here
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
    }

    private static void launchSecondWindow() throws IOException {
        new Login().createLoginPanel();
    }


}
