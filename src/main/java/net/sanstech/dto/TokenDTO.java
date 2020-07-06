package net.sanstech.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    private String token;
    private String user;

    public TokenDTO() {
    }

    public TokenDTO(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public TokenDTO(UserDTO user) {
        this.token = "123-456-789";
        this.user = user.getUser();
    }
}
