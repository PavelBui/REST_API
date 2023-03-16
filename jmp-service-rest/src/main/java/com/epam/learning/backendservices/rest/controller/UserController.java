package com.epam.learning.backendservices.rest.controller;

import com.epam.learning.backendservices.rest.dto.UserRequestDto;
import com.epam.learning.backendservices.rest.dto.UserResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@Api(tags = "User Endpoint")
public interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create User")
    EntityModel<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto);

    @PutMapping
    @ApiOperation("Update User")
    EntityModel<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete User by id")
    @ApiImplicitParam(name = "id", value = "User id", paramType = "path", required = true)
    ResponseEntity<String> deleteUser(@PathVariable Long id);

    @GetMapping("/{id}")
    @ApiOperation("Get User by id")
    @ApiImplicitParam(name = "id", value = "User id", paramType = "path", required = true)
    EntityModel<UserResponseDto> getUser(@PathVariable Long id);

    @GetMapping
    @ApiOperation("Get list of all Users")
    List<EntityModel<UserResponseDto>> getAllUser();

}
