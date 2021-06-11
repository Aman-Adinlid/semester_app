package se.lexicon.semester_app.dto;

import lombok.Data;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.entity.VacationDay;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class EmplyeeDto {
    private UUID uuid;
    private UserDto user;
    private List<VacationDayDto> vacationDays;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
}
