package cn.kinggm520.service.impl;

import cn.kinggm520.dao.RoleMapper;
import cn.kinggm520.domain.Role;
import cn.kinggm520.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-26 11:33
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    //    查询所有角色
    public List<Role> findAll() throws Exception {
        return roleMapper.findAll();
    }


    //    添加角色
    public void save(Role role) throws Exception {
         roleMapper.save(role);
    }

//    删除角色
    public void delRole(Long roleId) throws Exception {
        roleMapper.delRole(roleId);
    }

    public List<Role> findRoleByUserId(Long userId) throws Exception {
        return null;
    }


}
