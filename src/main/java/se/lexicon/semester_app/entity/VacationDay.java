package se.lexicon.semester_app.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class VacationDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate vacationDate;
    private  boolean approved;
    private  VacationType vacationType;

}
