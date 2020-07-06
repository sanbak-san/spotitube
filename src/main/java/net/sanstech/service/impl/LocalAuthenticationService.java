package net.sanstech.service.impl;


import net.sanstech.dto.TokenDTO;
import net.sanstech.service.AuthenticationService;

import javax.enterprise.inject.Alternative;

@Alternative
public class LocalAuthenticationService implements AuthenticationService {

    @Override
    public TokenDTO login(String username, String password) {
        return new TokenDTO("1234", username);
    }
}
