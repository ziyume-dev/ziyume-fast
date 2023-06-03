package dev.heming.fast.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.fast.entity.User;
import dev.heming.fast.param.user.PageListParam;
import dev.heming.fast.param.user.UserAddParam;
import dev.heming.fast.param.user.UserUpdateParam;

import java.util.List;
import java.util.Map;

/**
 * @Description 用户 Service
 * @Author Bess Croft
 * @Date 2023/5/25 19:50
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return Token 相关参数
     */
    SaTokenInfo login(String username, String password);

    /**
     * 获取已登录用户信息
     * @return 组装的用户信息
     */
    Map<String, Object> getUserInfo();

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户实体
     */
    User getUser(String username);

    /**
     * 获取用户
     * @param id 用户 id
     * @return 用户实体
     */
    User getUserById(Long id);

    /**
     * 分页查询
     * @param param 分页参数
     * @return 分页结果
     */
    List<User> pageList(PageListParam param);

    /**
     * 新增用户
     * @param param 用户新增参数
     */
    void addUser(UserAddParam param);

    /**
     * 更新用户
     * @param param 用户更新参数
     */
    void updateUser(UserUpdateParam param);

    /**
     * 删除用户
     * @param userId 用户 id
     */
    void deleteUser(Long userId);

}
