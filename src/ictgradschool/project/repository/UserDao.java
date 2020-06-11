package ictgradschool.project.repository;

import ictgradschool.project.entity.User;
import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.HashInfo;

import java.io.IOException;
import java.sql.*;

import static ictgradschool.project.repository.DaoUtil.getLastInsertedId;
import static ictgradschool.project.util.DBConnectionUtils.getConnectionFromClasspath;
import static ictgradschool.project.util.PasswordUtil.quickHash;

public class UserDao {
    public User getUserById(int id) throws IOException, SQLException {
        try (Connection connection = getConnectionFromClasspath("database.properties")) {
            return getUserById(connection, id);
        }
    }

    private User getUserById(Connection connection, int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, username, salt, password_hash \n" +
                        "FROM user\n" +
                        "WHERE id = ?;\n")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean hasNext = resultSet.next();
                return hasNext ? makeUser(resultSet) : null;
            }
        }
    }

    public User getUserByName(String username) throws IOException, SQLException {
        try (Connection connection = getConnectionFromClasspath("database.properties")) {
            return getUserByName(connection, username);
        }
    }

    private User getUserByName(Connection connection, String username) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, username, salt, password_hash \n" +
                        "FROM user\n" +
                        "WHERE username = ?;\n")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean hasNext = resultSet.next();
                return hasNext ? makeUser(resultSet) : null;
            }
        }
    }

    public User addUser(String username, String password) throws IOException, SQLException {
        HashInfo hashInfo = quickHash(password);
        try (Connection connection = getConnectionFromClasspath("database.properties")) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO user (username, salt, password_hash)\n" +
                            "values (?, ?, ?)\n"
            )) {
                statement.setString(1, username);
                statement.setString(2, hashInfo.saltBase64);
                statement.setString(3, hashInfo.hashBase64);
                statement.executeUpdate();
            }
            int id = getLastInsertedId(connection);
            return getUserById(connection, id);
        }
    }

    public void deleteUserById(int id) throws IOException, SQLException {
        try (Connection connection = getConnectionFromClasspath("database.properties")) {
            // TODO: delete articles and comments before deleting user
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }
    }

    private User makeUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String username = resultSet.getString(2);
        String salt = resultSet.getString(3);
        String passwordHash = resultSet.getString(4);
        return new User(id, username, salt, passwordHash);
    }
}
