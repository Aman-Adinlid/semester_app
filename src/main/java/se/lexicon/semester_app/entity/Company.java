package se.lexicon.semester_app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

}
