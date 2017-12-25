package com.epam.abmyotka.hr.exception;

public class WrongAttachmentException extends Exception {

    public WrongAttachmentException() {
    }

    public WrongAttachmentException(String message) {
        super(message);
    }

    public WrongAttachmentException(Throwable exception) {
        super(exception);
    }

    public WrongAttachmentException(String message, Throwable exception) {
        super(message, exception);
    }
}
