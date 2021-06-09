package se.lexicon.semester_app.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CompanyDtoTest {
    CompanyDto companyDto;
    List<EmployeeDto> employeeDtoList;

    @BeforeEach
    public void setUp() {
        companyDto = new CompanyDto();
        companyDto.setName("Test");
        companyDto.setEmployeeDto(employeeDtoList);
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_CompanyDto() {
        Assertions.assertEquals("Test", companyDto.getName());
        Assertions.assertEquals(employeeDtoList, companyDto.getEmployeeDto());
    }


    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Test");
        companyDto.setEmployeeDto(employeeDtoList);
        Assertions.assertTrue(companyDto.equals(companyDto));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Test");
        companyDto.setEmployeeDto(employeeDtoList);
        Assertions.assertEquals(companyDto.hashCode(), companyDto.hashCode());
    }
}
