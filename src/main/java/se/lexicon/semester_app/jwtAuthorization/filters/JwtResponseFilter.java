package se.lexicon.semester_app.jwtAuthorization.filters;

import lombok.Data;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.jwtAuthorization.jwtResponse.JwtEmployeeData;
import se.lexicon.semester_app.jwtAuthorization.jwtResponse.JwtEmployeeVacation;
import se.lexicon.semester_app.service.EmployeeService;
import se.lexicon.semester_app.service.VacationDayService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class JwtResponseFilter {

    private final EmployeeService employeeService;
    private final VacationDayService vacationDayService;

    public List<JwtEmployeeVacation> filterEmployeeVacationDates(int companyId) {
        List<JwtEmployeeVacation> responseList = new ArrayList<>();

        List<EmployeeDto> employeeDtoList = employeeService.findEmployeesByCompanyId(companyId);
        for (EmployeeDto employeeDto : employeeDtoList) {
            List<VacationDayDto> approvedVacationDays = vacationDayService.findByEmployeeId(employeeDto.getId()).stream()
                .filter(VacationDayDto::isApproved).collect(Collectors.toList());

            responseList.add(new JwtEmployeeVacation(employeeDto.getId(),
                                                     employeeDto.getJobTitle(),
                                                     employeeDto.getUser().getFirstName(),
                                                     employeeDto.getUser().getLastName(),
                                                     approvedVacationDays));
        }

        return responseList;
    }

    public List<JwtEmployeeData> filterAllEmployeeData(int companyId) {
        List<JwtEmployeeData> responseList = new ArrayList<>();

        List<EmployeeDto> employeeDtoList = employeeService.findEmployeesByCompanyId(companyId);
        for (EmployeeDto employeeDto : employeeDtoList) {
            List<VacationDayDto> allVacationDays = vacationDayService.findByEmployeeId(employeeDto.getId());

            responseList.add(new JwtEmployeeData(employeeDto.getId(),
                                                 employeeDto.getJobTitle(),
                                                 employeeDto.getSavedVacation(),
                                                 employeeDto.getYearlyVacationDays(),
                                                 employeeDto.getDateOfEmployment(),
                                                 allVacationDays,
                                                 employeeDto.getUser().getFirstName(),
                                                 employeeDto.getUser().getLastName(),
                                                 employeeDto.getUser().getEmail(),
                                                 employeeDto.getUser().getMobile(),
                                                 employeeDto.getUser().getUserType(),
                                                 employeeDto.getUser().isEnabled()));
        }

        return responseList;
    }
}
