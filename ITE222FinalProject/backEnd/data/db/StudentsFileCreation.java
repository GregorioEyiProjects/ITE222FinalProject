package ITE222FinalProject.backEnd.data.db;

import java.io.File;

public class StudentsFileCreation {

    public void createStudentFile(){
        try{
            String basePath = System.getProperty("user.dir");
            String relativePath = "src" + File.separator + "ITE222FinalProject" + File.separator + "backEnd" + File.separator + "data";
            String filename = "Students.txt";
            String absolutePath = basePath + File.separator + relativePath + File.separator + filename;
            File studentFile = new File(absolutePath);

            if (studentFile.createNewFile()) {
                System.out.println("Student file created: " + studentFile.getName());
            } else {
                System.out.println("Student file already exists.");
            }

        }catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
