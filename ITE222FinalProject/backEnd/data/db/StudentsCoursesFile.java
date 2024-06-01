package ITE222FinalProject.backEnd.data.db;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

public class StudentsCoursesFile {

    private final HashMap<String, String> studentsEmailAndCoursesLoaded = new HashMap<>();

    //Create the file
    public void StudentFileCreation(){

        try{

            String basePath = System.getProperty("user.dir");
            String relativePath = "src" + File.separator + "ITE222FinalProject" + File.separator + "backEnd" + File.separator + "data";
            String filename = "StudentsCourses.txt";
            String absolutePath = basePath + File.separator + relativePath + File.separator + filename;
            File studentCoursesFile = new File(absolutePath);

            if (studentCoursesFile.createNewFile()) {
                System.out.println("Student file created: " + studentCoursesFile.getName());
                //writeCourseIntoStudentCourseFile();
            } else {
                System.out.println("Student file already exists.");
                //writeCourseIntoStudentCourseFile();
            }

        }catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //Write into the file
    public void writeCourseIntoStudentCourseFile(String email, String password){

        try{

            String basePath = System.getProperty("user.dir");
            String relativePath = "src" + File.separator + "ITE222FinalProject" + File.separator + "backEnd" + File.separator + "data";
            String filename = "StudentsCourses.txt";
            String absolutePath = basePath + File.separator + relativePath + File.separator + filename;

            FileWriter fw = new FileWriter(absolutePath, true);

            JSONObject studentCourseObject = new JSONObject();

            studentCourseObject.put("gmail", email);
            studentCourseObject.put("courseCode", password);

            String jsonString = studentCourseObject.toJSONString();

            fw.write(System.lineSeparator() + jsonString);
            //System.out.println("Successfully wrote student email and courseCode to JSON file.");

            fw.close();
            //System.out.println("Successfully wrote student email and courseCode to the file.");

        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //Get info from the file
    public void readStudentCourseFile(){

        try{

            String basePath = System.getProperty("user.dir");
            String relativePath = "src" + File.separator + "ITE222FinalProject" + File.separator + "backEnd" + File.separator + "data";
            String filename = "StudentsCourses.txt";
            String absolutePath = basePath + File.separator + relativePath + File.separator + filename;
            File studentFile = new File(absolutePath);

            int studentCount = 1; // Counter for generating unique keys

            Scanner sc = new Scanner(studentFile);
            while (sc.hasNextLine()) {

                String studentData = sc.nextLine().trim();

                if (studentData.isEmpty()) {
                    continue;
                }

                String gmail = extractGmailFromJson(studentData);
                String courseCode = extractCourseCodeJson(studentData);

                String key = "student" + studentCount;
                studentsEmailAndCoursesLoaded.put(key + "_gmail", gmail);
                studentsEmailAndCoursesLoaded.put(key + "_courseCode", courseCode);
                studentCount++;

            }

        }catch (FileNotFoundException | ParseException | org.json.simple.parser.ParseException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    //Extra methods that allow me to retrieve data from the file
    private String extractGmailFromJson(String studentData) throws ParseException, org.json.simple.parser.ParseException{

        if (studentData.isEmpty()) {
            return "User gmail not found";
        }
        JSONParser parser = new JSONParser();
        JSONObject studentObject = (JSONObject) parser.parse(studentData);
        return (String) studentObject.get("gmail");

    }

    private String extractCourseCodeJson(String studentData) throws ParseException, org.json.simple.parser.ParseException{

        if (studentData.isEmpty()){
            return "Course code not found!";
        }
        JSONParser parser = new JSONParser();
        JSONObject studentObject = (JSONObject) parser.parse(studentData);
        return (String) studentObject.get("courseCode");

    }

    public HashMap<String, String> getStudentsEmailAndCoursesLoaded() {
        return studentsEmailAndCoursesLoaded;
    }



}
