package du.spring_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.HashMap;
import java.util.Map;

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
}
