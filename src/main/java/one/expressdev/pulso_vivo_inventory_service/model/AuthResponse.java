package one.expressdev.pulso_vivo_inventory_service.model;

public class AuthResponse {

    private String token;
    private String username;

    public AuthResponse() {}

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
