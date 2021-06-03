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
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String mobile;
    @OneToMany
    private List<VacationDays> vacationDays;
    @OneToMany
    private List<ParentLeave> parentLeave;
    private int savedVacation;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
    private int userType;
    @Column(nullable = false)
    private String password;


}
