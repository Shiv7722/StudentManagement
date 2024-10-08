import java.io.Console;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.sql.SQLException;

public class LoginMenu {
    private static Scanner sc;
    private DBManager dbManager;

    // LoginMenu Constructor
    public LoginMenu() {
        this.sc = new Scanner(System.in);
        this.dbManager = new DBManager();
    }

    // Method to get user input
    public static int getChoice() {
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    // Method to to get the User Choice to get Login/Signup as Student or Course
    // Provider
    public void getHomeMenu() {
        System.out.println("Login/SignUp Menu");
        System.out.println("------------");
        System.out.println("1. Login as Course Provider");
        System.out.println("2. SignUp as Course Provider");
        System.out.println("------------");
        System.out.println("3. Login as Student");
        System.out.println("4. Exit");

        System.out.print("Enter the corresponding number to select the option : ");

        switch (getChoice()) {
            case 1:
            loginAsCProvider();
                break;
            case 2:
                signUpAsCProvider();
                break;
            case 3:

                break;
            case 4:
                break;
            default:
                System.out.println("Please enter the valid option : ");
                getHomeMenu();
                break;
        }
    }

    // Method to Get Signup details
    public void signUpAsCProvider() {
        String cProviderName;
        String password;
        Long contact;

        System.out.println("To SignUp as Course Provider : \nPlease enter your name : ");
        cProviderName = sc.nextLine();

        System.out.println("Please enter your Contact : ");
        // Looping until get the valid contact input
        while (true) {
            contact = sc.nextLong();
            if (isValidCon(contact)) {
                break;
            } else {
                System.out.println("Please enter valid contact\nRe-enter your contact : ");
            }
        }

        // Console object to call the readPassword method of Console class
        Console console = System.console();

        // looping until get the valid password
        while (true) {
            String passCheck;
            char[] passCheckArray = console.readPassword("Create your password: ");
            passCheck = new String(passCheckArray);
            char[] passArray = console.readPassword("Re-enter the password: ");
            password = new String(passArray);

            // checks if both the entered password matches
            if (passCheck.equals(password)) {
                // check if password entered is valid
                if (isValidPassword(password)) {
                    try {
                        dbManager.addCourseProvider(cProviderName, contact, password);

                    } catch (SQLException e) {
                        System.out.println("Error while Creating account to database: " + e.getMessage());
                    }

                    break;

                } else {
                    System.out.println("Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one digit.");
                }
            } else {
                System.out.println("Password doesn't match");
            }
        }

    }

    // Method to get Course Provider login
    public void loginAsCProvider() {
        String password;
        long contact;
        int userCheck;
        System.out.println("Please enter your Contact : ");
        // Looping until get the valid contact input
        while (true) {
            contact = sc.nextLong();
            if (isValidCon(contact)) {
                break;
            } else {
                System.out.println("Please enter valid contact\nRe-enter your contact : ");
            }
        }

        Console console = System.console();
        // Looping until get valid password
        while (true) {
            char[] passArray = console.readPassword("Enter your password : ");
            password = new String(passArray);

            try {
                userCheck = dbManager.checkCourseProvider(contact, password);
                if (userCheck == 1) {
                    break;
                } else if (userCheck == 2) {
                    System.out.println("Incorrect Password !!\nPlease try again");
                }else if(userCheck==3){
                    break;
                }

            } catch (SQLException e) {
                System.out.println("Error while fetching the data" + e.getMessage());
            }
        }
        // Checking if the contact doesn't match with the database 
        if (userCheck == 3) {
            System.out.println("Incorrect Contact number ");
            loginAsCProvider();
        }
        System.out.println("Login successfull");

    }

    private static boolean isValidCon(Long contact) {
        String stringCon = String.valueOf(contact);
        String conPattern = "^[6-9][0-9]{9}$";
        return Pattern.matches(conPattern, stringCon);
    }

    // Method to validate the password
    private static boolean isValidPassword(String password) {
        String passPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        return Pattern.matches(passPattern, password);
    }

    public static void main(String[] args) {
        LoginMenu lm = new LoginMenu();
        lm.getHomeMenu();
    }
}