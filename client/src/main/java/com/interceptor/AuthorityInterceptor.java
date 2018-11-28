package com.interceptor;

import com.annotation.AuthorityCheck;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *  自定义权限拦截器
 *  继承HandlerInterceptorAdapter，重写preHanle()方法
 *  preHandle在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制等处理；
 *  postHandle在业务处理器处理请求执行完成后，生成视图之前执行。
 *     后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView；
 *  afterCompletion在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。
 *    返回处理（已经渲染了页面），可以根据ex是否为null判断是否发生了异常，进行日志记录；
 **/
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    /**
     *  重写方法preHandle；
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 不拦截登陆请求
        if(request.getRequestURI().contains("login.action")){
            return true;
        }else{
            //这里的Auth是自定义的注解，
            // 通过注解在controller的方法上来实现是否拦截该请求或者不拦截该请求。
            HandlerMethod handlerMethod=(HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Map<String, Object> map = new HashMap<>();
            AuthorityCheck auth = method.getAnnotation(AuthorityCheck.class);
            //处理拦截,这里还没有具体拦截

            return true;
        }
    }
}
