package com.epam.learning.backendservices.rest.controllerImpl;

import com.epam.learning.backendservices.rest.controller.UserController;
import com.epam.learning.backendservices.rest.dto.UserRequestDto;
import com.epam.learning.backendservices.rest.dto.UserResponseDto;
import com.epam.learning.backendservices.rest.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    public EntityModel<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {

    }

    public EntityModel<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto) {

    }

    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

    }

    public EntityModel<UserResponseDto> getUser(@PathVariable Long id) {

    }

    public List<EntityModel<UserResponseDto>> getAllUser() {

    }
}
