package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TimeComplexityDb {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/TimeComplexity",
                "wilyendri", "21043600");
    }

}
