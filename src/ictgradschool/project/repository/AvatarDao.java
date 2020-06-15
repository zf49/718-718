package ictgradschool.project.repository;

import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;

public class AvatarDao {
    public int insertAvatar(String name) {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("database.properties")) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO avatar (name, is_predefined) VALUES (?, false)")) {
                statement.setString(1, name);
                statement.executeUpdate();
            }
            return DaoUtil.getLastInsertedId(connection);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
