package ITE222FinalProject.project;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String email;
    private List<Course> enrolledCourses;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
        this.enrolledCourses = new ArrayList<>();
    }

    public void enroll(Course course) {
        this.enrolledCourses.add(course);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }


    public void get_info() {
        System.out.println("Name: " + getName() + "\nEmail: " + getEmail());
        System.out.println("\nStudent enrolled courses:\n");
        for (Course course : enrolledCourses) {
            course.get_info();
        }
    }
}
