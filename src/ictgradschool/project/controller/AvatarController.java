package ictgradschool.project.controller;

import ictgradschool.project.entity.User;
import ictgradschool.project.repository.AvatarDao;
import ictgradschool.project.repository.UserDao;

import java.io.File;

public class AvatarController {
    private User user;
    private UserDao userDao;
    private AvatarDao avatarDao;

    public AvatarController(User user, UserDao userDao, AvatarDao avatarDao) {
        this.user = user;
        this.userDao = userDao;
        this.avatarDao = avatarDao;
    }

    public User updateAvatar(String name) {
        int avatarId = avatarDao.insertAvatar(name);
        userDao.updateAvatarId(user.getId(), avatarId);
        return userDao.getUserById(user.getId());
    }
}
