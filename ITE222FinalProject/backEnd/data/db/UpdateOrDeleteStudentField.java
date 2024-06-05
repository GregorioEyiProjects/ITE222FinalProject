
package ITE222FinalProject.backEnd.data.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class UpdateOrDeleteStudentField {

    private GetStudentInfoFromFile gsif = new GetStudentInfoFromFile();
    private HashMap<String, String> studentsListLoadedComing = new HashMap<>();

    public void updatingStudentFile(String studentEmail, String newStudentUserName, String newStudentPassword) throws IOException {
        gsif.readStudentFile();//reading the student file first and load it into a HashMap
        studentsListLoadedComing = gsif.getStudentsListLoaded();
        updateStudentField(studentsListLoadedComing, studentEmail, newStudentUserName, newStudentPassword);
    }

    private void updateStudentField(HashMap<String, String> studentsListLoadedComing, String studentEmail, String newStudentUserName, String newStudentPassword) throws IOException {

        for (Map.Entry<String, String> entry: studentsListLoadedComing.entrySet()){

            String key = entry.getKey();
            String keyValue = entry.getValue();

            if (key.endsWith("_userName")){
                String storedEmail = studentsListLoadedComing.get(key.replace("_userName", "_email"));
                String storedDateOfBirth = studentsListLoadedComing.get(key.replace("_userName", "_dateOfBirth"));
                if (storedEmail.equals(studentEmail)){

                    //Get the new line to be written
                    String updatedLine =
                            "{\"userName\":\"" + newStudentUserName
                            + "\", \"email\":\"" + studentEmail
                            + "\", \"password\":\"" + newStudentPassword
                            + "\", \"dateOfBirth\":\"" + storedDateOfBirth + "\"}";


                    studentsListLoadedComing.put(key, updatedLine);

                    //Getting the location  file
                    String basePath = System.getProperty("user.dir");
                    String relativePath = "src" + File.separator + "ITE222FinalProject" + File.separator + "backEnd" + File.separator + "data";
                    String filename = "Students.txt";
                    String absolutePath = basePath + File.separator + relativePath + File.separator + filename;

                    //Reading the file
                    String fileContents = Files.readString(Path.of(absolutePath));

                    // Split the file contents into individual lines
                    String[] lines = fileContents.split("\\r?\\n");

                    // Create a StringBuilder to hold the updated contents
                    StringBuilder updatedFileContents = new StringBuilder();

                    // Iterate over the lines and update the specific line
                    for (String line : lines) {
                        if (line.contains("\"email\":\"" + studentEmail + "\"")) {
                            updatedFileContents.append(updatedLine).append("\n"); // Append the updated line
                        } else {
                            updatedFileContents.append(line).append("\n"); // Append the original line
                        }
                    }

                    Files.write(Path.of(absolutePath), updatedFileContents.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                    //System.out.println("File updated successfully!");

                }
            }

        }

    }

    public boolean deleteStudentCourse(String studentEmailComing, String courseCodeToDelete) throws IOException {

        //StudentsCoursesFile scf = StudentsCoursesFile.getInstance();
        StudentsCoursesFile scf = new StudentsCoursesFile();
        scf.readStudentCourseFile();

        boolean theCodeIsNotIside = false;

        HashMap<String, String> studentsCoursesLoadedComing = scf.getStudentsEmailAndCoursesLoaded();

        String basePath = System.getProperty("user.dir");
        String relativePath = "src" + File.separator + "ITE222FinalProject" + File.separator + "backEnd" + File.separator + "data";
        String filename = "StudentsCourses.txt";
        String absolutePath = basePath + File.separator + relativePath + File.separator + filename;

        // Read the file
        String fileContents = Files.readString(Path.of(absolutePath));

        // Split the file contents into individual lines
        String[] lines = fileContents.split("\\r?\\n");

        // Create a StringBuilder to hold the updated contents
        StringBuilder updatedFileContents = new StringBuilder();

        // Iterate over the lines and exclude the line to be deleted
        for (String line : lines) {
            if (!(line.contains("\"gmail\":\"" + studentEmailComing + "\"") && line.contains("\"courseCode\":\"" + courseCodeToDelete + "\""))) {
                updatedFileContents.append(line).append("\n");

            } else {
                theCodeIsNotIside = true;
                //System.out.println("Line to be deleted: " + line);
            }
        }

        if (theCodeIsNotIside){
            //System.out.println("Updated file contents: " + updatedFileContents);
            Files.write(Path.of(absolutePath), updatedFileContents.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            //System.out.println("Course deleted successfully!");
        }

        return theCodeIsNotIside;
    }

}
