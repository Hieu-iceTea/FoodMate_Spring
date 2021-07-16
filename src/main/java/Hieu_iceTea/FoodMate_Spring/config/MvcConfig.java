package Hieu_iceTea.FoodMate_Spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login").setViewName("front/account/login");
        //registry.addViewController("/access-denied").setViewName("front/account/access-denied");
    }

}