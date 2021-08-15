package se.lexicon.semester_app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;
    private String name;
    private String adress;
    private String administrator;
    private String email;
    private String tel;
    private boolean paused;
    private String workspace;

}
