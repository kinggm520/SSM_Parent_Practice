package cn.kinggm520.dao;

import cn.kinggm520.domain.Role;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-26 11:34
 */
public interface RoleMapper {

//    查询所有角色
    List<Role> findAll() throws Exception;

//    添加角色
    void save(Role role) throws Exception;

    //删除角色
    void delRole(Long roleId) throws Exception;

//    查询当前用户的所有角色
    List<Role> findRoleByUserId(Long userId) throws Exception;
}

