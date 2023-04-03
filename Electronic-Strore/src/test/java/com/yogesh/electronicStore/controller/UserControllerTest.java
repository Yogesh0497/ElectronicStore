package com.yogesh.electronicStore.controller;

import com.yogesh.electronicStore.model.User;
import com.yogesh.electronicStore.payloads.UserDto;
import com.yogesh.electronicStore.response.UserPageableResponse;
import com.yogesh.electronicStore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserControllerTest.class)
class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    UserController controller = new UserController();

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getUserTest() {

//        List<UserDto> user = new ArrayList<>();
//        user.add(new UserDto (1L,"Yogesh","y@gmail.com", "Ya@1234567", "Engineer","male","abc.png"));
//        user.add(new UserDto (1L,"Mangesh","m@gmail.com", "Ma@1234567", "Doctor","male","aba.png"));

        UserDto users = new UserDto(1L,"Yogesh","y@gmail.com", "Ya@1234567", "Engineer","male","abc.png");
        long userId = 1;

        when(userService.getUser(userId)).thenReturn(users);

        ResponseEntity<UserDto> userById = userController.getUser(userId);

        long actual = userById.getBody().getUserId();

        assertEquals(actual, userId);

        assertEquals(HttpStatus.FOUND, userById.getStatusCode());

    }

    @Test
    void getAllUserTest() {

//        List<User> user = new ArrayList<>();
//        user.add(new User (1L,"Yogesh","y@gmail.com", "Ya@1234567", "Engineer","male","abc.png"));
//        user.add(new User (1L,"Mangesh","m@gmail.com", "Ma@1234567", "Doctor","male","aba.png"));

       // when(userService.getAllUser()).thenReturn((UserPageableResponse<UserDto>) user);

    }

    @Test
    void getUserByEmailTest() {

        UserDto users = new UserDto(1L,"Yogesh","y@gmail.com", "Ya@1234567", "Engineer","male","abc.png");

        String email = "y@gmail.com";
        when(userService.getUserByEmail(email)).thenReturn(users);

        ResponseEntity<UserDto> userByEmail = userController.getUserByEmail(email);

        String actual = userByEmail.getBody().getEmail();

        assertEquals(actual, email);

        assertEquals(HttpStatus.FOUND, userByEmail.getStatusCode());
    }

    @Test
    void deleteUser() {
    }

    @Test
    void searchUser() {
    }

    @Test
    void uploadPostImage() {
    }

    @Test
    void downloadImage() {
    }
}