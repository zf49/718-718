package ictgradschool.project.controller.exception;

public class PasswordsDontMatchException extends Exception {
    public PasswordsDontMatchException() {
        super("Passwords don't match.");
    }
}
