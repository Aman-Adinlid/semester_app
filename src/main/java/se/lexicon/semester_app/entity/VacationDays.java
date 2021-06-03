package se.lexicon.semester_app.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class VacationDays {

    @Id
    private int id;
    private LocalDate vacationDate;
    private  boolean approved;
}
