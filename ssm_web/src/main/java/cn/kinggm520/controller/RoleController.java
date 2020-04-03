package cn.kinggm520.controller;

import cn.kinggm520.domain.Role;
import cn.kinggm520.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-26 11:29
 */

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

//       查询所有角色
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView) throws Exception {

        List<Role> roleList =  roleService.findAll();

        modelAndView.addObject("roleList",roleList);

        modelAndView.setViewName("role-list");

        return modelAndView;

    }

    //    添加角色
    @RequestMapping("/save")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:/role/list";
    }


    //删除角色
    @RequestMapping(value = "/del/{roleId}")
    public String del(@PathVariable(value = "roleId") Long roleId) throws Exception {
        roleService.delRole(roleId);
        return "redirect:/role/list";
    }


}
