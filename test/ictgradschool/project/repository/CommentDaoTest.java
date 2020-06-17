package ictgradschool.project.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ictgradschool.project.testutil.DBTestUtil.resetDBData;
import static org.junit.jupiter.api.Assertions.*;

class CommentDaoTest {
    private CommentDao commentDao;

    @BeforeAll
    static void setUpBeforeAll() throws IOException, SQLException {
        resetDBData();
    }

    @BeforeEach
    void setUp() {
        commentDao = new CommentDao();
    }

    @Test
    void testReplyToComment() throws IOException {
        commentDao.insertCommentToComment2("Reply to some comment", 1, 2);
    }
}