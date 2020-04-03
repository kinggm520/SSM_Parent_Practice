package cn.kinggm520.dao;

import cn.kinggm520.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-26 17:18
 */
public interface UserMapper {


    //    用户登录
    User login(User user);

    //    查询所有
    List<User> findAll() throws Exception;

    //    删除用户
    void delUser(Long userId) throws Exception;

    //    删除用户角色关系
    void delUserRoleRel(Long userId) throws Exception;

    //    添加用户
    Long save(User user) throws Exception;

    //    添加用户角色关系
    void saveUserRoleRel(@Param("userId") Long userId, @Param("roleId") Long roleId) throws Exception;


}

