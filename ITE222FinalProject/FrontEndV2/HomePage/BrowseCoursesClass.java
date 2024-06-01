package ITE222FinalProject.FrontEndV2.HomePage;

import ITE222FinalProject.FrontEndV2.Extras.GetStudentInfo;
import ITE222FinalProject.backEnd.classes.Course;
import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;
import ITE222FinalProject.backEnd.data.db.StudentsCoursesFile;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BrowseCoursesClass {

    public void displayAvailableCourses(String userName, String password){

        GetStudentInfo gsi = new GetStudentInfo();

        Course c = new Course();
        for (Course course : c.getListOfCourses()) { // displaying the courses
            System.out.println(course.getCourseInfo2());
        }

    }

    public void buyCourse(String userName, String password) throws IOException {
        boolean courseWasNotFound = true;
        String option = JOptionPane.showInputDialog("Would you like to buy a course?"+"\nIf yes, enter the code of the course of your preference");


        if (option == null){ // cancel button
            JOptionPane.showMessageDialog(null, "We are sorry that you are no longer interested. \nCome back soon!");
            new HOME(userName, password).createHomePanel();
        }else {

            Course c = new Course();
            for (Course course : c.getListOfCourses()) {
                if (course.getCourseCode().equals(option)) {
                    c.getCourse(
                            course.getName(),
                            course.getCourseCode(),
                            course.getInstructor(),
                            course.getHours(),
                            course.getTopic(),
                            course.getPublicationDate(),
                            course.getWeeklyHours(),
                            course.getPrice()
                    );

                    courseWasNotFound = false;
                    break;
                }
            }

        }

        if (!courseWasNotFound) {
            String[] addOrCancelValue = {"Buy", "Cancel"};
            int result = JOptionPane.showOptionDialog(
                    null,
                    "Would you like to buy "+option+" course?",
                    "Add course",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    addOrCancelValue,
                    addOrCancelValue[0]);

            //System.out.println("Option result: " + result);

            if (result == JOptionPane.YES_OPTION) {
                //System.out.println("Buy");
                sendCourseToBeAdded(option, userName, password); //buying the course
            } else if (result == JOptionPane.NO_OPTION) {
                new HOME(userName, password).createHomePanel();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Course not found!");
            new HOME(userName, password).createHomePanel();
        }

    }

    private void sendCourseToBeAdded(String option, String userName, String password) throws IOException {
        GetStudentInfo gsi = new GetStudentInfo();
        String emailComing_ = gsi.getEmail(userName, password);
        StudentsCoursesFile sgcf = new StudentsCoursesFile();
        sgcf.writeCourseIntoStudentCourseFile(emailComing_, option);
        JOptionPane.showMessageDialog(null, "Purchase made successfully!");
        new HOME(userName, password).createHomePanel();
    }

}
