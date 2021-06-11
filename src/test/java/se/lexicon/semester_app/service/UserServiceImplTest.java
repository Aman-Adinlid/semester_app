package se.lexicon.semester_app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.entity.UserType;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserServiceImplTest {
    UserService userService;
    UserDto userDto;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    public void setUp() throws RecordNotFoundException {
        userDto = new UserDto();
        userDto.setEmail("Test");
        userDto.setType(UserType.ADMIN);
        userService.create(userDto);
    }

    @Test
    @DisplayName("Test1")
    public void test1_findById() throws RecordNotFoundException {
        UserDto createdUserDto = userService.create(userDto);
        userService.findById(createdUserDto.getId());
        assertEquals(1, userService.findById(1).getId());
    }

    @Test
    @DisplayName("Test2")
    public void test2_findByEmail() {
        assertEquals("Test", userService.findByEmail("Test").getEmail());
    }

    @Test
    @DisplayName("Test3")
    public void test3_findAll() {
        assertEquals(1, userService.findAll().get(0).getId());
    }

    @Test
    @DisplayName("Test4")
    public void test4_create() throws RecordNotFoundException {
        assertEquals("Test", userService.create(userDto).getEmail());
    }

    @Test
    @DisplayName("Test5")
    public void test5_update() throws RecordNotFoundException {
        // assertEquals("Test", userService.update(userDto).getEmail());
    }

    @Test
    @DisplayName("Test6")
    public void test7_delete() throws RecordNotFoundException {
        UserDto createdUserDto = userService.create(userDto);
        userService.delete(createdUserDto.getId());
        assertEquals(1, userService.findAll().size());
    }
}
