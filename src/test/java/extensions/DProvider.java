package extensions;

import org.testng.annotations.DataProvider;

public class DProvider {

    @DataProvider(name = "testLoginData")
    public LoginData[] loadLoginData() {
        return new LoginData[] {
                new LoginData("angular", "password", "desc", true),
                new LoginData("wrong", "wrong", "wrong" , false)
        };
    }
}
