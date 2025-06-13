package extensions;

import lombok.Getter;

@Getter
public class LoginData {
    private String username;
    private String password;
    private String usernameDescription;
    private boolean isSucceed;

    public LoginData(String username, String password, String usernameDescription, boolean isSucceed) {
        this.username = username;
        this.password = password;
        this.usernameDescription = usernameDescription;
        this.isSucceed = isSucceed;
    }
}
