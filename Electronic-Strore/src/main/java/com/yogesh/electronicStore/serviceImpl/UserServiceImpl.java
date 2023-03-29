package com.yogesh.electronicStore.serviceImpl;

import com.yogesh.electronicStore.exception.ResourceNotFoundException;
import com.yogesh.electronicStore.helper.Helper;
import com.yogesh.electronicStore.model.User;
import com.yogesh.electronicStore.myConfig.AppConstant;
import com.yogesh.electronicStore.payloads.UserDto;
import com.yogesh.electronicStore.payloads.UserPageableResponse;
import com.yogesh.electronicStore.repository.UserRepo;
import com.yogesh.electronicStore.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
   @Autowired
    private UserRepo userRepo;

   @Autowired
   private ModelMapper modelMapper;
    @Override
    public UserDto saveUser(UserDto userDto) {

        logger.info("Initiating step to save user");

        User user =  this.dtoToUser(userDto);

        user.setIsactive(AppConstant.YES);

        User save = this.userRepo.save(user);

        logger.info("Completion step to save user");

        return  this.UserToDto(save);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

        logger.info("Initiating step to update user");

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setImageName(userDto.getImageName());

        User updateUser = userRepo.save(user);

        logger.info("Completion step to update user");

        return this.modelMapper.map(updateUser, UserDto.class);

    }

    @Override
    public void deleteUser(Long userId) {

        logger.info("Initiating step to delete user");

        User deleteUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));

        deleteUser.setIsactive(AppConstant.NO);

        userRepo.save(deleteUser);

        logger.info("Completion step to delete user");

    }

    @Override
    public UserDto getUser(Long userId) {

        logger.info("Initiating step to get user");

        User user =  this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));

        logger.info("Completion step to get user");

        return this.UserToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        logger.info("Initiating step to get user by email");

        User userEmail = this.userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));

        logger.info("Completion step to get user by email");

        return this.UserToDto(userEmail);
    }
    @Override
    public UserPageableResponse<UserDto> getAllUser(int pageSize, int pageNumber, String sortBy, String sortDir) {

        logger.info("Initiating step to get all user");

        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

//        Sort sort = null;
//        if (sortDir.equalsIgnoreCase("asc")) {
//            sort = Sort.by(sortBy).ascending();
//        } else {
//            sort = Sort.by(sortBy).descending();
//        }

        Pageable p = PageRequest.of(pageSize, pageNumber, sort);

        Page<User> userPage = this.userRepo.findAll(p);

        UserPageableResponse<UserDto> response = Helper.getPageableResponse(userPage,UserDto.class);

        logger.info("Completion step to get all user");

        return response;


    }

    @Override
    public List<UserDto> searchUser(String keyword) {

        logger.info("Initiating step to search user by using any keyword");

        List<User> byNameContaining = this.userRepo.findByNameContaining(keyword);

        List<UserDto> dtoList = byNameContaining.stream().map((user) -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        logger.info("Completion step to search user by using any keyword");

        return dtoList;
    }

    private User dtoToUser(UserDto userDto){

        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto UserToDto(User user){

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return  userDto;
    }
}
