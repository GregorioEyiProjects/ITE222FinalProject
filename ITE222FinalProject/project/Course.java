package ITE222FinalProject.project;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private String courseCode;
    private String instructor;
    private int hours;
    private String topic;
    private String publicationDate;
    private boolean bought;
    private int weeklyHours;
    private double price;
    List<Student> enrolledStudents;

    public Course(String name, String courseCode, String instructor, int hours, String topic, String publicationDate, boolean bought, int weeklyHours, double price) {
        this.name = name;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.hours = hours;
        this.topic = topic;
        this.publicationDate = publicationDate;
        this.bought = bought;
        this.weeklyHours = weeklyHours;
        this.price = price;
        this.enrolledStudents = new ArrayList<>();
    }

    public void enroll(Student student) {
        this.enrolledStudents.add(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    @Override
    public String toString() {
        return "\n---------- Course ----------" +"\n"+
                "name='" + name + "'\n" +
                "courseCode='" + courseCode + "'\n" +
                "instructor='" + instructor + "'\n" +
                "hours=" + hours +"'\n"+
                "topic='" + topic + "'\n" +
                "publicationDate='" + publicationDate + "'\n" +
                "bought='" + bought + "'\n" +
                "weeklyHours='" + weeklyHours + "'\n" +
                "price='" + price + "'\n" +
                "enrolledStudents='" + enrolledStudents + "'\n" +
                "----------------------------";
    }

    public String toString2() {
        return "\n---------- Course ----------" +"\n"+
                "name='" + name + "'\n" +
                "courseCode='" + courseCode + "'\n" +
                "instructor='" + instructor + "'\n" +
                "hours=" + hours +"'\n"+
                "topic='" + topic + "'\n" +
                "publicationDate='" + publicationDate + "'\n" +
                "bought='" + bought + "'\n" +
                "weeklyHours='" + weeklyHours + "'\n" +
                "price='" + price + "'\n" +
                "----------------------------";
    }

    public void get_info() {
        System.out.println("Title: " + name + "\nInstructor: " + instructor + "\nDescription: " + topic +"\n");
    }
}


