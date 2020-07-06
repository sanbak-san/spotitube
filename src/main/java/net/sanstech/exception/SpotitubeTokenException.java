package net.sanstech.exception;

public class SpotitubeTokenException extends RuntimeException {
    public SpotitubeTokenException() {
        super("Token is invalid");
    }
}
