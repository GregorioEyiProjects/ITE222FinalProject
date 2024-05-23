package ITE222FinalProject.backEnd.classes;

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

    private String studentEmail;

    List<Student> studentsAndCourses;

    private List<Course> listOfCourses = new ArrayList<>();

    public Course() { // default courses
        Course c1 = new Course("Information Technology Fundamentals", "ITE101", "Lanka", 40, "Introduction to Computers", "12/04/2010", false, 4, 0.0);
        Course c2 = new Course("Discrete Mathematics", "ITE102", "Amin", 36, "Logic and Problem Solving", "", false, 3, 0.0);
        Course c3 = new Course("Intro to Data Structures and Algorithms", "ITE103", "Amin", 48, "Data Organization and Manipulation", "30/05/2010", false, 4, 0.0);
        Course c4 = new Course("Computer Organization", "ITE104", "Amin", 40, "Computer Architecture and Components", "", false, 4, 0.0);
        Course c5 = new Course("Social and Professional Issues in IT", "ITE210", "Nay", 32, "Ethics and IT in Society", "04/04/2009", false, 3, 0.0);
        Course c6 = new Course("System Analysis, Design, and Implementation", "ITE321", "Amin", 52, "Software Development Lifecycle", "01/04/2010", false, 5, 0.0);
        Course c7 = new Course("Web Development I", "ITE120", "Amin", 44, "Building Web Applications", "12/04/2012", false, 4, 0.0);
        Course c8 = new Course("Intro to Data Science", "ITE224", "Farouz", 48, "Data Analysis and Visualization", "", false, 4, 120.0); // Example with a price
        Course c9 = new Course("Intro to Internet of Things", "ITE233", "Atikom", 40, "Connecting Devices to the Internet", "10/09/2010", false, 4, 0.0);
        Course c10 = new Course("Programming I", "ITE221", "Farouz", 48, "Fundamentals of Programming", "20/04/2015", false, 4, 0.0);
        Course c11 = new Course("Programming II", "ITE222", "Farouz", 48, "Advance Programming", "20/04/2015", false, 4, 0.0);

        //the rest of the courses here

        listOfCourses.add(c1);
        listOfCourses.add(c2);
        listOfCourses.add(c3);
        listOfCourses.add(c4);
        listOfCourses.add(c5);
        listOfCourses.add(c6);
        listOfCourses.add(c7);
        listOfCourses.add(c8);
        listOfCourses.add(c9);
        listOfCourses.add(c10);
        listOfCourses.add(c11);
    }

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
        this.studentsAndCourses = new ArrayList<>();
    }

    public Course(String name, String courseCode, String instructor, int hours, String topic, String publicationDate, int weeklyHours, double price) {
        this.name = name;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.hours = hours;
        this.topic = topic;
        this.publicationDate = publicationDate;
        this.weeklyHours = weeklyHours;
        this.price = price;
        this.studentsAndCourses = new ArrayList<>();
    }


    public List<Course> getListOfCourses() {
        return listOfCourses;
    }

    public void studentEnrollCourse(Student student) {
        this.studentsAndCourses.add(student);
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


    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public List<Student> getEnrolledStudents() {
        return studentsAndCourses;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.studentsAndCourses = enrolledStudents;
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
                "enrolledStudents='" + studentsAndCourses + "'\n" +
                "----------------------------";
    }

    public String getCourseInfo() {
        return "\n---------- Course Info ----------" +"\n"+
                "name= '" + name + "'\n" +
                "courseCode= '" + courseCode + "'\n" +
                "instructor= '" + instructor + "'\n" +
                "hours=" + hours +"'\n"+
                "topic='" + topic + "'\n" +
                "publicationDate='" + publicationDate + "'\n" +
                "weeklyHours='" + weeklyHours + "'\n";
    }

    public String getCourseInfo2() {
        return "\n---------- Course Info ----------" +"\n"+
                "name= '" + name + "'\n" +
                "courseCode= '" + courseCode + "'\n" +
                "instructor= '" + instructor + "'\n" +
                "hours=" + hours +"'\n"+
                "topic='" + topic + "'\n" +
                "publicationDate='" + publicationDate + "'\n" +
                "weeklyHours='" + weeklyHours + "'\n"+
                "price='" + price + "'\n";
    }

    public String getCourse(String name, String courseCode, String instructor, int hours, String topic, String publicationDate, int weeklyHours, double price) {
        return "\n---------- Course Info ----------" +"\n"+
                "name= '" + name + "'\n" +
                "courseCode= '" + courseCode + "'\n" +
                "instructor= '" + instructor + "'\n" +
                "hours=" + hours +"'\n"+
                "topic='" + topic + "'\n" +
                "publicationDate='" + publicationDate + "'\n" +
                "weeklyHours='" + weeklyHours + "'\n"+
                "price='" + price + "'\n";
    }

    public void get_info() {
        System.out.println("Title: " + name + "\nInstructor: " + instructor + "\nDescription: " + topic +"\n");
    }
}


