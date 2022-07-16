package kz.iitu.itse1908.daniyal.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.postgresql.ds.PGPoolingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJms
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Config {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${mail}")
    String mail;

    /////
//    @Bean(initMethod = "init", destroyMethod = "close")
//    public com.atomikos.jdbc.AtomikosDataSourceBean DataSourceA() {
//        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
//        return dataSource;
//    }
//
//    @Bean(initMethod = "init", destroyMethod = "close")
//    public com.atomikos.jdbc.AtomikosDataSourceBean DataSourceB() {
//        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
//        return dataSource;
//    }

//    @Bean(initMethod = "init", destroyMethod = "close")
//    public UserTransactionManager userTransactionManager() throws SystemException {
//        UserTransactionManager userTransactionManager = new UserTransactionManager();
//        userTransactionManager.setTransactionTimeout(300);
//        userTransactionManager.setForceShutdown(true);
//        return userTransactionManager;
//    }

//    @Bean
//    public JtaTransactionManager JtatransactionManager() throws SystemException {
//        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
//        jtaTransactionManager.setTransactionManager(userTransactionManager());
//        jtaTransactionManager.setUserTransaction(userTransactionManager());
//        return jtaTransactionManager;
//    }

/////

    @Bean
    public DataSource dataSource() {
        Properties props = new Properties();

        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", "postgres");
        props.setProperty("dataSource.password", "Www.dan.com7");
        props.setProperty("dataSource.databaseName", "zhexenovLab6");
        props.put("dataSource.logWriter", new PrintWriter(System.out));

        HikariConfig config = new HikariConfig(props);
        HikariDataSource dataSource = new HikariDataSource(config);

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("org.hibernate.envers.audit_table_suffix", "_AUDIT_LOG");
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        hibernateProp.put("spring.jpa.properties.hibernate.enable_lazy_load_no_trans", true);

        return hibernateProp;
    }

    @Bean(name="entityManagerFactory")
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("kz.iitu.itse1908.daniyal");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }


//    @Bean
//    public Executor taskExecutor() {
//        return Executors.newScheduledThreadPool(10);
//    }
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(taskExecutor());
//    }

}
