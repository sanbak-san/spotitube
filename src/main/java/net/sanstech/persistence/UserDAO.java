package net.sanstech.persistence;

import net.sanstech.dto.UserDTO;

public interface UserDAO {
    UserDTO getUser(String username, String password);
}
