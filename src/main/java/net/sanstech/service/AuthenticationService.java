package net.sanstech.service;

import net.sanstech.dto.TokenDTO;

public interface AuthenticationService {
    TokenDTO login(String username, String password);
}