package ITE222FinalProject.FrontEndV2.Extras;

import ITE222FinalProject.backEnd.data.db.GetStudentInfoFromFile;

import java.util.HashMap;
import java.util.Map;

public class GetStudentInfo {

    private String storedStudentEmail_, storedPassword_, storedDateOfBirth_, storedName_;

    public String getEmail(String nameComing, String passwordComing){
        GetStudentInfoFromFile gsff = new GetStudentInfoFromFile();
        gsff.readStudentFile(); // reading the file

        HashMap<String, String> studentsInfoComingFromFile = gsff.getStudentsListLoaded(); // loading the student

        for (Map.Entry<String, String> entry : studentsInfoComingFromFile.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            if (key.endsWith("_userName")) {
                storedName_ = value;
                storedStudentEmail_ = studentsInfoComingFromFile.get(key.replace("_userName", "_email"));
                storedPassword_ = studentsInfoComingFromFile.get(key.replace("_userName", "_password"));
                storedDateOfBirth_ = studentsInfoComingFromFile.get(key.replace("_userName", "_dateOfBirth"));
                //I check if they match
                if (nameComing.equals(storedName_) && passwordComing.equals(storedPassword_)) {
                 return storedStudentEmail_;
                }

            }

        }

        return  " ";//Email not found!
    }

    public String getStoredStudentEmail_() {
        return storedStudentEmail_;
    }

    public String getStoredDateOfBirth_() {
        return storedDateOfBirth_;
    }

    public String getStoredPassword_() {
        return storedPassword_;
    }

    public String getStoredName_() {
        return storedName_;
    }
}
