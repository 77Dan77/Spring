package kz.iitu.itse1908.daniyal;

import kz.iitu.itse1908.daniyal.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@Component
@EntityScan("kz.iitu.itse1908.daniyal.*")
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableScheduling
@EnableJms
@EnableSwagger2
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class DaniyalApplication {
//    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
//        applicationContext = new AnnotationConfigApplicationContext(ScanConfig.class);
        SpringApplication.run(DaniyalApplication.class, args);
    }

}
