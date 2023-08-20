package org.example;


import org.example.cons.DatabaseConfig;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;

public class Main {
    public static void main(String[] args) {
        ClassicConfiguration classicConfiguration = new ClassicConfiguration();
        classicConfiguration.setDataSource(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);

        Flyway flyway = new Flyway(classicConfiguration);
        flyway.baseline();
        flyway.migrate();
        flyway.repair();
    }
}