package com.jpym.ymfrontgm.configurer;

import com.jpym.ymfrontgm.interceptor.ApiInterceptor;
import com.jpym.ymfrontgm.interceptor.ThirdDataInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebInterceptorConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public ThirdDataInterceptor thirdDataInterceptor() {
        return new ThirdDataInterceptor();
    }
    
    @Bean
    public ApiInterceptor apiInterceptor() {
        return new ApiInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        // api签名拦截
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/**");

        //三方数据查询长度拦截
        registry.addInterceptor(thirdDataInterceptor()).addPathPatterns("/searchFundBonus", "/fundManagerInformation",
                "/searchFundCompany", "/searchFundDocument", "/searchFundHistoryNav", "/searchFundIncomeFigure", "/searchFundPerformance",
                "/searchFundStrategy", "/queryFundNotice");
        super.addInterceptors(registry);

    }
}
