package com.epam.learning.backendservices.rest.controller.impl;

import com.epam.learning.backendservices.rest.controller.UserController;
import com.epam.learning.backendservices.rest.converter.UserRequestDtoToUser;
import com.epam.learning.backendservices.rest.converter.UserToUserResponseDto;
import com.epam.learning.backendservices.rest.dto.UserRequestDto;
import com.epam.learning.backendservices.rest.dto.UserResponseDto;
import com.epam.learning.backendservices.rest.model.User;
import com.epam.learning.backendservices.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserToUserResponseDto userToUserResponseDto;
    @Autowired
    private UserRequestDtoToUser userRequestDtoToUser;

    public EntityModel<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.createUser(userRequestDtoToUser.convert(userRequestDto));
        return toModel(userToUserResponseDto.convert(user));
    }

    public EntityModel<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.updateUser(userRequestDtoToUser.convert(userRequestDto));
        return toModel(userToUserResponseDto.convert(user));
    }

    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    public EntityModel<UserResponseDto> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return toModel(userToUserResponseDto.convert(user));
    }

    public List<EntityModel<UserResponseDto>> getAllUser() {
        return userService.getAllUser()
                .stream()
                .map(user -> userToUserResponseDto.convert(user))
                .map(UserControllerImpl::toModel)
                .collect(Collectors.toList());
    }

    private static EntityModel<UserResponseDto> toModel(UserResponseDto response) {
        return EntityModel.of(response,
                linkTo(methodOn(UserControllerImpl.class).getUser(response.getId())).withSelfRel(),
                linkTo(methodOn(UserControllerImpl.class).getAllUser()).withRel("users"),
                linkTo(methodOn(UserControllerImpl.class).deleteUser(response.getId())).withRel("delete"),
                linkTo(methodOn(UserControllerImpl.class).createUser(new UserRequestDto())).withRel("create"),
                linkTo(methodOn(UserControllerImpl.class).updateUser(new UserRequestDto())).withRel("update")
        );
    }
}
