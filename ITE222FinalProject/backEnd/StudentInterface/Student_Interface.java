package ITE222FinalProject.backEnd.StudentInterface;

import ITE222FinalProject.backEnd.classes.Course;

public interface Student_Interface {

    void getCourses();
    Course getCourse(String courseCode);
    void courseEnrolled(int studentID, Course course);
    void deleteCourse(String courseCode, String delete);

}
