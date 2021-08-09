package se.lexicon.semester_app.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompanyDto {
    private String id;
    private String name;
    private String adress;
    private String admin;
    private String email;
    private String tel;
    private boolean pause;
    private String workspace;
}
