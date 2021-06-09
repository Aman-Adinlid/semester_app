package se.lexicon.semester_app.entity;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CompanyTest {

    Company company;

    @BeforeEach
    public void setUp() {
        company = new Company();
        company.setName("TripAdvisor");
    }
}
