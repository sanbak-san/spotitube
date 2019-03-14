package net.sanstech.dto;

public class TokenDTO {
    private String token;
    private String user;

    public TokenDTO() {
        this.token = "123-456-789";
        this.user = "harry";
    }

    public TokenDTO(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public TokenDTO(UserDTO user) {
        this.token = "123-456-789";
        this.user = user.getUser();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
