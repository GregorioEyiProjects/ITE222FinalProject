package ITE222FinalProject.backEnd.SignIn;

import ITE222FinalProject.V1.Student;
import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInImplementation implements SignIn{

    private HashMap<String, String> studentListComing = new HashMap<>();
    @Override
    public boolean studentAuth(String userName, String password) {

        GetStudentInfoFromFile students = new GetStudentInfoFromFile();
        students.readStudentFile();

        //Loading the student list coming from the file
        studentListComing = students.getStudentsListLoaded();
        System.out.println(studentListComing);

        for (Map.Entry<String, String> entry : studentListComing.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            // Extract the student's username and password from the key-value pair
            if (key.endsWith("_userName")) {
                String storedUserName = value;
                String storedPassword = studentListComing.get(key.replace("_userName", "_password"));

                // Compare the stored username and password with the entered values.
                if (storedUserName.equals(userName) && storedPassword.equals(password)) {
                    return true;
                }
            }

        }

        //Otherwise, return false if the values are not correct.
        return false;
    }

}
