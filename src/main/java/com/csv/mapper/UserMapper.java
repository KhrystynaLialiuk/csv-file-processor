package com.csv.mapper;

import com.csv.domain.User;
import com.csv.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User toUser(final UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setDateOfBirth(userDto.getDateOfBirth());
        if (userDto.getPhone().matches("[0-9]{9}")) {
            user.setPhone(userDto.getPhone());
        }
        return user;
    }

    public UserDto toUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getDateOfBirth(),
                user.getPhone());
    }

    public List<UserDto> toUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}
