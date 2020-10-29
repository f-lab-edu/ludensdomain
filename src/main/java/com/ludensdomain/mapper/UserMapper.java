package com.ludensdomain.mapper;

import com.ludensdomain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDto getUserInfo();
    void insertUserInfo(UserDto userDto);
}
