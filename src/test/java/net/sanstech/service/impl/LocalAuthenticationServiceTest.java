package net.sanstech.service.impl;

import net.sanstech.dto.TokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalAuthenticationServiceTest {

    LocalAuthenticationService sut;

    @BeforeEach
    void setUp() {
        sut = new LocalAuthenticationService();
    }

    @Test
    void login() {
        // Call
        TokenDTO result = sut.login("harry", "potter");

        // Assert
        assertAll(
                () -> assertEquals("1234", result.getToken()),
                () -> assertEquals("harry", result.getUser())
        );
    }
}
