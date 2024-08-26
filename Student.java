public class Student {
    private int stID;
    private String stName;
    private int stRoll;
    private long stContact;

    // Student class constractor
    public Student(String stName, int stRoll, long stContact) {
        this.stName = stName;
        this.stRoll = stRoll;
        this.stContact = stContact;
    }
    public int getStudentID() {
        return stID;
    }

    public String getStudentName() {
        return stName;
    }

    public int getStudentRoll() {
        return stRoll;
    }

    public long getStudentCon() {
        return stContact;
    }
}
