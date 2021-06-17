package se.lexicon.semester_app.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;
    private String name;

}
