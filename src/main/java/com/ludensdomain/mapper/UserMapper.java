package com.ludensdomain.mapper;

import com.ludensdomain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDto getUserInfo(long id, String password);

    void insertUserInfo(UserDto userDto);

    void updateUserInfo(long id, UserDto userDto);

    void deleteUser(long id, String password);
}
