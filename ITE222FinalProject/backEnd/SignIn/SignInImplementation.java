package ITE222FinalProject.backEnd.SignIn;

import ITE222FinalProject.backEnd.classes.Course;
import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;
import ITE222FinalProject.backEnd.data.db.StudentsCoursesFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInImplementation implements SignIn{

    //private static SignInImplementation instance;

    private HashMap<String, String> studentListComing = new HashMap<>(); // student list coming from the file
    private HashMap<String, String> studentsEmailAndCourses = new HashMap<>(); //Email and courses coming from the file
    private List<Course> listOfCoursesComing = new ArrayList<>(); // List of the default courses
    private List<Course> studentCoursesFound = new ArrayList<>(); // List of the default courses that match with the student

    private List<Course> studentCoursesFound2 = new ArrayList<>(); // List of the default courses that match with the student


    private String courseCodeStored = "";

    private String storedStudentEmail;

    private GetStudentInfoFromFile students = new GetStudentInfoFromFile();
    //private StudentsCoursesFile scf = StudentsCoursesFile.getInstance();
    private StudentsCoursesFile scf = new StudentsCoursesFile();

    private boolean isAuthenticated;
    public SignInImplementation() {// Private constructor to prevent instantiation from other classes

    }

    //I created a singleton Object to avoid duplications of this object
    /*public static SignInImplementation getInstance() {
        if (instance == null) {
            synchronized (SignInImplementation.class) {
                if (instance == null) {
                    instance = new SignInImplementation();
                }
            }
        }
        return instance;
    }*/

    @Override
    public boolean studentAuth(String userName, String password) {

        // Clear the studentCoursesFound list before adding new courses in case a new Student is login
        studentCoursesFound.clear();

        students.readStudentFile();

        //Loading the student list coming from the file
        studentListComing = students.getStudentsListLoaded();
        //System.out.println(studentListComing);

        for (Map.Entry<String, String> entry : studentListComing.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            // Extract the student's username and password from the key-value pair
            if (key.endsWith("_userName")) {
                String storedUserName = value;
                String storedPassword = studentListComing.get(key.replace("_userName", "_password"));

                // Compare the stored username and password with the entered values.
                if (storedUserName.equals(userName) && storedPassword.equals(password)) {
                    storedStudentEmail = studentListComing.get(key.replace("_userName", "_email"));
                    //System.out.println("Student email who just log in " + storedStudentEmail);
                    getStudentCoursesFromList(storedStudentEmail);
                    isAuthenticated = true;
                    return true;
                }
            }

        }

        return false;
    }

    public void getStudentCoursesFromList(String studentEmail){

        scf.readStudentCourseFile();
        studentsEmailAndCourses = scf.getStudentsEmailAndCoursesLoaded();

        for (Map.Entry<String, String> entry : studentsEmailAndCourses.entrySet()) {

            String key = entry.getKey();

            if (key.endsWith("_gmail")) {
                String storedStudentEmail = entry.getValue();
                //System.out.println("\nStudent stored email in ..."+ storedStudentEmail);
                //System.out.println("Student email with registration "+ studentEmail);
                if (storedStudentEmail.equals(studentEmail)){
                    courseCodeStored = studentsEmailAndCourses.get(key.replace("_gmail", "_courseCode"));
                    //System.out.println("CourseCode: "+ courseCodeStored+ "\nEmail: "+ storedStudentEmail+ "\n");
                    createCourse(courseCodeStored);
                }
            }

        }

        //I am loading the courses into this empty list to compare courseCode store and the course code in the list
        if (!courseCodeStored.isEmpty()){
            System.out.println("\nStudent courses found");
            for (Course item: studentCoursesFound){
                System.out.println(item.getCourseInfo());
            }
        }else {
            System.out.println("Course not found!");
        }

    }

    public void createCourse(String courseCodeStored) {

        Course course = new Course();

        //System.out.println(studentCoursesFound.size());

        listOfCoursesComing = course.getListOfCourses();

        for (Course item: listOfCoursesComing){
            if (item.getCourseCode().equals(courseCodeStored)){
                Course courseFound = new Course( // create a new course
                        item.getName(),
                        item.getCourseCode(),
                        item.getInstructor(),
                        item.getHours(),
                        item.getTopic(),
                        item.getPublicationDate(),
                        item.getWeeklyHours(),
                        item.getPrice()
                );
                studentCoursesFound.add(courseFound);
            }
        }
    }

    public void createCourse2(String courseCodeStored) {

        Course course = new Course();

        //System.out.println(studentCoursesFound2.size());

        listOfCoursesComing = course.getListOfCourses();

        studentCoursesFound2.clear();

        for (Course item: listOfCoursesComing){
            if (item.getCourseCode().equals(courseCodeStored)){
                Course courseFound = new Course( // create a new course
                        item.getName(),
                        item.getCourseCode(),
                        item.getInstructor(),
                        item.getHours(),
                        item.getTopic(),
                        item.getPublicationDate(),
                        item.getWeeklyHours(),
                        item.getPrice()
                );
                studentCoursesFound2.add(courseFound);
            }
        }
    }

    public String getStoredStudentEmail() {
        return storedStudentEmail;
    }

    public List<Course> getStudentCoursesFound() {
        return studentCoursesFound;
    }

    public List<Course> getStudentCoursesFound2() {
        return studentCoursesFound2;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
