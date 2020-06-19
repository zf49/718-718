package ictgradschool.project.repository;

import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ictgradschool.project.util.DBConnectionUtils.getConnection;
import static ictgradschool.project.util.DBConnectionUtils.getConnectionFromClasspath;

public class DaoUtil {
    public static int getLastInsertedId(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();\n");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }
}
