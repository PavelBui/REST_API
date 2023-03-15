package com.epam.learning.backendservices.rest.converter;

import com.epam.learning.backendservices.rest.dto.UserRequestDto;
import com.epam.learning.backendservices.rest.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserRequestDtoToUserConvertor {

    public User convert(UserRequestDto userRequestDto) {
        User user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        user.setBirthday(LocalDate.parse(userRequestDto.getBirthday()));
        return user;
    }
}
