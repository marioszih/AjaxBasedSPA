package com.example.userManager.model;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserModelTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        Integer id = 1;
        String name = "Marios";
        String surname = "Zich";
        String email = "marioszich@gmail.com";

        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getSurname()).isEqualTo(surname);
        assertThat(user.getEmail()).isEqualTo(email);
    }

}
