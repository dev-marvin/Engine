package me.tuskdev.engine;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.ConfigurationSection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PooledConnection {

    public final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    private final HikariDataSource hikariDataSource;

    PooledConnection(ConfigurationSection configurationSection) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(configurationSection.getString("url"));
        hikariConfig.setUsername(configurationSection.getString("username"));
        hikariConfig.setPassword(configurationSection.getString("password"));

        configurationSection.getConfigurationSection("dataSourceProperties").getValues(true).forEach(hikariConfig::addDataSourceProperty);

        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    void close() {
        hikariDataSource.close();
    }

}
