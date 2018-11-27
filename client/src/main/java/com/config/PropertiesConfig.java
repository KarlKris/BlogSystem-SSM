package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取application.properties属性文件的类
 **/
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.user}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("spring.datasource.driver")
    private String driver;

    @Value("${mybatis.type.alias.package}")
    private String mybatisTypeAliasPackage;

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getMybatisTypeAliasPackage() {
        return mybatisTypeAliasPackage;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return user;
    }

    public PropertiesConfig(){
        System.out.println("实例化PropertiesConfig类.........");
    }
}
