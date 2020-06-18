package ictgradschool.project.controller.exception;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Incorrect password.");
    }
}
