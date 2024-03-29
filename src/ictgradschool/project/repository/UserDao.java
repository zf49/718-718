package ictgradschool.project.repository;

import ictgradschool.project.entity.User;
import ictgradschool.project.entity.UserCredential;

import java.io.IOException;
import java.sql.*;

import static ictgradschool.project.repository.DaoUtil.getLastInsertedId;
import static ictgradschool.project.util.DBConnectionUtils.getConnection;

public class UserDao {
    public User getUserById(int id) {
        try (Connection connection = getConnection()) {
            return getUserById(connection, id);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User getUserById(Connection connection, int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT user.id AS id, username, avatar.name AS avatar_name\n" +
                        "FROM user\n" +
                        "LEFT JOIN avatar ON user.avatar_id = avatar.id\n" +
                        "WHERE user.id = ?;")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean hasNext = resultSet.next();
                return hasNext ? makeUser(resultSet) : null;
            }
        }
    }

    public UserCredential getUserCredentialByName(String username) throws IOException {
        try (Connection connection = getConnection()) {
            return getUserCredentialByName(connection, username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private UserCredential getUserCredentialByName(Connection connection, String username) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT user.id, username, salt, password_hash, avatar.name AS avatar_name\n" +
                        "FROM user\n" +
                        "LEFT JOIN avatar ON user.avatar_id = avatar.id\n" +
                        "WHERE username = ?;\n")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean hasNext = resultSet.next();
                return hasNext ? makeUserCredential(resultSet) : null;
            }
        }
    }

    private UserCredential makeUserCredential(ResultSet resultSet) throws SQLException {
        return new UserCredential(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)
        );
    }


    public User getUserByName(String username) throws IOException {
        try (Connection connection = getConnection()) {
            return getUserByName(connection, username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User getUserByName(Connection connection, String username) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT user.id, username, avatar.name AS avatar_name\n" +
                        "FROM user\n" +
                        "LEFT JOIN avatar ON user.avatar_id = avatar.id\n" +
                        "WHERE username = ?;\n")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean hasNext = resultSet.next();
                return hasNext ? makeUser(resultSet) : null;
            }
        }
    }

    public User getUserDetails(User user) throws IOException, SQLException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT detail_id, fname, lname, date_birth, descrip FROM user " +
                            "LEFT JOIN user_detail ON user.detail_id = user_detail.id WHERE user.id = ?;")) {
                statement.setInt(1, user.getId());
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        user.setDetailId(rs.getInt(1));
                        user.setFirstName(rs.getString(2));
                        user.setLastName(rs.getString(3));
                        Date date = rs.getDate(4);
                        if (date == null) {
                            user.setDateBirth(null);
                        } else {
                            user.setDateBirth(new java.util.Date(date.getTime()));
                        }
                        user.setDescription(rs.getString(5));
                    }
                }
            }
        }
        return user;
    }

    public void updateUserDetail(User user) throws IOException {
        try (Connection connection = getConnection()) {
            Integer detailId = user.getDetailId();
            if (detailId == null || detailId == 0) {
                addUserDetails(user, connection);
            } else {
                updateUserDetails(user, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateUserDetails(User user, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE user_detail SET fname = ?, lname = ?, date_birth = ?, descrip = ? WHERE id = ?;")) {
            stmt.setInt(5, user.getDetailId());
            transferUserDetailToStatement(user, stmt);
        }
    }

    public void addUserDetails(User user) throws IOException {
        try (Connection connection = getConnection()) {
            addUserDetails(user, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUserDetails(User user, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO user_detail (fname, lname, date_birth, descrip) VALUES (?,?,?,?);")) {
            transferUserDetailToStatement(user, statement);
        }
        int detailId = getLastInsertedId(connection);
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE user SET detail_id = ? WHERE id = ?")) {
            stmt.setInt(1, detailId);
            stmt.setInt(2, user.getId());
            stmt.executeUpdate();
        }
    }

    private void transferUserDetailToStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setDate(3, new Date(user.getDateBirth().getTime()));
        statement.setString(4, user.getDescription());
        statement.executeUpdate();
    }

    public User addUser(String username, String saltBase64, String hashBase64) throws IOException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO user (username, salt, password_hash)\n" +
                            "VALUES (?, ?, ?)\n"
            )) {
                statement.setString(1, username);
                statement.setString(2, saltBase64);
                statement.setString(3, hashBase64);
                statement.executeUpdate();
            }
            int id = getLastInsertedId(connection);
            return getUserById(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User changeUser(int id, String username, String saltBase64, String hashBase64) throws IOException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE user SET username = ?, salt = ?, password_hash = ? WHERE id = ?;")) {
                statement.setString(1, username);
                statement.setString(2, saltBase64);
                statement.setString(3, hashBase64);
                statement.setInt(4, id);
                statement.executeUpdate();
            }
            return getUserById(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUserById(int id) throws IOException {
        try (Connection connection = getConnection()) {
            ArticleDao articleDao = new ArticleDao();
            CommentDao commentDao = new CommentDao();
            articleDao.deleteUserAllArticle(id);
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT id FROM comment WHERE author_id = ?;")) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        commentDao.deleteCommentById(resultSet.getInt(1));
                    }
                }
            }
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?;")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User makeUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String username = resultSet.getString(2);
        String avatarName = resultSet.getString(3);
        return new User(id, username, avatarName);
    }

    public void updateAvatarId(int id, int avatarId) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE user SET avatar_id = ? WHERE id = ?")) {
                statement.setInt(1, avatarId);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
