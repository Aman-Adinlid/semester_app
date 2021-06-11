package se.lexicon.semester_app.dto;

import lombok.Data;
import se.lexicon.semester_app.entity.Employee;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Data
public class CompanyDto {
    private UUID uuid;
    private String name;
    private List<Employee> employees;
}
