package com.se.sample.config;
import com.se.sample.beans.MyBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ProjectConfig {

    @Bean
    MyBeans myBeans(){
        return  new MyBeans();
    }
}
