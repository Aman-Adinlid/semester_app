package se.lexicon.semester_app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String email;
    private UserType userType;
}
