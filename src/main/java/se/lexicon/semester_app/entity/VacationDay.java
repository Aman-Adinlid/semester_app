package se.lexicon.semester_app.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class VacationDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ElementCollection
    private List<LocalDate> vacationDate;
    private boolean approved;
    private VacationType vacationType;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
