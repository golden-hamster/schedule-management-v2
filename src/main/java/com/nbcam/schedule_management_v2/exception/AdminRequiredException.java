package com.nbcam.schedule_management_v2.exception;

public class AdminRequiredException extends RuntimeException {
    private static final String MESSAGE = "권한이 없습니다.";

    public AdminRequiredException() {super (MESSAGE);}

    public AdminRequiredException(String message) {super(message);}
}
