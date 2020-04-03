package cn.kinggm520.service;

import cn.kinggm520.domain.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-26 17:19
 */
public interface UserService {

    //    用户登录
    User login(User user);

//    查询所有
    List<User> findAll() throws Exception;

    //    分页查询
    PageInfo<User> findByPage(int pageNum, int pageSize) throws Exception;

    //    删除用户
    void delUser(Long userId) throws Exception;

//    添加用户
    void save(User user, Long[] roleIds) throws Exception;

}
