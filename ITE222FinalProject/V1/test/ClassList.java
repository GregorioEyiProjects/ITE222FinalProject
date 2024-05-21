package ITE222FinalProject.test;

import java.util.ArrayList;
import java.util.List;

public class ClassList {

    public static void main(String[] args) {
        List<ITClass> availableClasses = createAvailableClasses();

        System.out.println("Available Classes in the IT Program:");
        for (ITClass itClass : availableClasses) {
            System.out.println(itClass);
            System.out.println("-------------------------------------------------------"
                    + "-------------------------------------------------------------------");
        }
    }

    public static List<ITClass> createAvailableClasses() {
        List<ITClass> classes = new ArrayList<>();

        ITClass class1 = new ITClass("Information Technology Fundamentals", "ITE101", "Lanka", 4);
        ITClass class2 = new ITClass("Discrete Mathematics", "ITE102", "Amin", 4);
        ITClass class3 = new ITClass("Intro to Data Structures and Algorithms", "ITE103", "Amin", 4);
        ITClass class4 = new ITClass("Computer Organization", "ITE104", "Amin", 4);
        ITClass class5 = new ITClass("Social and Professional Issues in IT", "ITE210", "Nay", 4);
        ITClass class6 = new ITClass("System Analysis, Design, and Implementation", "ITE321", "Amin", 4);
        ITClass class7 = new ITClass("Web Development I", "ITE120", "Amin", 4);
        ITClass class8 = new ITClass("Intro to Data Science", "ITE224", "Farouz", 4);
        ITClass class9 = new ITClass("Intro to Internet of Things", "ITE233", "Atikom", 4);
        ITClass class1a = new ITClass("Programming I", "ITE221", "Farouz", 4);
        ITClass class1b = new ITClass("System Administration and Maintenance", "ITE231", "Amin", 4);
        ITClass class1c = new ITClass("Operating Systems", "ITE240", "Lanka", 4);
        ITClass class1d = new ITClass("IT Planning and Project Management", "ITE479", "Nay", 4);
        ITClass class1e = new ITClass("Into to 3D Modeling and Virtual Reality", "ITE331", "Atikom", 4);
        ITClass class1f = new ITClass("Database Management Systems I", "ITE441", "Amin", 4);
        ITClass class1g = new ITClass("Database Management Systems II", "ITE442", "Amin", 4);
        ITClass class1h = new ITClass("Programming II", "ITE222", "Farouz", 4);
        ITClass class1i = new ITClass("Human Computer Interaction", "ITE254", "Nay", 4);
        ITClass class1j = new ITClass("Information Assurance and Security I", "ITE420", "Farouz", 4);
        ITClass class1k = new ITClass("Network I", "ITE475", "Amin", 4);

        // Set prerequisite requirements for classes
        class7.addPrerequisite("ITE221");
        class8.addPrerequisite("ITE102");
        class9.addPrerequisite("ITE221");
        class1a.addPrerequisite("ITE103");
        class1g.addPrerequisite("ITE441");
        class1h.addPrerequisite("ITE221");
        class1j.addPrerequisite("ITE475");

        classes.add(class1);
        classes.add(class2);
        classes.add(class3);
        classes.add(class4);
        classes.add(class5);
        classes.add(class6);
        classes.add(class7);
        classes.add(class8);
        classes.add(class9);
        classes.add(class1a);
        classes.add(class1b);
        classes.add(class1c);
        classes.add(class1d);
        classes.add(class1e);
        classes.add(class1f);
        classes.add(class1g);
        classes.add(class1h);
        classes.add(class1i);
        classes.add(class1j);
        classes.add(class1k);

        return classes;
    }
}

class ITClass {
    private String className;
    private String classCode;
    private String instructor;
    private int creditHours;
    private List<String> prerequisites;

    public ITClass(String className, String classCode, String instructor, int creditHours) {
        this.className = className;
        this.classCode = classCode;
        this.instructor = instructor;
        this.creditHours = creditHours;
        this.prerequisites = new ArrayList<>();
    }

    public String getClassName() {
        return className;
    }

    public String getClassCode() {
        return classCode;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void addPrerequisite(String prerequisite) {
        prerequisites.add(prerequisite);
    }

    public void addPrerequisite(String... prerequisites) {
        for (String prerequisite : prerequisites) {
            this.prerequisites.add(prerequisite);
        }
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    @Override
    public String toString() {
        return "Class Name: " + className +
                ", Class Code: " + classCode +
                ", Instructor: " + instructor +
                ", Credit Hours: " + creditHours +
                ", Prerequisites: " + prerequisites;
    }
}
