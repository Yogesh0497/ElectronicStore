package com.yogesh.electronicStore.serviceImpl;

import com.yogesh.electronicStore.model.User;
import com.yogesh.electronicStore.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserServiceImplTest.class)
class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserServiceImpl impl;

    @Test
    void saveUser() {
    }

    @Test
    void updateUser() {

    }

    @Test
    void deleteUserTest() {
    }

    @Test
    void getUserTest() {

        List<User> user = new ArrayList<>();
        user.add(new User (1L,"Yogesh","y@gmail.com", "Y@1234567", "Engineer","male","abc.png"));
        user.add(new User (2L,"Mangesh","m@gmail.com", "M@1234567", "Doctor","male","aba.png"));

        long userid = 1;

        when(userRepo.findAll()).thenReturn(user);
        long actual = impl.getUser(userid).getUserId();

        assertEquals(actual,userid);

    }

    @Test
    void getUserByEmailTest() {

        List<User> user = new ArrayList<>();
        user.add(new User (1L,"Yogesh","y@gmail.com", "Yo@1234567", "Engineer","male","abc.png"));
        user.add(new User (2L,"Mangesh","m@gmail.com", "Ma@1234567", "Doctor","male","aba.png"));

        String email = "y@gmail.com";

        when(userRepo.findAll()).thenReturn(user);
        String actual = impl.getUserByEmail(email).getEmail();

        assertEquals(actual,email);

    }

    @Test
    void getAllUserTest() {


//        List<User> user = new ArrayList<>();
//        user.add(new User (1L,"Yogesh","y@gmail.com", "Ya@1234567", "Engineer","male","abc.png"));
//        user.add(new User (1L,"Mangesh","m@gmail.com", "Ma@1234567", "Doctor","male","aba.png"));
//
//        when(userRepo.findAll()).thenReturn(user);
//        int actual = impl.getAllUser().size();
//
//        assertEquals(2, actual);
    }

    @Test
    void searchUser() {
    }
}