package com.nbcam.schedule_management_v2.exception;

public class TokenNotFoundException extends RuntimeException{
    private static final String MESSAGE = "토큰을 찾을 수 없습니다.";

    public TokenNotFoundException() {super (MESSAGE);}

    public TokenNotFoundException(String message) {super(message);}
}
