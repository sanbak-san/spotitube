package net.sanstech.service.impl;

import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.UserDTO;
import net.sanstech.exception.SpotitubeLoginException;
import net.sanstech.persistence.TokenDAO;
import net.sanstech.persistence.UserDAO;
import net.sanstech.util.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    UserDAO userDAO;

    @Mock
    TokenDAO tokenDAO;

    @Mock
    TokenGenerator tokenGenerator;

    @InjectMocks
    AuthenticationServiceImpl sut = new AuthenticationServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void login_withUserNull_throwsSpotitubeLoginException() {
        // Init
        when(userDAO.getUser(anyString(), anyString())).thenReturn(null);

        // Call & Assert
        assertThrows(SpotitubeLoginException.class, () -> sut.login("", ""));
    }

    @Test
    void login_withTokenEmpty_returnsGeneratedToken() {
        // Init
        UserDTO userDTO = new UserDTO();
        when(userDAO.getUser(anyString(), anyString())).thenReturn(userDTO);
        TokenDTO tokenDTO = new TokenDTO();
        String token = "abcd";
        when(tokenGenerator.generateToken()).thenReturn(token);
        when(tokenDAO.getToken(userDTO)).thenReturn(tokenDTO);
        when(tokenDAO.insertToken(token, userDTO)).thenReturn(tokenDTO);

        // Call
        TokenDTO result = sut.login("", "");

        // Assert
        assertEquals(tokenDTO, result);
    }

    @Test
    void login_withValidToken_returnsToken() {
        // Init
        UserDTO userDTO = new UserDTO();
        when(userDAO.getUser(anyString(), anyString())).thenReturn(userDTO);
        String token = "abcd";
        String user = "harry";
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setUser(user);
        when(tokenDAO.getToken(userDTO)).thenReturn(tokenDTO);

        // Call
        TokenDTO result = sut.login("", "");

        // Assert
        assertEquals(tokenDTO, result);
    }

}
