package ITE222FinalProject.backEnd.StudentInterface;

import ITE222FinalProject.backEnd.classes.Course;

public interface Student_Interface { //I am not using this interface for now, so i might delete later

    void getCourses();
    Course getCourse(String courseCode);
    void courseEnrolled(int studentID, Course course);
    void deleteCourse(String courseCode, String delete);

}
