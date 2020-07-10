package net.sanstech.resources;

import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.UserDTO;
import net.sanstech.exception.SpotitubeLoginException;
import net.sanstech.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginResourceTest {

    @Mock
    AuthenticationService authenticationService;

    LoginResource sut;

    @BeforeEach
    void setup() {
        sut = new LoginResource(authenticationService);
    }

    @Test
    void loginUser_withValidUser_returnsOkAndToken() {
        // Init
        String username = "user";
        String password = "pass";
        UserDTO user = new UserDTO(username, password);
        String token = "aaa-bbb-ccc";
        TokenDTO tokenDTO = new TokenDTO(token, username);
        when(authenticationService.login(username, password)).thenReturn(tokenDTO);

        // Call
        Response result = sut.loginUser(user);
        TokenDTO resultToken = (TokenDTO) result.getEntity();

        // Assert
        verify(authenticationService, times(1)).login(username, password);
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(tokenDTO, resultToken);
    }

    @Test
    void loginUser_withSpotitubeLoginExceptionThrown_returnsUnauthorizedAndError() {
        // Init
        String username = "user";
        String password = "pass";
        UserDTO user = new UserDTO(username, password);
        when(authenticationService.login(username, password)).thenThrow(new SpotitubeLoginException(""));

        // Call
        assertThrows(SpotitubeLoginException.class, () -> sut.loginUser(user));
    }
}
