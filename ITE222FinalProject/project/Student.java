package ITE222FinalProject.project;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String username;
    private String DateOfBirth;
    private String email;
    private String password;

    private String courseToken;
    private List<Course> enrolledCourses;

    public Student(String username, String dateOfBirth, String email, String password) {
        this.username = username;
        DateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.enrolledCourses = new ArrayList<>();
    }

/*    public Student(String username, String dateOfBirth, String email, String password, String courseToken) {
        this.username = username;
        DateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.courseToken = courseToken;
        this.enrolledCourses = new ArrayList<>();
    }*/

    public void enroll(Course course){
        this.enrolledCourses.add(course);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }


    public void get_info() {
        System.out.println(ConsoleColors.BLACK_BOLD+ConsoleColors.BLUE_BACKGROUND+ "\nStudent enrolled courses:");
        for (Course course : enrolledCourses) {
            System.out.println(ConsoleColors.RESET+course.toString2());
        }
    }

    public void get_info2() {
        System.out.println("Name: " + getUsername() + "\nEmail: " + getEmail());
        System.out.println("\nStudent enrolled courses:\n");
        for (Course course : enrolledCourses) {
            System.out.println(course.toString());
        }
    }

    public void get_info3() {
        System.out.println(ConsoleColors.BLACK_BOLD+ConsoleColors.BLUE_BACKGROUND+ "\nStudent profile");
        System.out.println(ConsoleColors.RESET+ "Name: " + getUsername() + "\nEmail: " + getEmail());
        System.out.println("\nYour courses:");
        if (enrolledCourses.isEmpty()){
            System.out.println(ConsoleColors.RED+"No courses added yet!");
        }else {
            for (Course course : enrolledCourses) {
                System.out.println(course.toString());
            }
        }
    }
}
