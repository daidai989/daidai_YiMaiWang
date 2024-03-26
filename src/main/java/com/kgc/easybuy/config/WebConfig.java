package com.kgc.easybuy.config;

import com.kgc.easybuy.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录接口和注册接口不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/userLogin","/doRegister","/Login.html","/")
                .excludePathPatterns("/CategoryList.html","/forgetPwd.html","/Login.html","/Member_Address.html","/Member_Address_Update.html")
                .excludePathPatterns("/Product.html","/product_add.html","/product_update.html","/User_detail.html")
                .addPathPatterns("/**")
                .excludePathPatterns("/checkUserExist","/getUser","/logout","/sendEmailCode","/updatePwd")
                .excludePathPatterns("/css/**","/register.html","/images/**","/js/**")
                .excludePathPatterns("/getProductList","/getProductByCategoryId","/getHotProduct","/getImage","/getProductById","/getproducts","/upload","/getProductByLogin","/getRecommendProduct")
                .excludePathPatterns("/getAllCats")
                .excludePathPatterns("/createOrder","/alipayNotify")
                .excludePathPatterns("/getFirstCategory","/getSecondCategory","/getThirdCategory","/getSecondAllCategories","/getAllCategory")
                .excludePathPatterns("/getNewsListByPage","/getNewsList");




    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //项目中的所有接口都支持跨域
        registry.addMapping("/**")
                //所有地址都可以访问，也可以配置具体地址
                .allowedOrigins("*")
                //允许的请求方式
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                //是否支持跨域Cookie
                .allowCredentials(true)
                // 跨域允许时间
                .maxAge(3600);
    }

}
