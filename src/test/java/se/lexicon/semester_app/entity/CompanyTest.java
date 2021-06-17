package se.lexicon.semester_app.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CompanyTest {
    Company company;
    List<Employee> employeeList;

    @BeforeEach
    public void setUp() {
        company = new Company();
        company.setName("Test");
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_Company() {
        Assertions.assertEquals("Test", company.getName());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        Company company = new Company();
        company.setName("Test");
        Assertions.assertTrue(company.equals(company));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        Company company = new Company();
        company.setName("Test");
        Assertions.assertEquals(company.hashCode(), company.hashCode());

    }
}
