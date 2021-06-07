package se.lexicon.semester_app.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID") @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String mobile;
    //@OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH, CascadeType.PERSIST,CascadeType.REFRESH})
    //private List<VacationDay> vacationDay;
    private int savedVacation;
    private int yearlyVacationDay;
    private LocalDate dateOfEmployment;
    //@ManyToOne
    //private Company company;
}
