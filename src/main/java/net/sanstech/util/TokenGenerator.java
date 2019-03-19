package net.sanstech.util;

import java.util.UUID;

public class TokenGenerator {
    public String generateToken() {
        return String.valueOf(UUID.randomUUID());
    }
}
