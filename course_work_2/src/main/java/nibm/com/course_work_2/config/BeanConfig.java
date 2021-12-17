package nibm.com.course_work_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class BeanConfig {

    @Bean
    public RestTemplate getRestTemplate() {return  new RestTemplate();}
}
