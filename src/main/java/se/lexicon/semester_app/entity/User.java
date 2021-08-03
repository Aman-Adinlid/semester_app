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
    @Column(nullable = false, unique = true)
    private String email;
    private UserType userType;
    private boolean enabled;
    private String mobile;                   //not required
    private String password;


    public User() {
    }

    public User(String firstName, String lastName,
                String mobile,
                String email, UserType userType, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }




}
