package Student.com;

// Import Statements.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    // Variable Declaration.
    static final private String DRIVER= "com.jdbc.cj.Driver";
    static final private String URL= "jdbc:mysql://localhost:3306/student_manage";
    static final private String USER= "root";
    static final private String PASS= "kapil";

    // Create Connection To Database.
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
