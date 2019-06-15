package com.example.experiment;

//import com.example.experiment.interceptor.AdminInterceptor;
import com.example.experiment.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
/*    @Autowired
    private AdminInterceptor adminInterceptor;

 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/admin/adduser")
                .excludePathPatterns("/api/admin/updateuser")
                .excludePathPatterns("/api/admin/selectuser")
                .excludePathPatterns("/api/admin/deleteuser")
        .excludePathPatterns("/api/admin/addexam")
                .excludePathPatterns("/api/admin/selectexam")
                .excludePathPatterns("/api/admin/selectname");
//        registry.addInterceptor(adminInterceptor)
//                .addPathPatterns("/api/admin");
    }
}
