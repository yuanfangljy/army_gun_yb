package com.ybkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = {"com.ybkj.gun.mapper"})
public class App  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
    //发布
   @Override
    protected SpringApplicationBuilder configure
            (SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
}
