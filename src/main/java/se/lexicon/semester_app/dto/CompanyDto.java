package se.lexicon.semester_app.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CompanyDto {
    private int id;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    private String workspace;
}
