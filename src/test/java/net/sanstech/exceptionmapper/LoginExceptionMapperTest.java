package net.sanstech.exceptionmapper;

import net.sanstech.dto.ErrorDTO;
import net.sanstech.exception.SpotitubeLoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class LoginExceptionMapperTest {

    LoginExceptionMapper sut;

    @BeforeEach
    void setup(){
        sut = new LoginExceptionMapper();
    }

    @Test
    void toResponse_returnsUnauthorizedAndHasErrorDTO() {
        // Init
        String msg = "message";

        // Call
        Response result = sut.toResponse(new SpotitubeLoginException(msg));
        ErrorDTO resultError = (ErrorDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), result.getStatus());
        assertEquals(msg, resultError.getMessage());
    }
}
