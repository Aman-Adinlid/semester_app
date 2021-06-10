package se.lexicon.semester_app.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.entity.UserType;

@SpringBootTest
public class UserDtoTest {
    UserDto userDto;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        userDto.setEmail("Test");
        userDto.setType(UserType.ADMIN);
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_UserDto() {
        Assertions.assertEquals("Test", userDto.getEmail());
        Assertions.assertEquals(UserType.ADMIN, userDto.getType());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        UserDto userDto = new UserDto();
        userDto.setEmail("Test");
        userDto.setType(UserType.ADMIN);
        Assertions.assertTrue(userDto.equals(userDto));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        UserDto userDto = new UserDto();
        userDto.setEmail("Test");
        userDto.setType(UserType.ADMIN);
        Assertions.assertEquals(userDto.hashCode(), userDto.hashCode());
    }
}
