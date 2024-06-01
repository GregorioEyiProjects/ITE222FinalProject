package ITE222FinalProject.backEnd.data.db;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

public class GetStudentInfoFromFile {

    private final HashMap<String, String> studentsListLoaded = new HashMap<>();

    public void readStudentFile() {

        try {
            String basePath = System.getProperty("user.dir");
            String relativePath = "src" + File.separator + "ITE222FinalProject" + File.separator + "backEnd" + File.separator + "data";
            String filename = "Students.txt";
            String absolutePath = basePath + File.separator + relativePath + File.separator + filename;
            File studentFile = new File(absolutePath);

            int studentCount = 1; // Counter for generating unique keys


            Scanner sc = new Scanner(studentFile);
            while (sc.hasNextLine()) {

                String studentData = sc.nextLine().trim();

                if (studentData.isEmpty()) {
                    continue;
                }

                String name = extractNameFromJson(studentData);
                String email = extractEmailFromJson(studentData);
                String password = extractPasswordFromJson(studentData);
                String dateOfBirth = extractDateOFBirthFromJson(studentData);

                // Generate a unique key for each student
                String key = "student" + studentCount;

                // Loading the student info into the HashMap with the unique key
                studentsListLoaded.put(key + "_userName", name);
                studentsListLoaded.put(key + "_password", password);
                studentsListLoaded.put(key + "_email", email);
                studentsListLoaded.put(key + "_dateOfBirth", dateOfBirth);
                studentCount++;

            }

        } catch (FileNotFoundException | ParseException | org.json.simple.parser.ParseException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    private String extractNameFromJson(String jsonString) {

        if (jsonString.isEmpty()) {
            return "Username not found";
        }

        try {
            JSONParser parser = new JSONParser();
            JSONObject studentObject = (JSONObject) parser.parse(jsonString);
            return (String) studentObject.get("userName");
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractEmailFromJson(String jsonString) throws ParseException, org.json.simple.parser.ParseException {
        if (jsonString.isEmpty()) {
            return "Email not found!";  // or handle the empty case as needed
        }
        JSONParser parser = new JSONParser();
        JSONObject studentObject = (JSONObject) parser.parse(jsonString);
        return (String) studentObject.get("email");
    }

    private String extractPasswordFromJson(String jsonString) throws ParseException, org.json.simple.parser.ParseException {
        if (jsonString.isEmpty()) {
            return "password found!";  // or handle the empty case as needed
        }
        JSONParser parser = new JSONParser();
        JSONObject studentObject = (JSONObject) parser.parse(jsonString);
        return (String) studentObject.get("password");
    }

    private String extractDateOFBirthFromJson(String jsonString) throws ParseException, org.json.simple.parser.ParseException {
        if (jsonString.isEmpty()) {
            return "Date of birth not found!";  // or handle the empty case as needed
        }
        JSONParser parser = new JSONParser();
        JSONObject studentObject = (JSONObject) parser.parse(jsonString);
        return (String) studentObject.get("dateOfBirth");
    }

    public HashMap<String, String> getStudentsListLoaded() {
        return studentsListLoaded;
    }

}
