public class validate {
    public static boolean validateEmail(String email) {
    
        // Check there is ONE @ symbol somewhere in the string
        if (email.indexOf('@') != email.lastIndexOf('@') ||email.indexOf('@') == -1) {
            return false;
            // if both occurences are not the same, multiple @ within the string: returns false
        }

        // Splits the email using the index of the @ and ensures neither of them are empty
        String local = email.substring(0, email.indexOf("@"));
        String domain = email.substring(email.indexOf("@")+1);
        if (local.isBlank() || domain.isBlank()) {
            return false;
        }

        // Check the dot is somewhere in the domain part of the email
        int dot_position = domain.indexOf(".");
        if (dot_position == -1 || dot_position == 0 || dot_position == domain.length() -1) {
            // Ensures a dot is present, is right after the @ and that it is not at the end.
            return false;
        }

        // Checks there aren't consecutive dots
        if (email.contains("..")) {
            return false;
        }

        // Checks there is one character before the dot and two or more after the dot.
        if ((domain.substring(0, dot_position)).isEmpty() || (domain.substring(dot_position+1)).length() < 2) {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {

        // Accepts user email
        String user_email = "Marco@gmail.cm";

        System.out.println(validateEmail(user_email));

    }
}
