package se.lexicon.semester_app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
//    @Column(nullable = false, unique = true)
    private String mobile;
    @Column(nullable = false, unique = true)
    private String email;
    private UserType userType;
}
