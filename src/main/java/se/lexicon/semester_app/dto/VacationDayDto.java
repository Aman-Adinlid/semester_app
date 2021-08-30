package se.lexicon.semester_app.dto;

import lombok.Data;
import se.lexicon.semester_app.entity.VacationType;

import java.time.LocalDate;
import java.util.List;


@Data
public class VacationDayDto {
    private int id;
    private List<LocalDate> vacationDate;
    private boolean approved;
    private VacationType vacationType;
    private EmployeeDto employee;
}
