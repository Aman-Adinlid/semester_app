package se.lexicon.semester_app.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VacationDayDto {
    private int id;
    private LocalDate vacationDate;
    private boolean approved;

}

