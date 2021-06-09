package se.lexicon.semester_app.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompanyDto {
    private int id;
    private String name;
    List<EmployeeDto> employeeDtos;


    //ADDED
    private EmployeeDto employeeDto;
}
