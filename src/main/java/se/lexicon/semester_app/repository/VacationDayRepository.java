package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.VacationDay;

import java.time.LocalDate;
import java.util.List;

public interface VacationDayRepository extends CrudRepository<VacationDay, Integer> {

    List<VacationDay> findByVacationDate(LocalDate vacationDate);

    List<VacationDay> findByApproved(boolean approved);

    List<VacationDay> findVacationDaysByEmployee_CompanyId(int id);
}
