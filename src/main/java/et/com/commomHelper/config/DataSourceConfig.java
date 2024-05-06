package et.com.commomHelper.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Bean(name = "oraDataSource")
    @Primary
    public DataSource getOraDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("oracle.jdbc.OracleDriver")
                .url("jdbc:oracle:thin:@localhost:1521/orcl1pdb1")
                .username("OCRM_CORE")
                .password("OCRM_CORE")
                .build();
    }

    @Bean(name = "pgSqlDataSource")
    public DataSource postgreDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver") // Класс драйвера для PostgreSQL
                .url("jdbc:postgresql://localhost:5432/postgres") // URL подключения к PostgreSQL
                .username("postgres") // Имя пользователя PostgreSQL
                .password("admin") // Пароль PostgreSQL
                .build();
    }
}
