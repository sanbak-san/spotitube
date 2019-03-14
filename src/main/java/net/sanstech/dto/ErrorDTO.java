package net.sanstech.dto;

public class ErrorDTO extends RuntimeException {
    public ErrorDTO(String message) {
        super(message);
    }
}
