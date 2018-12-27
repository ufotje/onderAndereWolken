package client.MainApp;

import client.interceptors.FooterInterceptor;
import client.interceptors.SidebarInterceptor;
import client.interceptors.SiteStatisticsInterceptor;
import client.interceptors.VisitorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = "client")
@EntityScan()
public class OnderAndereWolken extends SpringBootServletInitializer{

    @Autowired
    private SidebarInterceptor sidebarInterceptor;
    @Autowired
    private FooterInterceptor footerInterceptor;
    @Autowired
    private VisitorInterceptor visitorInterceptor;
    @Autowired
    private SiteStatisticsInterceptor siteStatisticsInterceptor;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(OnderAndereWolken.class);
    }

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(sidebarInterceptor).excludePathPatterns("/admin/*", "/error");
                registry.addInterceptor(footerInterceptor).excludePathPatterns("/login", "/logout", "/error");
                registry.addInterceptor(visitorInterceptor).excludePathPatterns("/admin/*", "/error");
                registry.addInterceptor(siteStatisticsInterceptor);
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnderAndereWolken.class, args);
    }

}
