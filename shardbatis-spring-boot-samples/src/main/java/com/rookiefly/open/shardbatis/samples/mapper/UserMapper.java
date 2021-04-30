package com.rookiefly.open.shardbatis.samples.mapper;

import com.rookiefly.open.shardbatis.samples.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    List<User> findById(Integer id);

}
