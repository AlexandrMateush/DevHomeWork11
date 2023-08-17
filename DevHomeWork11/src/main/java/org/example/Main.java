package org.example;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;

import static org.hibernate.cfg.AvailableSettings.*;


public class Main {
    public static void main(String[] args) {
        ClassicConfiguration classicConfiguration = new ClassicConfiguration();
        classicConfiguration.setUrl(URL);
        classicConfiguration.setUser(USER);
        classicConfiguration.setPassword(PASS);
        classicConfiguration.setDataSource(URL,USER,PASS);
        Flyway flyway = new Flyway(classicConfiguration);
        flyway.baseline();
        flyway.migrate();
        flyway.repair();

    }
}