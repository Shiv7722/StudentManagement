import java.util.regex.Pattern;

public class CProvider {
    private String cProviderName;
    private Long cProviderCon;
    private String cProviderPass;

    // Constractor of CProvider class
    public CProvider(String cProviderName, Long cProviderCon, String cProviderPass) {
        this.cProviderName = cProviderName;
        this.cProviderCon = cProviderCon;
        this.cProviderPass = cProviderPass;

        
    }public static boolean isValidCon(long contact) {
        String conString = String.valueOf(contact);
        String conPattern = "^[6-9][0-9]{9}$";
        return Pattern.matches(conPattern, conString);
    }

    // Method to validate the password
    public static boolean isValidPassword(String password) {
        String passPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        return Pattern.matches(passPattern, password);
    }

    public static boolean isValidRoll(int stRoll) {
        String roll = String.valueOf(stRoll);
        String passPattern = "^[0-9]{4}$";
        return Pattern.matches(passPattern, roll);
    }

    public static void main(String[] args) {
        CProvider cpro = new CProvider("Shivraj",3427877234656L , "Shiv1234");
        System.out.println(cpro.hashCode());

        System.out.println(cpro.equals(cpro));
    }
}
