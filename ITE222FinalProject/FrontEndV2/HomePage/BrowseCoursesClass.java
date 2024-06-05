package ITE222FinalProject.FrontEndV2.HomePage;

import ITE222FinalProject.FrontEndV2.Extras.GetStudentInfo;
import ITE222FinalProject.backEnd.classes.Course;
import ITE222FinalProject.backEnd.classes.Student;
import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;
import ITE222FinalProject.backEnd.data.db.StudentsCoursesFile;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowseCoursesClass {

    public void displayAvailableCourses(String userName, String password) {

        GetStudentInfo gsi = new GetStudentInfo();

        Course c = new Course();
        for (Course course : c.getListOfCourses()) { // displaying the courses
            System.out.println(course.getCourseInfo2());
        }

    }

    public void buyCourse(String userName, String password) throws IOException {

        String[] options = {"BUY", "COURSE INFO"};

        int studentOption = JOptionPane.showOptionDialog(
                null,
                "Would you like to?",
                "Choose an option",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (studentOption == JOptionPane.YES_OPTION) { // Buy
            //JOptionPane.showMessageDialog(null, "BUY");
            buyCourseMethod(userName, password);
        } else if (studentOption == JOptionPane.NO_OPTION) { // Course info
            viewCourseInfo(userName, password);
        } else {
            new HOME(userName, password).createHomePanel();
        }


    }

    private void buyCourseMethod(String userName, String password) throws IOException {
        boolean courseWasNotFound = true;
        String option = JOptionPane.showInputDialog("Enter the code of the course you want to buy");

        if (option == null) { // cancel button
            JOptionPane.showMessageDialog(null, "We are sorry that you are no longer interested. \nCome back soon!");
            new HOME(userName, password).createHomePanel();
        } else {

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
                    "Would you like to buy " + option + " course?",
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

    private void viewCourseInfo(String userName, String password) throws IOException {
        boolean courseWasNotFound = true;
        String option = JOptionPane.showInputDialog("Enter a code of the course to view more details");

        if (option == null) { // cancel button
            new HOME(userName, password).createHomePanel();
        } else {
            //Create the course info
            Course c = new Course();
            for (Course course : c.getListOfCourses()) {
                if (course.getCourseCode().equals(option)) {
                    //System.out.println("********** Course info **********");
                    System.out.println(c.getCourse2(
                            course.getName(),
                            course.getCourseCode(),
                            course.getInstructor(),
                            course.getHours(),
                            course.getTopic(),
                            course.getPublicationDate(),
                            course.getWeeklyHours(),
                            course.getPrice()
                    ));
                    courseWasNotFound = false;
                    break;
                }
            }

        }

        if (!courseWasNotFound) {
            // bring the list of student with the same course
            printStudentWithSameCourseChosen(option, userName, password);
        } else {
            JOptionPane.showMessageDialog(null, "Course not found!");
            new HOME(userName, password).createHomePanel();
        }
    }

    private void printStudentWithSameCourseChosen(String option, String userName, String password) throws IOException {
        //read the file to find all the students.
        StudentsCoursesFile scf = new StudentsCoursesFile();
        scf.readStudentCourseFile();

        // Load the students found with their courses from the File
        HashMap<String, String> coursesLoadedFromFile = scf.getStudentsEmailAndCoursesLoaded();

        //Empty list of Strings to store the emails found
        List<String> emailsFromStudentCourses = new ArrayList<>();

        for (Map.Entry<String, String> entry : coursesLoadedFromFile.entrySet()) {
            String key = entry.getKey();
            String keyValue = entry.getValue();

            if (key.endsWith("_gmail")) {
                String gmailStored = keyValue;
                String courseCodeStored = coursesLoadedFromFile.get(key.replace("_gmail", "_courseCode"));
                if (courseCodeStored.equals(option)) {
                    emailsFromStudentCourses.add(gmailStored); // loading the array with the emails found
                }
            }
        }

        findStudentWithSameCourse(emailsFromStudentCourses, userName, password, option);
    }

    private void findStudentWithSameCourse(List<String> emailsFromStudentCoursesComing, String userName, String password, String option) throws IOException {

        //Empty list to store the students that have the same course
        List<Student> studentListWithSameCourse = new ArrayList<>();

        // reading the file to get the student to compare theirs emails
        GetStudentInfoFromFile gsff = new GetStudentInfoFromFile();
        gsff.readStudentFile();
        HashMap<String, String> studentsInfoComingFromFile = gsff.getStudentsListLoaded(); // loading the student

        String storedName, storedStudentEmail, storedPassword, storedDateOfBirth;

        for (Map.Entry<String, String> entry : studentsInfoComingFromFile.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            if (key.endsWith("_userName")) {
                storedName = value;
                storedStudentEmail = studentsInfoComingFromFile.get(key.replace("_userName", "_email"));
                storedPassword = studentsInfoComingFromFile.get(key.replace("_userName", "_password"));
                storedDateOfBirth = studentsInfoComingFromFile.get(key.replace("_userName", "_dateOfBirth"));
                //I check if they match
                for (String email : emailsFromStudentCoursesComing) {
                    if (email.equals(storedStudentEmail)) {
                        Student newStudent = new Student(
                                storedName,
                                storedDateOfBirth,
                                storedStudentEmail,
                                "********"
                        );
                        studentListWithSameCourse.add(newStudent);
                    }
                }

            }

        }


        //printing the student
        if (!studentListWithSameCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The info will be printed in the CONSOLE LOG");
            System.out.println("*********** Students enrolled in "+ option+" *********** \n");
            for (Student student : studentListWithSameCourse) {
                System.out.println(
                        student.get_info4(
                                student.getUsername(),
                                student.getEmail(),
                                student.getDateOfBirth()
                        ));
            }
            new HOME(userName, password).createHomePanel();
        }else {
            JOptionPane.showMessageDialog(null, "*********** Students enrolled in "+ option+ " ***********"+"\n           Student not found for this course");
            System.out.println("*********** Students enrolled in "+ option+" *********** \n");
            System.out.println("          Student not found for this course                ");
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
