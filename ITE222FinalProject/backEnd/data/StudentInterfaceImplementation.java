package ITE222FinalProject.backEnd.data;

import ITE222FinalProject.backEnd.StudentInterface.Student_Interface;
import ITE222FinalProject.backEnd.classes.Course;

import java.util.List;

public class StudentInterfaceImplementation implements Student_Interface {

    Course course = new Course();

    private List<Course> listOfCoursesComing = course.getListOfCourses();

    @Override
    public void getCourses() {
        for (Course course: listOfCoursesComing){
            System.out.println("\n---------- Course ----------" +"\n"+
                    "name='" + course.getName() + "'\n" +
                    "courseCode='" + course.getCourseCode() + "'\n" +
                    "instructor='" + course.getInstructor() + "'\n" +
                    "hours=" + course.getHours() +"'\n"+
                    "topic='" + course.getTopic() + "'\n" +
                    "publicationDate='" + course.getPublicationDate() + "'\n" +
                    "bought='" + course.isBought() + "'\n" +
                    "weeklyHours='" + course.getWeeklyHours() + "'\n" +
                    "price='" + course.getPrice() + "'\n" +
                    "----------------------------");
        }
    }

    @Override
    public Course getCourse(String courseCode) {
        boolean courseFound = false;
        Course courseFoundAndReturn = new Course();
        for (Course c: listOfCoursesComing){
            if (c.getCourseCode().equals(courseCode)){
                courseFoundAndReturn = new Course(c.getName(), c.getCourseCode(), c.getInstructor(), c.getHours(), c.getTopic(), c.getPublicationDate(), c.isBought(), c.getWeeklyHours(), c.getPrice());
                courseFound = true;
                break;
            }
        }
        if (!courseFound){
            System.err.print("NO course with the code!");
            System.out.print(courseCode);
        }
        return courseFoundAndReturn;
    }

    @Override
    public void courseEnrolled(int studentID, Course course) {

    }

    @Override
    public void deleteCourse(String courseCode, String delete) {

    }

}
