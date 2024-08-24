import java.util.Scanner;

public class LoginMenu{
    private static Scanner sc;

    //LoginMenu Constructor
    public LoginMenu() {
        this.sc = new Scanner(System.in);
    }

    //Method to get user input 
    public static int getChoice(){
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
    

    //Method to to get the User Choice to get Login/Signup as Student or Course Provider
    public void getHomeMenu(){
        System.out.println("Login/SignUp Menu");
        System.out.println("------------");
        System.out.println("1. Login as Course Provider");
        System.out.println("2. SignUp as Course Provider");
        System.out.println("3. Login as Student");
        System.out.println("4. Exit");

        System.out.print("Enter the corresponding number to select the option : ");
        
        switch (getChoice()) {
            case 1:
                break;
            case 2:
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
}