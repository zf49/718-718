package ictgradschool.project.repository;

import ictgradschool.project.entity.Auth;
import ictgradschool.project.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static ictgradschool.project.repository.DaoUtil.getLastInsertedId;
import static ictgradschool.project.util.DBConnectionUtils.getConnectionFromClasspath;

public class AuthDao {
    public Auth addAuth(User user) throws SQLException, IOException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusMinutes(5);
        String token = "TODO";
        try (Connection connection = getConnectionFromClasspath("database.properties")) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO auth (token, expiry_date, user_id)\n" +
                            "values (?, ?, ?)\n"
            )) {
                statement.setString(1, token);
//                statement.setDate(2, expiryDate);
                statement.setInt(3, user.getId());
                statement.executeUpdate();
            }
            int id = getLastInsertedId(connection);
            return getAuthById(connection, id);
        }
    }

    private Auth getAuthById(Connection connection, int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, token, expiry_date, user_id\n" +
                        "FROM user\n" +
                        "WHERE id = ?;\n")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean hasNext = resultSet.next();
                return hasNext ? makeAuth(resultSet) : null;
            }
        }
    }

    private Auth makeAuth(ResultSet resultSet) {
        throw new UnsupportedOperationException();
    }
}
