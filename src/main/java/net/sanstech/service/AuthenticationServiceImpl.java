package net.sanstech.service;

import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.UserDTO;
import net.sanstech.persistence.TokenDAO;
import net.sanstech.persistence.UserDAO;
import net.sanstech.util.TokenGenerator;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private TokenDAO tokenDAO;

    private TokenGenerator tokenGenerator = new TokenGenerator();

    public AuthenticationServiceImpl() {
    }

    public AuthenticationServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public TokenDTO login(String username, String password) {
        final UserDTO user = userDAO.getUser(username, password);
        if (user != null) {
            final TokenDTO token = tokenDAO.getToken(user);
            if (token.getToken() != null && token.getUser() != null) {
                return token;
            } else {
                return tokenDAO.insertToken(tokenGenerator.generateToken(), user);
            }
        } else {
            throw new SpotitubeLoginException("Login failed for user " + username);
        }
    }
}