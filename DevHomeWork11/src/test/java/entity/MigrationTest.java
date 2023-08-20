package entity;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MigrationTest {

    private Flyway flyway;

    @BeforeEach
    public void setUp() {
        flyway = Flyway.configure()
                .dataSource("jdbc:h2:mem:testdb", "sa", "")
                .load();
    }

    @Test
    public void testMigrations() {
        flyway.migrate();

        assertTrue(true, "Міграції пройшли успішно");
    }
}