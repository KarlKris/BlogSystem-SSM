package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Spring的配置类
 **/

@Configuration  //表明此类是配置类
@ComponentScan //扫描自定义的组件,默认会扫描该类所在的包下所有的配置类
// 使用ApplicationContext的getBeanDefinitionNames() 方法获取已经注册到容器中的 bean 的名称。
@PropertySource("classpath:application.properties")  //读取属性文件
@MapperScan("com.mapper")    //扫描Mybatis的Mapper接口
@EnableTransactionManagement  //开启事务管理
public class SpringConfig {

    /**
     * 配置阿里druid数据源
     **/
    @Bean
    public DataSource dataSource(PropertiesConfig propertiesConfig){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUsername(propertiesConfig.getUsername());
        druidDataSource.setPassword(propertiesConfig.getPassword());
        druidDataSource.setUrl(propertiesConfig.getUrl());
        druidDataSource.setDriverClassName(propertiesConfig.getDriver());
        return druidDataSource;
    }

    /**
     *  配置mybatis的SqlSessionFactoryBean
     **/
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource,PropertiesConfig propertiesConfig){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(propertiesConfig.getMybatisTypeAliasPackage());
        return sqlSessionFactoryBean;
    }

    /**
     *  配置spring的声明式事务
     **/
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
