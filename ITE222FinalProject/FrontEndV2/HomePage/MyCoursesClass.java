package ITE222FinalProject.FrontEndV2.HomePage;

import Assignments.assignment4.Book;
import ITE222FinalProject.FrontEndV2.Extras.GetStudentInfo;
import ITE222FinalProject.backEnd.SignIn.SignInImplementation;
import ITE222FinalProject.backEnd.classes.Course;
import ITE222FinalProject.backEnd.data.db.StudentsCoursesFile;
import ITE222FinalProject.backEnd.data.db.UpdateOrDeleteStudentField;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyCoursesClass {

    private String userName, password;

    public MyCoursesClass(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean readCoursesFromFile() throws IOException {
        //I read the file again
        StudentsCoursesFile scf = new StudentsCoursesFile();
        scf.readStudentCourseFile();

        GetStudentInfo gsi = new GetStudentInfo();
        String email = gsi.getEmail(this.userName, this.password);

        JOptionPane.showMessageDialog(null, "The courses will be printed in the CONSOLE LOG");

        //I load the all the student in this HashMap
        HashMap<String, String> coursesLoadedFromFile = scf.getStudentsEmailAndCoursesLoaded();

        boolean emailFound = false;

        if (!coursesLoadedFromFile.isEmpty()) {

            int index =1;
            SignInImplementation signIn = new SignInImplementation();
            System.out.println("********** Student courses **********");

            for (Map.Entry<String, String> courses : coursesLoadedFromFile.entrySet()) {

                String key = courses.getKey();
                String keyValue = courses.getValue();

                if (key.endsWith("_gmail")) {

                    String storedStudentEmail = keyValue;

                    if (storedStudentEmail.equals(email)) {

                        emailFound = true;

                        String storedStudentCourseCode = coursesLoadedFromFile.get(key.replace("_gmail", "_courseCode"));

                        signIn.createCourse2(storedStudentCourseCode);
                        List<Course> listOfcCourse = signIn.getStudentCoursesFound2(); //Returns a List

                        if (!listOfcCourse.isEmpty()) {


                            for (Course course : listOfcCourse) {
                                System.out.println(course.getCourseInfo3(index));
                                index++;
                            }
                        }

                    }
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Courses not added yet!");
        }

        if (!emailFound) {
            return true;
        }

        return false;
    }

    public void deleteCourse() throws IOException {
        System.out.println("\n\nWould you like to delete a course? \nType: Yes/No");
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine();
        if (result.isEmpty()){
            System.out.println("The field can not be empty:");
            // main page or my courses
        }else {
            String yes = result.toLowerCase();
            String no = result.toLowerCase();

            if ("yes".equals(yes)){
                String courseCode = JOptionPane.showInputDialog("Enter the course code");
                // check before deleting that it is a string
                if (courseCode == null) {// if the user presses cancel
                    JOptionPane.showMessageDialog(null, "OKAY!");
                    new HOME(this.userName, this.password).createHomePanel();
                } else if (courseCode.isEmpty()){ // if the user presses enter without the courseCode
                    JOptionPane.showMessageDialog(null, "Option not valid!");
                    new HOME(this.userName, this.password).createHomePanel();
                } else{
                    deleteCourseByCourseCode(courseCode);
                }

            }else if ("no".equals(no)){
                JOptionPane.showMessageDialog(null, "OKEY!");
                new HOME(this.userName, this.password).createHomePanel();
            }else {
                JOptionPane.showMessageDialog(null, "Option not valid!");
                new HOME(this.userName, this.password).createHomePanel();
            }
        }

    }

    private void deleteCourseByCourseCode(String courseCode) throws IOException {
        UpdateOrDeleteStudentField uodsf = new UpdateOrDeleteStudentField();
        GetStudentInfo gsi = new GetStudentInfo();
        String storedStudentEmail = gsi.getEmail(this.userName, this.password);

        boolean delete = uodsf.deleteStudentCourse(storedStudentEmail, courseCode);
        if (delete) {
            JOptionPane.showMessageDialog(null, "Successfully delete!");
            readCoursesFromFile();
            new HOME(this.userName, this.password).createHomePanel();
        } else {
            JOptionPane.showMessageDialog(null, "Course not found!");
            new HOME(this.userName, this.password).createHomePanel();
        }

    }

}
