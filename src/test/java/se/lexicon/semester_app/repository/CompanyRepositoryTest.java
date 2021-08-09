package se.lexicon.semester_app.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyRepositoryTest {
    CompanyRepository companyRepository;
    Company company;
    List<Employee> employeeList;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @BeforeEach
    public void setUp() {
        company = new Company();
        company.setName("Test");
        companyRepository.save(company);
    }

    @Test
    @DisplayName("Test1")
    public void test1_findByName() {
        List<Company> companyList = new ArrayList<>();
        companyRepository.findCompanyByNameContainsIgnoreCase("Test").iterator().forEachRemaining(companyList::add);
        assertEquals(1, companyList.size());
    }

    @Test
    @DisplayName("Test2")
    public void test2_findById() {
        List<Company> companyList = new ArrayList<>();
        companyRepository.findAll().iterator().forEachRemaining(companyList::add);
        String expected = companyList.get(0).getId();
        Optional<Company> actual = companyRepository.findById(expected);
        assertEquals("Test", actual.get().getName());
    }

    @Test
    @DisplayName("Test3")
    public void test3_findAll_save() {
        List<Company> companyList = new ArrayList<>();
        companyRepository.findAll().iterator().forEachRemaining(companyList::add);
        assertEquals(2, companyList.size());
    }

    @Test
    @DisplayName("Test4")
    public void test4_delete() {
        List<Company> companyList = new ArrayList<>();
        companyRepository.delete(company);
        List<Company> list = new ArrayList<>();
        companyRepository.findAll().iterator().forEachRemaining(list::add);
        assertEquals(companyList, list);
    }

}
