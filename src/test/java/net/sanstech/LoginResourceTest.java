//package net.sanstech;
//
//
//import net.sanstech.dto.TokenDTO;
//import net.sanstech.dto.UserDTO;
//import net.sanstech.resources.LoginResource;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.ws.rs.core.Response;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class LoginResourceTest {
//
//    private LoginResource sut;
//
//    @BeforeEach
//    void setup() {
//        sut = new LoginResource();
//    }
//
//    @Test
//    public void loginUser() {
//        UserDTO userDTO = new UserDTO("Test", "User");
//
////        Mockito.when
//        Response actualResult = sut.loginUser((userDTO));
//        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
//        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
//        assertEquals("Test", actualToken.getUser());
//        assertEquals("123-456-789", actualToken.getToken());
//    }
//
//    @Test
//    public void loginUserFail() {
//        UserDTO userDTO = new UserDTO("Test", "User");
//        Response actualResult = sut.loginUser((userDTO));
//        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());
//        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
//        assertEquals("Test", actualToken.getUser());
//        assertEquals("123-456-789", actualToken.getToken());
//    }
//}