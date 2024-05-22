package ITE222FinalProject.backEnd;

import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;
import ITE222FinalProject.backEnd.data.db.StudentFileModification;
import ITE222FinalProject.backEnd.data.db.StudentsCoursesFile;
import ITE222FinalProject.backEnd.data.db.StudentsFileCreation;
import ITE222FinalProject.frontEnd.login.LoginWindow;

public class Coursera {
    public static void main(String[] args) {
        createStudentFile();
    }

    private static void createStudentFile() {
        StudentsFileCreation sf = new StudentsFileCreation();
        StudentFileModification sfm = new StudentFileModification();
        GetStudentInfoFromFile students = new GetStudentInfoFromFile();
        StudentsCoursesFile scf = new StudentsCoursesFile();
        //sf.createStudentFile();
        //sfm.addStudentToTheFile();
        //students.readStudentFile();
        //scf.StudentFileCreation();
        //scf.getStudentNameAndCourse();
        //System.out.println( scf.getStudentsEmailAndCoursesLoaded());

        //Launching the Login JFrame here
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);


    }
}
