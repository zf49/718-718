package ictgradschool.project.repository;

import ictgradschool.project.entity.Comment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static ictgradschool.project.testutil.DBTestUtil.resetDBData;

class CommentDaoTest {
    private CommentDao commentDao;

    @BeforeAll
    static void setUpBeforeAll() throws IOException, SQLException {
//        resetDBData();
    }

    @BeforeEach
    void setUp() {
        commentDao = new CommentDao();
    }

    @Test
    void testReplyToComment() throws IOException {
//        commentDao.insertNewComment("Reply to some comment", 1, 2);
    }

    @Test
    void testGetAllCommentsOfAnArticle() throws IOException {
        List<Comment> comments = commentDao.getCommentsByArticleId(1);
        for (Comment comment : comments) {
            System.out.println(comment);
            System.out.println("---");
        }
    }

    @Test
    void testReset() throws IOException, SQLException {
        resetDBData();
    }

    @Test
    void testAddComments() throws IOException {
        int parentId = 2;
//        Comment comment1 = commentDao.insertNewComment("Nested 1", 1, parentId);
//        commentDao.insertNewComment("Nested 1 - 1", 1, comment1.id);
//        commentDao.insertNewComment("Nested 2", 1, parentId);
//        commentDao.insertNewComment("Nested 1 - 2", 1, comment1.id);

        List<Comment> comments = commentDao.getCommentsByArticleId(1);
        comments = Comment.flatten(comments);
        for (Comment comment : comments) {
            System.out.println(comment);
            System.out.println("---");
        }
    }
}