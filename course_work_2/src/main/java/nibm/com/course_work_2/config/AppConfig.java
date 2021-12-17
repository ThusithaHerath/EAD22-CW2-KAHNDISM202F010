package nibm.com.course_work_2.config;

import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
@Component
@PropertySource("classpath:application-${spring.profiles.active}-db.properties")
public class AppConfig {

    private String jdbcUrl;
    private String user;
    private String password;
    private String maxPoolSize;
    private String minIdlePooleSize;
    long idleTimeout;

    public AppConfig(@Value("${application.cam-datasource.url}") String jdbcUrl,
                     @Value("${application.cam-datasource.user}") String user,
                     @Value("${application.cam-datasource.password}") String password,
                     @Value("${application.cam-datasource.maxPoolSize}") String maxPoolSize,
                     @Value("${application.cam-datasource.minIdlePoolSize}") String minIdlePooleSize,
                     @Value("${application.cam-datasource.idleTimeout}") long idleTimeout) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
        this.maxPoolSize = maxPoolSize;
        this.minIdlePooleSize = minIdlePooleSize;
        this.idleTimeout = idleTimeout;
    }

    /**
     * Configure Logger
     */
    @Primary
    @Scope(SCOPE_PROTOTYPE)
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    /**
     * Configure HikariDataSource
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource cgDomDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(Integer.parseInt(maxPoolSize));
        config.setMinimumIdle(Integer.parseInt(minIdlePooleSize));
        config.setIdleTimeout(idleTimeout);
        return new com.zaxxer.hikari.HikariDataSource(config);
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert() {
        return new SimpleJdbcInsert(cgDomDataSource());
    }

    /**
     * Configure NamedParameterJdbcTemplate
     */
    @Bean
    @Primary
    public NamedParameterJdbcTemplate cgDomNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(cgDomDataSource());
    }

    /**
     * Configure JdbcTemplate
     */
    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate() {

        return new JdbcTemplate(cgDomDataSource());
    }


    @Bean
    @Primary
    public SimpleJdbcCall simpleJdbcCall(){
        return new SimpleJdbcCall(cgDomDataSource());
    }
}

