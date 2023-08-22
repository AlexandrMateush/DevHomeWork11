package org.example;

import org.flywaydb.core.Flyway;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Application {
    public Application() {
    }

    public void initialize() {
        Properties properties = loadProperties("application.properties");
        String jdbcUrl = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        Flyway flyway = Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();
    }

    private Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    public static void main(String[] args) {
        Application app = new Application();
        app.initialize();
    }
}





