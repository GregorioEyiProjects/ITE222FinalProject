package ITE222FinalProject.backEnd.data.db;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StudentFileModification  {//implements SignUp

    public void addStudentToTheFile(){//String userName, String email, String password, String dateOfBirth

        try {

            String basePath = System.getProperty("user.dir");
            String relativePath = "src" + File.separator + "ITE222FinalProject" + File.separator + "backEnd" + File.separator + "data";
            String filename = "Students.txt";
            String absolutePath = basePath + File.separator + relativePath + File.separator + filename;

            FileWriter fw = new FileWriter(absolutePath, true);

            JSONObject studentObject = new JSONObject();
            studentObject.put("userName", "Gregorio");
            studentObject.put("email", "gregorioGmail.com");
            studentObject.put("password", "Password");
            studentObject.put("dateOfBirth", "11/03/2000");

            String jsonString = studentObject.toJSONString();

            fw.write(System.lineSeparator() + jsonString);
            System.out.println("Successfully wrote student information to JSON file.");

            fw.close();
            System.out.println("Successfully wrote to the file.");


        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

/*
    @Override
    public void addStudent(String userName, String email, String password, String dateOfBirth) {
        addStudentToTheFile(userName, email, password, dateOfBirth);
    }*/
}
