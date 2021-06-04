package se.lexicon.semester_app.entity;

import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class Person {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

}
