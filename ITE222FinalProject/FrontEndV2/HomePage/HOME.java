package ITE222FinalProject.FrontEndV2.HomePage;

import ITE222FinalProject.FrontEndV2.login.Login;
import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HOME {

    private String nameComing, passwordComing;

    public HOME(String nameComing, String passwordComing) {
        this.nameComing = nameComing;
        this.passwordComing = passwordComing;
    }

    public void createHomePanel() throws IOException {

        try{
            // i know that this might give me an error when pressing the cancel button or the exit (X) button, that's why i am using
            // try catch to catch the error and redirect the user to the same page unless the user selects the option 4
            int option = Integer.parseInt(JOptionPane.showInputDialog("1. My courses. \n2. Browse courses. \n3. View profile. \n\n4. Logout"));

            switch (option) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Going to my 'MY COURSES'");
                    myCoursesMethod();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Going to my 'BROWSE COURSE'");
                    browseCourseMethod();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "VIEW PROFILE");
                    viewProfileMethod();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "LOGIN OUT");
                    new Login().createLoginPanel();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not found!");
                    new HOME(this.nameComing, this.passwordComing).createHomePanel();
                    break;
            }

        }catch (NumberFormatException | NullPointerException e){
            JOptionPane.showMessageDialog(null, "Option not valid");
            new HOME(this.nameComing, this.passwordComing).createHomePanel();
        }

    }

    private void myCoursesMethod() throws IOException {
        MyCoursesClass mcc = new MyCoursesClass(this.nameComing, this.passwordComing);
        boolean notEmpty = mcc.readCoursesFromFile();
        if (notEmpty) {
            JOptionPane.showMessageDialog(null, "Courses not found!");
            new HOME(this.nameComing, this.passwordComing).createHomePanel();
        } else {
            mcc.deleteCourse();
        }
    }

    private void browseCourseMethod() throws IOException{
        JOptionPane.showMessageDialog(null, "The courses will be printed in the CONSOLE LOG");
        BrowseCoursesClass bcc = new BrowseCoursesClass();
        bcc.displayAvailableCourses(this.nameComing, this.passwordComing);
        bcc.buyCourse(this.nameComing, this.passwordComing);
    }

    private void viewProfileMethod() throws IOException {
        new ViewProfileClass().readStudentFile2(getNameComing(), getPasswordComing());
    }


    public String getNameComing() {
        return nameComing;
    }

    public String getPasswordComing() {
        return passwordComing;
    }
}

