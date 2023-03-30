package com.yogesh.electronicStore.controller;

import com.yogesh.electronicStore.myConfig.AppConstant;
import com.yogesh.electronicStore.response.ApiResponse;
import com.yogesh.electronicStore.payloads.UserDto;
import com.yogesh.electronicStore.response.UserPageableResponse;
import com.yogesh.electronicStore.service.FileService;
import com.yogesh.electronicStore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        logger.info("Initiating request for create a user");

        UserDto saveUser1 = this.userService.saveUser(userDto);

        logger.info("Completion request for create a user");

        return new ResponseEntity<>(saveUser1, HttpStatus.CREATED);

    }
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId ){

        logger.info("Initiating request for update a user");

        UserDto updateUser = this.userService.updateUser(userDto, userId);

        logger.info("Completion request for update a user");

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId){

        logger.info("Initiating request for get a user");

        UserDto getUser = this.userService.getUser(userId);

        logger.info("Completion request for get a user");

        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<UserPageableResponse<UserDto>> getAllUser
            (@RequestParam(value ="pageNumber", defaultValue =AppConstant.PAGE_NUMBER, required = false)int pageNumber,
             @RequestParam(value ="pageSize", defaultValue =AppConstant.PAGE_NUMBER, required = false)int pageSize,
             @RequestParam(value="sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
             @RequestParam(value="sortDir",defaultValue = AppConstant.SORT_DIR,required=false)String sortDir){

        logger.info("Initiating request for get all user");

        UserPageableResponse<UserDto> getAllUser = this.userService.getAllUser(pageSize, pageNumber, sortBy, sortDir);

        logger.info("Completion request for get all user");

        return new ResponseEntity<>(getAllUser, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){

        logger.info("Initiating request for get user by email");

        UserDto getUserByEmail = this.userService.getUserByEmail(email);

        logger.info("Initiating request for get user by email");

        return new ResponseEntity<>(getUserByEmail, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId ){

        logger.info("Initiating request for delete user by userId");

        this.userService.deleteUser(userId);

        logger.info("Completion request for delete user by userId");

        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.USER_DELETE, true), HttpStatus.OK);
    }
    @GetMapping("/search/{keyword}")
    public  ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword){

        List<UserDto> searchUser = this.userService.searchUser(keyword);

        return new ResponseEntity<>(searchUser, HttpStatus.OK);
    }

    @PostMapping("/user/image/upload/{userId}")
    public ResponseEntity<UserDto>uploadPostImage(@RequestParam("image") MultipartFile image,
                                                  @PathVariable Long userId) throws IOException {

        logger.info("Initiating requst for uploading image by using postId");

        String filename = this.fileService.uploadImage(path, image);

        UserDto userDto = this.userService.getUser(userId);

        userDto.setImageName(filename);

        UserDto updatePost = this.userService.updateUser(userDto, userId);

        logger.info("Completion step for uploading image by using postId");

        return new ResponseEntity<UserDto>(updatePost, HttpStatus.OK);
    }

    // method to serve files

    @GetMapping(value = "/user/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
            throws IOException {

        logger.info("Initiating request for get image by using imageName");

        InputStream resource = this.fileService.getResource(path, imageName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        //logger.info("Completion step for get image by using imageName");

        StreamUtils.copy(resource, response.getOutputStream());
    }

}
