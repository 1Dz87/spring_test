package du.spring_test.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Entity;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableWebMvc
@ComponentScan("du.spring_test")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/**").addResourceLocations("/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean(name = "dataBase")
    public Map<String, String> getDataBase() {
        Map<String, String> dataBase =  new HashMap<>();
        dataBase.put("eng", "Hello everybody!");
        dataBase.put("ru", "Всем привет!");
        return dataBase;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(hibernateSettings()).build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("du.spring_test")) {
            String name = bd.getBeanClassName();
            try {
                Class clazz = Class.forName(name);
                metadataSources.addAnnotatedClass(clazz);
            } catch (Exception E) {
                throw new RuntimeException("Class not found");
            }
        }
        Metadata metadata = metadataSources.buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }

    @Bean(name = "session")
    public Session getSession() {
        SessionFactory factory = sessionFactory();
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        return session;
    }

    private Map<String, String> hibernateSettings() {
        Map<String, String> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/parkings");
        settings.put("hibernate.connection.username", "postgres");
        settings.put("hibernate.connection.password", "qwerty");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");
        return settings;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/parkings");
        dataSource.setUsername("postgres");
        dataSource.setPassword("qwerty");
        return dataSource;
    }
}
