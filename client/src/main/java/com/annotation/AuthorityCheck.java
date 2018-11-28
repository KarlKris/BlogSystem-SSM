package com.annotation;

import java.lang.annotation.*;

/**
 *  自定义注解 用于权限检查
 **/
//@Documented //支持JavaDoc文档注释
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE}) //表明该注解对类，接口和成员方法起作用
@Retention(RetentionPolicy.RUNTIME) //在编译以后仍然起作用
public @interface AuthorityCheck {
    boolean validate() default true;
}
