package bookStore.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    private Long id;

    @NotNull(message = "Username cannot be null.")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    private String username;

    @NotNull(message = "Password cannot be null.")
    @Pattern(regexp = "[^A-Za-z0-9]", message = "Password must contain at least one special character.")
    @Pattern(regexp = ".*[0-9].*", message = "Password must contain at least one digit.")
    @Size(min = 8, message = "Password too short.")
    private String password;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
