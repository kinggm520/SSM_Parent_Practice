package cn.kinggm520.interceptor;

import cn.kinggm520.domain.User;
import cn.kinggm520.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-26 17:12
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 访问静态资源不拦截
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }

        HttpSession session = request.getSession();

        //  如果勾选的记住密码 就把cookie里的用户信息设置到session
        Cookie[] cookies = request.getCookies();
        String username;
        String password;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user_cookie".equals(cookie.getName())) {
                    username = cookie.getValue().split("&")[0];
                    password = cookie.getValue().split("&")[1];
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    session.setAttribute("user",userService.login(user));
                }
            }
        }



        User  user = (User) session.getAttribute("user");
        if (user == null) {
//            用户未登录 重定向到登录页面
            System.out.println(request.getContextPath());

            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }



        //        登录了 放行
        return true;
    }


}
