package ictgradschool.project.repository;

import ictgradschool.project.entity.Comment;
import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CommentDAO {

    public List<Comment> getCommentsForArticle(int targetArticleId) throws IOException, SQLException {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, content, date_created, author_id, article_id FROM comment WHERE article_id = ?;")) {
                statement.setInt(1, targetArticleId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Comment> comments = new LinkedList<>();
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String content = resultSet.getString(2);
                        //LocalDateTime dateCreated = resultSet.getDate(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        LocalDateTime dateCreated = resultSet.getTimestamp(3).toLocalDateTime();
                        int authorId = resultSet.getInt(4);
                        int articleId = resultSet.getInt(5);
                        Comment comment = new Comment(authorId, articleId, id, content, dateCreated);
                        comments.add(comment);
                    }
                    return comments;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        List<Comment> comments = new CommentDAO().getCommentsForArticle(1);
        for (Comment comment : comments)
        System.out.println(comment.toString());
    }

}
