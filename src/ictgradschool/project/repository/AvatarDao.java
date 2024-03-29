package ictgradschool.project.repository;

import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AvatarDao {
    public int insertAvatar(String name) {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO avatar (name, is_predefined) VALUES (?, FALSE)")) {
                statement.setString(1, name);
                statement.executeUpdate();
            }
            return DaoUtil.getLastInsertedId(connection);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<String> getPredefinedAvatarNames() {
        try (Connection connection = DBConnectionUtils.getConnection()) {
            try (Statement statement1 = connection.createStatement()) {
                ResultSet resultSet = statement1.executeQuery(
                        "SELECT name FROM avatar WHERE is_predefined=TRUE");
                List<String> names = new LinkedList<>();
                while (resultSet.next()) {
                    names.add(resultSet.getString(1));
                }
                return names;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
