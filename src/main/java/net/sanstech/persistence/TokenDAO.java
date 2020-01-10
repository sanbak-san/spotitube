package net.sanstech.persistence;

import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.UserDTO;

public interface TokenDAO {

    TokenDTO getToken(UserDTO user);

    TokenDTO insertToken(String token, UserDTO user);
}
