package ITE222FinalProject.backEnd.classes;

import java.util.ArrayList;
import java.util.List;

public class StudentCourses extends Student{

    private List<Course> courses;

    public StudentCourses(String username, String dateOfBirth, String email, String password) {
        super(username, dateOfBirth, email, password);
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
