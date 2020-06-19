package ictgradschool.project.repository;

import ictgradschool.project.entity.User;
import ictgradschool.project.entity.UserCredential;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ictgradschool.project.testutil.DBTestUtil.resetDBData;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertNotNull(user.getAvatarName());
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
        assertNotNull(user.getAvatarName());
    }

    @Test
    void testGetNonExistedUserById() throws IOException, SQLException {
        User user = userDao.getUserById(0);
        assertNull(user);
    }

    @Test
    void testAddUser() throws IOException, SQLException {
        User user = userDao.addUser("George",
                "S4rKvEb7vOWJ5yLkX1qNuICtLX20lJMOH9vzkNnJV4E=",
                "YogwH6+EIz9lvTurXPD7wdscRQc/viTM+tkS5nyuHY9vlbHKxNlP0jMNmAzOytj6qZLqHt7rt8ND/sE+f2MTwg==");
        assertNotNull(user);
        resetDBData();
    }

    @Test
    void testGetUserCredential() throws IOException {
        UserCredential credential = userDao.getUserCredentialByName("Bret");
        assertNotNull(credential);
        System.out.println(credential);
    }
}