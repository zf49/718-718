package ictgradschool.project.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUtil {
    public static int getLastInsertedId(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();\n");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }
}
