package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginData {
    private String username;
    private String password;
    private String usernameDescription;
    private boolean isSucceed;
}
