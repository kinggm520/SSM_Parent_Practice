package cn.kinggm520.controller;

import cn.kinggm520.domain.Role;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.RoleService;
import cn.kinggm520.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-26 17:17
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    //    登录
    @RequestMapping("/login")
    public String login(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response, String remember) throws ServletException, IOException {
        User currentUser = userService.login(user);
        if (currentUser != null) {
            if("remember_me".equals(remember)){
                Cookie cookie = new Cookie("user_cookie",user.getUsername()+"&"+user.getPassword());
                cookie.setPath(request.getContextPath());
                cookie.setMaxAge(24*3600*7);
                response.addCookie(cookie);
            }
            session.setAttribute("user", currentUser);
            return "redirect:/index.jsp";
        }
        request.setAttribute("user", user);
        request.setAttribute("errorMsg", "用户名或密码错误！");
        return "forward:/login.jsp";

    }


//    跳转到主页
    @RequestMapping("/main")
    public String main(){
        return "redirect:/pages/main.jsp";
    }


    //    用户列表展示
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView,
                            @RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize) throws Exception {

        // 分页助手
        PageInfo<User> pb = userService.findByPage(pageNum, pageSize);

        List<User> userList = userService.findAll();
        modelAndView.addObject("pb", pb);
        modelAndView.setViewName("user-list");
        return modelAndView;

    }

    //    删除用户
    @RequestMapping("/del/{userId}")
    public String del(@PathVariable(value = "userId") Long userId) throws Exception {
        userService.delUser(userId);
        return "redirect:/user/list";
    }

    //    异步获取角色信息
    @RequestMapping("/getRoleList")
    @ResponseBody
    public List<Role> saveUI() throws Exception {
        List<Role> roleList = roleService.findAll();
        return roleList;
    }




    //    添加用户
    @RequestMapping("/save")
    public String save(User user, Long[] roleIds) throws Exception {
        userService.save(user, roleIds);
        return "redirect:/user/list";
    }


    //    退出
    @RequestMapping("/quit")
    public String quit(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        Cookie cookie = new Cookie("user_cookie",null);
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/login.jsp";
    }

}