package extensions;

import io.qameta.allure.Step;

public class User {
    public String name;
    public String email;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    @Step("Проверка эквивалентности данных")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User other = (User) o;
        return name.equals(other.name) && email.equals(other.email);
    }
}
