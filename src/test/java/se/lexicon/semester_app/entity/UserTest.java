package se.lexicon.semester_app.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("Test");
        user.setType(UserType.USER);
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_User() {
        Assertions.assertEquals("Test", user.getEmail());
        Assertions.assertEquals(UserType.USER, user.getType());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        User user = new User();
        user.setEmail("Test");
        user.setType(UserType.USER);
        Assertions.assertTrue(user.equals(user));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        User user = new User();
        user.setEmail("Test");
        user.setType(UserType.USER);
        Assertions.assertEquals(user.hashCode(), user.hashCode());

    }
}
