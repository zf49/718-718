package ictgradschool.project.repository;

import ictgradschool.project.entity.User;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User getUserByName(String username) throws IOException, SQLException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("database.properties")) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, username, salt, password_hash \n" +
                            "FROM user\n" +
                            "WHERE username = ?;\n")) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean hasNext = resultSet.next();
                    return hasNext ? createUser(resultSet) : null;
                }
            }
        }
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String username = resultSet.getString(2);
        String salt = resultSet.getString(3);
        String passwordHash = resultSet.getString(4);
        return new User(id, username, salt, passwordHash);
    }
}
