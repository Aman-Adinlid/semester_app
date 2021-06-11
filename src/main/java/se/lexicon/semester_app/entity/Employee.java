package se.lexicon.semester_app.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;
    @OneToOne
    private User user;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH, CascadeType.PERSIST,CascadeType.REFRESH})
    private List<VacationDay> vacationDays;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;

}
