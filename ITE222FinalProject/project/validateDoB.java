public class validateDoB {

    public static boolean validate(String DoB) {
        
        // Splitting the date into three components using '-' as a separator.
        String[] components = DoB.split("-");

        // Checks there are only three components
        if (components.length != 3) {
            return false;
        }
        // Assigns the variable outside of the try blocks


        int day, month, year;
        // Converts each components into string type, returning false if it can't.
        try {
            day = Integer.parseInt(components[0]);
            month = Integer.parseInt(components[1]);
            year = Integer.parseInt(components[2]);
        } catch (NumberFormatException e) {
            return false;
            // The user did not enter a valid date format
        }

        // Validates the range of each date components
        if (day < 1 || day > 31) {
            return false;
            // Checks the day component is valid
        }
        if (month < 1 || month > 12) {
            return false;
            // Checks the month component is valid
        }

        if (year < 1900 || year > 2024) {
            return false;
            // Checks the year component is valid
        }

    
        return true;
    }

    public static void main(String[] args) {
        
        // Ensure the date is in DD-MM-YYYY format or the program will return false 
        String DoB = "30-11-2020";
        System.out.println(validate(DoB));
    }
}
