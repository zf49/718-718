package ictgradschool.project.repository;

import ictgradschool.project.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDao();
    }

    @Test
    void testGetUserByName() throws IOException, SQLException {
        User user = userDao.getUserByName("Bret");
        assertNotNull(user);
    }

    @Test
    void testGetUserByName2() throws IOException, SQLException {
        User user = userDao.getUserByName("Null");
        assertNull(user);
    }
}