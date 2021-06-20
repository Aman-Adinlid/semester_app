package se.lexicon.semester_app.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private int savedVacation;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
    @OneToOne
    private User user;
}
