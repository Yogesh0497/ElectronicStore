package com.yogesh.electronicStore.service;

import com.yogesh.electronicStore.payloads.UserDto;
import com.yogesh.electronicStore.payloads.UserPageableResponse;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    void deleteUser(Long userId);

    UserDto getUser(Long userId);

    UserDto getUserByEmail(String email);

    UserPageableResponse<UserDto> getAllUser(int pageSize, int pageNumber, String sortBy, String sortDir);

    List<UserDto> searchUser(String keyword);
}
