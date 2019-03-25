package net.sanstech.service;

import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.UserDTO;
import net.sanstech.persistence.TokenDAO;
import net.sanstech.persistence.UserDAO;
import net.sanstech.util.TokenGenerator;

import javax.enterprise.inject.Default;

@Default
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserDAO userDAO;
    private TokenDAO tokenDAO = new TokenDAO();

    private TokenGenerator tokenGenerator = new TokenGenerator();

    public AuthenticationServiceImpl() {
    }

    public AuthenticationServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public TokenDTO login(String username, String password) {
        UserDTO user = userDAO.getUser(username, password);
        if (user != null) {
            TokenDTO token = tokenDAO.getToken(username);
            if (token != null) {
                return token;
            } else {
                return tokenDAO.insertToken(tokenGenerator.generateToken(), username);
            }
        } else {
            throw new SpotitubeLoginException("Login failed for user " + username);
        }
    }

}