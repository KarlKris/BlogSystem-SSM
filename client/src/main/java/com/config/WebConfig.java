package com.config;

import com.interceptor.AuthorityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 *  对应Spring-Mvc-xml文件
 **/
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

    /**
     *  配置静态资源
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        System.out.println("11111111111111111111111111111111111111111");
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/*");
    }

    /**
     *  配置HTML视图第1步：配置模板解析器
     **/
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    /**
     *  配置HTML视图第2步：配置模板引擎
     **/
    @Bean
    public TemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    /**
     *  配置HTML视图第3步：配置视图解析器
     **/
    @Bean
    public ViewResolver viewResolver(TemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setContentType("text/html; charset=utf-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    /**
     *  添加拦截器
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //在这里注册拦截器
        registry.addInterceptor(new AuthorityInterceptor());
    }
}
