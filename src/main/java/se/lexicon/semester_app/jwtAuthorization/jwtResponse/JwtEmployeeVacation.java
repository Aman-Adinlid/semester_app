package se.lexicon.semester_app.jwtAuthorization.jwtResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.semester_app.dto.VacationDayDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtEmployeeVacation {

    private String employeeId;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private List<VacationDayDto> vacationDayDtoList;
}
