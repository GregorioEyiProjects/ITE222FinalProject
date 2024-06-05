package ITE222FinalProject.backEnd.classes;

import ITE222FinalProject.V1.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String username;
    private String DateOfBirth;
    private String email;
    private String password;

    private List<Course> studentCourses;

    public Student(String username, String dateOfBirth, String email, String password) {
        this.username = username;
        DateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.studentCourses = new ArrayList<>();
    }

    public void enroll(Course course){
        this.studentCourses.add(course);
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

    public void setStudentCourses(List<Course> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public List<Course> getStudentCourses() {
        return studentCourses;
    }


    public void get_info() {
        System.out.println(ConsoleColors.BLACK_BOLD+ConsoleColors.BLUE_BACKGROUND+ "\nStudent enrolled courses:");
        for (Course course : studentCourses) {
            System.out.println(ConsoleColors.RESET+course.getCourseInfo());
        }
    }

    public void get_info2() {
        System.out.println("Name: " + getUsername() + "\nEmail: " + getEmail());
        System.out.println("\nStudent enrolled courses:\n");
        for (Course course : studentCourses) {
            System.out.println(course.toString());
        }
    }

    public void get_info3() {
        System.out.println(ConsoleColors.BLACK_BOLD+ ConsoleColors.BLUE_BACKGROUND+ "\nStudent profile");
        System.out.println(ConsoleColors.RESET+ "Name: " + getUsername() + "\nEmail: " + getEmail());
        System.out.println("\nYour courses:");
        if (studentCourses.isEmpty()){
            System.out.println(ConsoleColors.RED+"No courses added yet!");
        }else {
            for (Course course : studentCourses) {
                System.out.println(course.toString());
            }
        }
    }

    public String get_info4(String userName, String gmail, String dateOfBirth){
        return "\n---------- Student info ----------" +"\n"+
                "Username= '" + userName + "'\n" +
                "Email= '" + gmail + "'\n" +
                "Date of birth= '" + dateOfBirth + "'\n" ;
    }
}
