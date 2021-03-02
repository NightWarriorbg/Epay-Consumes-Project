package me.nightwarrior.epayconsumes.config;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    @Value("${datasource.url}")
    private String serverUrl;
    @Value("${datasource.username}")
    private String dbUsername;
    @Value("${datasource.password}")
    private String dbPassword;
    @Value("${oracle.ucp.minPoolSize}")
    private String minPoolSize;

    @Value("${oracle.ucp.maxPoolSize}")
    private String maxPoolSize;

    @Value("${datasource.driver-class-name:oracle.jdbc.pool.OracleDataSource}")
    private String driverClassName;

    @Bean(name = "OraleUniversalConnectionPool")
    @Primary
    public DataSource getDataSource() {
        PoolDataSource pds = null;
        try {
            pds = PoolDataSourceFactory.getPoolDataSource();

            pds.setConnectionFactoryClassName(driverClassName);
            pds.setURL(serverUrl);
            pds.setUser(dbUsername);
            pds.setPassword(dbPassword);
            pds.setMinPoolSize(Integer.valueOf(minPoolSize));
            pds.setInitialPoolSize(10);
            pds.setMaxPoolSize(Integer.valueOf(maxPoolSize));

        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }

        return pds;
    }

}
