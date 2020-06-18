package ictgradschool.project.controller.exception;

public class NoSuchUsernameException extends Exception {
    public NoSuchUsernameException() {
        super("Username does not exist.");
    }
}
