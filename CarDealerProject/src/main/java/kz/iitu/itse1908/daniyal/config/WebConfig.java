package kz.iitu.itse1908.daniyal.config;

import kz.iitu.itse1908.daniyal.TransformData.CustomerFormatter;
import kz.iitu.itse1908.daniyal.TransformData.StrToCustomerConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        CustomerFormatter customerFormatter = new CustomerFormatter();
//        registry.addConverter(new StrToCustomerConverter());
//        registry.addFormatter(customerFormatter);
//    }

}