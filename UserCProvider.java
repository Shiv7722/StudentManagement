import java.util.Scanner;
import java.sql.SQLException;

public class UserCProvider {
    private static Scanner sc = LoginMenu.sc;
    private CProvider cProvider;
    private DBManager dbManager;

    //UserCProvider Constrctor (Copy Constructor)
    public UserCProvider (CProvider cProvider){
        this.cProvider=cProvider;
        this.dbManager = new DBManager();
    }
    
    public void getCProviderMenu(){
        System.out.println("Course Provider Menu");
        System.out.println("---------");

        System.out.println("1. Get Student Details");
        System.out.println("2. Add Student");
        System.out.println("3. Remove Student");
        System.out.println("4. Exit");

        // Method to print the CProvider menu and run methods according to user choice
        System.out.print("Enter the corresponding number to select the option : ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                break;
            case 2:
            addStudent();
                break;
            case 3:
                break;
            case 4:
                break;
            default:
            System.out.println("Please enter the valid option : ");
            getCProviderMenu();
                break;
        }
    }

    public void addStudent() {
        String stName;
        int stRoll;
        long stCon;
        
        System.out.println("\nEnter student name: ");
        stName = sc.nextLine(); 
       
        // Looping until get the valid roll number input
        System.out.print("Enter student Roll Nuumber : ");
        while (true) {
            stRoll = sc.nextInt();
            sc.nextLine();
            if(LoginMenu.isValidRoll(stRoll)){
                break;
            }else{
                System.out.println("Student Roll Number can only contains four digits");
            }
        }

        System.out.print("Enter student contact number : ");
        while (true) {
            stCon = sc.nextLong();
            sc.nextLine();
            if(LoginMenu.isValidCon(stCon)){
               break;
            }else{
                System.out.println("Enter valid contact");
            }
        }
        
        //Created a Student object from user input to pass into the addStudentInfo method
        Student student = new Student(stName,stRoll,stCon);
        try {
            int result = dbManager.addStudent(student,cProvider.getCPID());
            if(result==1){
                System.out.println("Student added successfuly");
            }else{
                System.out.println("Error while inserting data");
                addStudent();
            }
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
        
    }
}
