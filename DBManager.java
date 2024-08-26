import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class DBManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/1cproject";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private Connection con;
    private CProvider cProvider;

    // DBManager Constructor
    public DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error while connecting to database: " + e.getMessage());
        } catch (ClassNotFoundException e2) {
            System.out.print("TError while connecting to database: " + e2.getMessage());
        }
    }

    //Method to add student to the database
    public int addStudent(Student student,int cProviderID) throws SQLException{
        PreparedStatement pStatement = null;
        try {
            pStatement = con.prepareStatement("INSERT INTO student(studentName,stRoll,stCon,cProviderID) VALUES (?, ?, ?, ?)");
            pStatement.setString(1, student.getStudentName());
            pStatement.setInt(2, student.getStudentRoll());
            pStatement.setLong(3, student.getStudentCon());
            pStatement.setInt(4, cProviderID);
            int result = pStatement.executeUpdate();
            return result;
        } finally {
            if (pStatement != null) {
                pStatement.close();
            }
        }
    }

    // Method to insert the CProvider Signup values into DB
    public void addCourseProvider(String name, long contact, String pass) throws SQLException {
        PreparedStatement pStatement = null;
        try {
            pStatement = con.prepareStatement("insert into cprovider(cProviderName,cProviderCon,cProviderPass) values(?,?,?)");
            pStatement.setString(1, name);
            pStatement.setLong(2, contact);
            pStatement.setString(3, passEncrypter(pass));
            pStatement.executeUpdate();

        } finally {
            if (pStatement != null) {
                pStatement.close();
            }
        }
    }
    
    //Method to check CProvider login info from DB
    public int checkCourseProvider(long contact, String pass) throws SQLException {
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            pStatement = con.prepareStatement("select * from cprovider where cProviderCon = ?");
            pStatement.setLong(1, contact);
            rSet = pStatement.executeQuery();
            if (rSet.next()) {
                String encPass = rSet.getString("cProviderPass");
                if (passMatch(pass, encPass)) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 3;
            }
        } finally {
            if (pStatement != null) {
                pStatement.close();
            }
            if (rSet != null) {
                rSet.close();
            }
        }
    }

    //Method to fetch CProvider Info
    public CProvider getCProvider(long contact, String pass) throws SQLException{
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            pStatement = con.prepareStatement("select * from cprovider where cProviderCon = ?");
            pStatement.setLong(1, contact);
            rSet = pStatement.executeQuery();
            if (rSet.next()) {
                String encPass = rSet.getString("cProviderPass");
                if (passMatch(pass, encPass)) {
                    return new CProvider(rSet.getInt("cProviderID"), rSet.getString("cProviderName"), rSet.getLong("cProviderCon"));
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } finally {
            if (pStatement != null) {
                pStatement.close();
            }
            if (rSet != null) {
                rSet.close();
            }
        }

    }
    // Method to get convert password to encrypted password
    public static String passEncrypter(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    // Method to match password to encrypted password
    public static boolean passMatch(String pass, String passEncrypted) {
        return BCrypt.checkpw(pass, passEncrypted);
    }
}
