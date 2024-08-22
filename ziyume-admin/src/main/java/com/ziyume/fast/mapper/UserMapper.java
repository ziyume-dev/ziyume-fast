package com.ziyume.fast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ziyume.fast.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 用户 Mapper
 * @Author Bess Croft
 * @Date 2023/5/25 17:57
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 查询用户列表
     * @param username 用户名
     * @param name 昵称
     * @param telephone 手机
     * @param email 邮箱
     * @return 用户列表
     */
    List<User> selectUserList(@Param("username") String username,
                              @Param("name") String name,
                              @Param("telephone") String telephone,
                              @Param("email") String email);

}
