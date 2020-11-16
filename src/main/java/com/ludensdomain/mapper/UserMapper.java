package com.ludensdomain.mapper;

import com.ludensdomain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<UserDto> getUserInfo(long id, String password);

    void insertUserInfo(UserDto userDto);
}
