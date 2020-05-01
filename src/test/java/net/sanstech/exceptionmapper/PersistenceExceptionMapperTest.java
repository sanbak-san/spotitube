package net.sanstech.exceptionmapper;

import net.sanstech.dto.ErrorDTO;
import net.sanstech.exception.SpotitubeLoginException;
import net.sanstech.exception.SpotitubePersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceExceptionMapperTest {

    PersistenceExceptionMapper sut;

    @BeforeEach
    void setup() {
        sut = new PersistenceExceptionMapper();
    }

    @Test
    void toResponse_returnsInternalServerErrorAndHadErrorDTO() {
        // Init
        String msg = "Database connection error. Please try again later.";

        // Call
        Response result = sut.toResponse(new SpotitubePersistenceException(new RuntimeException()));
        ErrorDTO resultError = (ErrorDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), result.getStatus());
        assertEquals(msg, resultError.getMessage());
    }
}
