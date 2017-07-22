package com.xcjy.web.mapper;

import com.xcjy.web.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {

    int insert(User record);

    void updateLoginMessage(@Param("username") String username,
                            @Param("loginTime") Date loginTime,
                            @Param("loginIp") String loginIp,
                            @Param("updateTime") Date updateTime);

    User getByUsername(@Param("username") String username);

    User getByPhone(@Param("phone") String phone);

}