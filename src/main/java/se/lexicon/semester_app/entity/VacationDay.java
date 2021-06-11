package se.lexicon.semester_app.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class VacationDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate vacationDate;
    @Column(nullable = false)
    private VacationType vacationType;
    private boolean approved;
}
