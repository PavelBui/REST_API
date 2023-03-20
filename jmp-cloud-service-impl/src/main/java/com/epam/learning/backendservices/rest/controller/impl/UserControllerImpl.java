package com.epam.learning.backendservices.rest.controller.impl;

import com.epam.learning.backendservices.rest.controller.UserController;
import com.epam.learning.backendservices.rest.dto.UserRequestDto;
import com.epam.learning.backendservices.rest.dto.UserResponseDto;
import com.epam.learning.backendservices.rest.exeption.UserNotFoundException;
import com.epam.learning.backendservices.rest.model.User;
import com.epam.learning.backendservices.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
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
    private Converter<User, UserResponseDto> userToUserResponseDtoConverter;
    @Autowired
    private Converter<UserRequestDto, User> userRequestDtoToUserConverter;

    public EntityModel<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.createUser(userRequestDtoToUserConverter.convert(userRequestDto));
        return toModel(userToUserResponseDtoConverter.convert(user));
    }

    public EntityModel<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.updateUser(userRequestDtoToUserConverter.convert(userRequestDto));
        return toModel(userToUserResponseDtoConverter.convert(user));
    }

    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    public EntityModel<UserResponseDto> getUser(@PathVariable Long id) {
        User user = userService.getUser(id).orElseThrow(() -> new UserNotFoundException(id));
        return toModel(userToUserResponseDtoConverter.convert(user));
    }

    public List<EntityModel<UserResponseDto>> getAllUser() {
        return userService.getAllUser()
                .stream()
                .map(user -> userToUserResponseDtoConverter.convert(user))
                .map(UserControllerImpl::toModel)
                .collect(Collectors.toList());
    }

    private static EntityModel<UserResponseDto> toModel(UserResponseDto response) {
        return EntityModel.of(response,
                linkTo(methodOn(UserControllerImpl.class).getUser(response.getId())).withSelfRel(),
                linkTo(methodOn(UserControllerImpl.class).getAllUser()).withRel("getAllUsers"),
                linkTo(methodOn(UserControllerImpl.class).deleteUser(response.getId())).withRel("deleteUser"),
                linkTo(methodOn(UserControllerImpl.class).createUser(new UserRequestDto())).withRel("createUser"),
                linkTo(methodOn(UserControllerImpl.class).updateUser(new UserRequestDto())).withRel("updateUser")
        );
    }
}
