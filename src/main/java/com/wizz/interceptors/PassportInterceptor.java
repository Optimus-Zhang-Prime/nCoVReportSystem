package com.wizz.interceptors;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//判断用户是否登录
@Component
public class PassportInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies = request.getCookies();
        String aname;
        try {//try-catch用于处理response.getOutputStream必检异常
            response.setContentType("text/html;charset=utf-8");
            ServletOutputStream out = response.getOutputStream();
            if (cookies==null)//没有cookie
            {
                out.print("1007");
                return false;
            }
            for (Cookie cookie : cookies) {//遍历cookie
                if (cookie.getName().equals("name")) {
                    aname = cookie.getValue();
                    //此处if-else可化简
                    if (aname == null) {
                        out.print("1007");
                        return false;//拦截
                    } else {
                        out.print("1000");
                        return true;
                    }
                }
            }
            out.print("1007");
            return false;//没找到name的cookie
        } catch (Exception e4) {
            System.out.println("getOutputStream报错");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
