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
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("oracle.jdbc.OracleDriver")
                .url("jdbc:oracle:thin:@localhost:1521/orcl1pdb1")
                .username("OCRM_CORE")
                .password("OCRM_CORE")
                .build();

        return configurePool(dataSource);
    }

    @Bean(name = "pgSqlDataSource")
    public DataSource postgreDataSource() {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver") 
                .url("jdbc:postgresql://localhost:5432/postgres") 
                .username("postgres") 
                .password("admin") 
                .build();
        return configurePool(dataSource);
    }

    private DataSource configurePool(DataSource dataSource) {
        if (dataSource instanceof com.zaxxer.hikari.HikariDataSource) {
            com.zaxxer.hikari.HikariDataSource hikariDataSource = (com.zaxxer.hikari.HikariDataSource) dataSource;
            hikariDataSource.setPoolName("POOL_MUTABLE");
            hikariDataSource.setMaximumPoolSize(50);
            hikariDataSource.setMinimumIdle(5);
        }
        return dataSource;
    }

}
