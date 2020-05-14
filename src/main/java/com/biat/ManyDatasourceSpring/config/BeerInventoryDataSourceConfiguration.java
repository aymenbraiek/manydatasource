package com.biat.ManyDatasourceSpring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class BeerInventoryDataSourceConfiguration {
    @Bean(name = "beerInventoryDb")
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.beerfirst")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "BeerinvJdbcTemplate")
    public JdbcTemplate jdbcTemplateBeerInventory(@Qualifier("beerInventoryDb") DataSource datasourcebeerinventory) {
        return new JdbcTemplate(datasourcebeerinventory);
    }

    @Bean(name = "beerDb")
    @ConfigurationProperties(prefix = "app.datasource.cardholder")
    public DataSource beerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "BeerJdbcTemplate")
    public JdbcTemplate JdbcTemplatebeer(@Qualifier("beerDb")
                                                 DataSource beerdatasource) {
        return new JdbcTemplate(beerdatasource);
    }
}
