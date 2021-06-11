package se.lexicon.semester_app.dto;

import lombok.Data;
import se.lexicon.semester_app.entity.VacationType;

import java.time.LocalDate;

@Data
public class VacationDayDto {
    private int id;
    private LocalDate vacationDate;
    private VacationType vacationType;
    private boolean approved;
}
