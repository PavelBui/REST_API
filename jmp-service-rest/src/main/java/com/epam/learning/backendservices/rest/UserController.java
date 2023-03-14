package com.epam.learning.backendservices.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto);

    @PutMapping
    EntityModel<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<String> deleteUser(@PathVariable Long id);

    @GetMapping("/{id}")
    EntityModel<UserResponseDto> getUser(@PathVariable Long id);

    @GetMapping
    List<EntityModel<UserResponseDto>> getAllUser();

}
