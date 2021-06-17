package se.lexicon.semester_app.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(nullable = false, unique = true)
    private String firstName;
    @Column(nullable = false, unique = true)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String mobile;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private int savedVacation;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
    @OneToOne
    private User user;


}
