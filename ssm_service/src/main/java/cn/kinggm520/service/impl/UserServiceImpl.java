package cn.kinggm520.service.impl;

import cn.kinggm520.dao.RoleMapper;
import cn.kinggm520.dao.UserMapper;
import cn.kinggm520.domain.Role;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-26 17:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    //    用户登录
    public User login(User user) {
        return userMapper.login(user);
    }

    //    查询用户列表
    public List<User> findAll() throws Exception {
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            List<Role> roleList = roleMapper.findRoleByUserId(user.getId());
            user.setRoles(roleList);
        }
        return userList;
    }

//    分页
    public PageInfo<User> findByPage(int pageNum, int pageSize) throws Exception {

        // 分页助手
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = findAll();
        // 分页信息对象
        PageInfo<User> pb = new PageInfo<User>(users);
        return pb;
    }


    //    删除用户
    public void delUser(Long userId) throws Exception {
//       先删除用户角色关系
        userMapper.delUserRoleRel(userId);
//        再删除用户
        userMapper.delUser(userId);
    }


    //    添加用户
    public void save(User user, Long[] roleIds) throws Exception {
        userMapper.save(user);
        for (Long roleId : roleIds) {
            userMapper.saveUserRoleRel(user.getId(), roleId);
        }
    }


}
