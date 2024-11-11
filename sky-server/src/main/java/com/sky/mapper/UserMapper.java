package com.sky.mapper;


import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 插入
     * @param user
     */
    void insert(User user);

    /**
     * 根据openid查询用户
     * @param openId
     */
    @Select("select * from user where openid = #{openid}" )
    User getByOpenId(String openId);
}
