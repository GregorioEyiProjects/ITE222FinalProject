package ITE222FinalProject.project;

import java.util.ArrayList;
import java.util.List;

public class Coursera {

    public static void main(String[] args) {
        createCourses();
    }

    private static void createCourses() {
        Course c1 = new Course("Information Technology Fundamentals", "ITE101", "Lanka", "Description 1", 4);
        Course c2 = new Course("Discrete Mathematics", "ITE102", "Amin", "Description 2", 4);
        Course c3 = new Course("Intro to Data Structures and Algorithms", "ITE103", "Amin", "Description 3", 4);
        Course c4 = new Course("Computer Organization", "ITE104", "Amin", "Description 4", 4);
        Course c5 = new Course("Social and Professional Issues in IT", "ITE210", "Nay", "Description 5", 4);
        Course c6 = new Course("System Analysis, Design, and Implementation", "ITE321", "Amin", "Description 6", 4);
        Course c7 = new Course("Web Development I", "ITE120", "Amin", "Description 7", 4);
        Course c8 = new Course("Intro to Data Science", "ITE224", "Farouz", "Description 8", 4);
        Course c9 = new Course("Intro to Internet of Things", "ITE233", "Atikom", "Description 8", 4);
        Course c10 = new Course("Programming I", "ITE221", "Farouz", "Description 10", 4);
        //the rest of the courses here


        List<Course> courses = new ArrayList<>();
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);
        courses.add(c5);
        courses.add(c6);
        courses.add(c7);
        courses.add(c8);
        courses.add(c9);
        courses.add(c10);
        createStudents(c1, c2, courses);
    }

    
    private static void createStudents(Course course, Course course2, List<Course> courses) {

        //Student creation
        Student student1 = new Student("Gregorio", "gregorio@example.com");
        Student student2 = new Student("StudentName", "student@example.com");

        //Student enrollment
        student1.enroll(course);
        student1.enroll(course2);
        course.enroll(student1);

        //printing info
        System.out.println("\n*************************** Student info ***************************");
        for (Student student : course.enrolledStudents) {
            student.get_info();
        }

        //List of available courses
        System.out.println("\n*************************** List of available courses: ***************************");
        courses.forEach(c -> c.get_info());

    }

}
