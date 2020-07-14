package net.sanstech.exceptionmapper;

import net.sanstech.dto.ErrorDTO;
import net.sanstech.exception.SpotitubeTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenExceptionMapperTest {

    TokenExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new TokenExceptionMapper();
    }

    @Test
    void toResponse() {
        // Init
        String msg = "Token is invalid";

        // Call
        Response result = sut.toResponse(new SpotitubeTokenException());
        ErrorDTO resultError = (ErrorDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), result.getStatus());
        assertEquals(msg, resultError.getMessage());
    }
}
