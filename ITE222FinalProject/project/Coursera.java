package ITE222FinalProject.project;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coursera {

    private static Course chosenCourse;

    public static void main(String[] args) {
        createCourses();
    }

    private static void createCourses() {
        Course c1 = new Course("Information Technology Fundamentals", "ITE101", "Lanka", 40, "Introduction to Computers", "", false, 4, 0.0);
        Course c2 = new Course("Discrete Mathematics", "ITE102", "Amin", 36, "Logic and Problem Solving", "", false, 3, 0.0);
        Course c3 = new Course("Intro to Data Structures and Algorithms", "ITE103", "Amin", 48, "Data Organization and Manipulation", "", false, 4, 0.0);
        Course c4 = new Course("Computer Organization", "ITE104", "Amin", 40, "Computer Architecture and Components", "", false, 4, 0.0);
        Course c5 = new Course("Social and Professional Issues in IT", "ITE210", "Nay", 32, "Ethics and IT in Society", "", false, 3, 0.0);
        Course c6 = new Course("System Analysis, Design, and Implementation", "ITE321", "Amin", 52, "Software Development Lifecycle", "", false, 5, 0.0);
        Course c7 = new Course("Web Development I", "ITE120", "Amin", 44, "Building Web Applications", "", false, 4, 0.0);
        Course c8 = new Course("Intro to Data Science", "ITE224", "Farouz", 48, "Data Analysis and Visualization", "", false, 4, 120.0); // Example with a price
        Course c9 = new Course("Intro to Internet of Things", "ITE233", "Atikom", 40, "Connecting Devices to the Internet", "", false, 4, 0.0);
        Course c10 = new Course("Programming I", "ITE221", "Farouz", 48, "Fundamentals of Programming", "", false, 4, 0.0);
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
        //createStudents(c1, c2, courses);


        //Student test.
        Student student = new Student("Gregorio", "10/10/1900", "gregorio@example.com", "1234");

        createMenu(courses, student);
    }


    private static void createMenu(List<Course> courses, Student student) {
        Scanner sc = new Scanner(System.in);
        System.out.println( ConsoleColors.RESET+ "*************************** Temporal Menu ***************************");
        System.out.println("1. My Courses \n2. Browse Courses \n3. View Profile \n4. Logout \nEnter e number:");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                try {
                    if (chosenCourse == null) {
                        System.out.println(ConsoleColors.RED+"No courses added yet!");
                        createMenu(courses, student);
                    } else {
                        getStudentCourses(chosenCourse, student);
                        createMenu(courses, student);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;

            case 2:
                //display the available courses
                System.out.println("\n*************************** List of available courses: ***************************");
                courses.forEach(c -> System.out.println(c.toString()));

                performStudentOption(student, courses);
                createMenu(courses, student);
                break;

            case 3:
                student.get_info3();
                createMenu(courses, student);
                break;

            case 4:
                System.exit(0);
                break;
        }

    }

    private static void getStudentCourses(Course course, Student student) {
        student.get_info();
    }

    private static void performStudentOption(Student student, List<Course> courses) {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE+ "\nType the 'courseCode' to buy it or 'back' to go back");
        String studentOption = sc.nextLine();
        System.out.println(studentOption);
        for (Course c : courses) {
            if (c.getCourseCode().equals(studentOption) && !c.isBought()) {
                chosenCourse = new Course(
                        c.getName(),
                        c.getCourseCode(),
                        c.getInstructor(),
                        c.getHours(),
                        c.getTopic(),
                        c.getPublicationDate(),
                        c.isBought(),
                        c.getWeeklyHours(),
                        c.getPrice()
                );
                student.enroll(chosenCourse);
                c.setBought(true);
                System.out.println(ConsoleColors.GREEN  +c.getCourseCode()+" purchased successfully!\nYou can see your purchases in 'My Courses'\n");

            } else if (studentOption.equals("back")) {
                createMenu(courses, student);
            }
        }
    }

   /* private static void createStudents(Course course, Course course2, List<Course> courses) {

        //Student creation
        Student student1 = new Student("Gregorio", "10/10/1900", "gregorio@example.com", "1234");
        Student student2 = new Student("StudentName", "10/10/1900", "student@example.com", "1234");

        //Student enrollment
        student1.enroll(course);
        student1.enroll(course2);
        course.enroll(student1);
        course2.enroll(student1);

        //printing info
        System.out.println("\n*************************** Student info ***************************");
        for (Student student : course.enrolledStudents) {
            student.get_info();
        }

        //List of available courses
        //System.out.println("\n*************************** List of available courses: ***************************");
        //courses.forEach(c -> System.out.println(c.toString()));

    }*/

}
