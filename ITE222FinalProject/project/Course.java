package ITE222FinalProject.project;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String title;
    private String courseCode;
    private String instructor;
    private String description;
    private int credits;
    List<Student> enrolledStudents;

    public Course(String title, String courseCode, String instructor, String description, int credits) {
        this.title = title;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.description = description;
        this.credits = credits;
        this.enrolledStudents = new ArrayList<>();
    }

    public void enroll(Student student) {
        this.enrolledStudents.add(student);
    }

    public void get_info() {
        System.out.println("Title: " + title + "\nInstructor: " + instructor + "\nDescription: " + description+"\n");
    }
}


