package com.yogesh.electronicStore.serviceImpl;

import com.yogesh.electronicStore.BaseTest;
import com.yogesh.electronicStore.exception.ResourceNotFoundException;
import com.yogesh.electronicStore.model.User;
import com.yogesh.electronicStore.payloads.UserDto;
import com.yogesh.electronicStore.repository.UserRepo;
import com.yogesh.electronicStore.response.UserPageableResponse;
import com.yogesh.electronicStore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends BaseTest {

    @MockBean
    private UserRepo userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    User user;

    User user1;

    List<User> users;

    UserDto userDto;

    @BeforeEach
    public void init() {
        userDto = UserDto.builder().name("Yogesh").email("yogesh@gmail.com").gender("male").password("Yogesh@123")
                .imageName("abcdef.png").about("for test").build();

        user = User.builder().name("Changesh").email("changesh96@gmail.com").gender("male").password("Changesh@123")
                .imageName("abcdef.png").about("for test").build();

        user1 = User.builder().name("Ramesh").email("ramesh@gmail.com").gender("male").password("Ramesh@123")
                .imageName("pqrs.png").about("for test").build();

        users = new ArrayList<>();
        users.add(user);
        users.add(user1);
    }

    @Test
    void saveUser() {
//        Arrange
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
//        Act
        UserDto user1 = userService.saveUser(userDto);
//        Assert
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(userDto.getName(), user1.getName());
    }


    @Test
    void updateUser() {
        Long id = 10L;
        //Arrange
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        //Act
        UserDto updateUser = userService.updateUser(userDto, id);
        //Assert
        Assertions.assertNotNull(updateUser);
        Assertions.assertEquals(userDto.getName(), updateUser.getName());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userDto, 111L));
    }

// //   If we are hard delete user then we can use code as bellow

//    @Test
//    void deleteUser() {
//        Long id = 10l;
//        //Arrange
//        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
//        //Act
//        userService.deleteUser(id);
//        //Assert
//        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
//        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(111l));
//    }


    // //   if we use soft delete then we use code as bellow
    @Test
    void deleteUser() {
        Long id = 10l;
        //Arrange
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        //Act
        userService.deleteUser(id);
        //Assert
        Assertions.assertEquals("No", user.getIsactive());
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> userService.deleteUser(111l));
    }

    @Test
    void getUser() {
        Long id = 10l;
//        Arrange
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
//        Act
        UserDto userDto1 = userService.getUser(id);
//        Assert
        Assertions.assertNotNull(userDto1);
    }

    @Test
    void getUserByEmail() {
        String mail = "cs@gmail.com";
//        Arrange
        Mockito.when(userRepository.findByEmail(mail)).thenReturn(Optional.of(user));
//        Act
        UserDto user3 = userService.getUserByEmail(mail);
//        Assert
        Assertions.assertNotNull(user3);
    }

    @Test
    void getAllUser() {
        //Arrange
        List<User>userList = Arrays.asList(user,user1);
        Page<User> page = new PageImpl<>(userList);
        Mockito.when(userRepository.findAll((Pageable)Mockito.any())).thenReturn(page);
        //Act
        UserPageableResponse<UserDto> allUser = userService.getAllUser(1,2,"name","asc");
//        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        //Assert
        Assertions.assertEquals(2, allUser.getContent().size());
    }

    @Test
    void searchUser() {
    }

}