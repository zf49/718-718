package ictgradschool.project.repository;

import ictgradschool.project.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ictgradschool.project.repository.DBTestUtil.resetDBData;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private UserDao userDao;

    @BeforeAll
    static void setUpBeforeAll() throws IOException, SQLException {
        resetDBData();
    }

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

    @Test
    void testGetUserById() throws IOException, SQLException {
        User user = userDao.getUserById(1);
        assertNotNull(user);
    }

    @Test
    void testGetNonExistedUserById() throws IOException, SQLException {
        User user = userDao.getUserById(0);
        assertNull(user);
    }

    @Test
    void testAddUser() throws IOException, SQLException {
        User user = userDao.addUser("George", "George123");
        assertNotNull(user);
        resetDBData();
    }
}