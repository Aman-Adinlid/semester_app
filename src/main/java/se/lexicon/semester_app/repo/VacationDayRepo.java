package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.VacationDay;

import java.time.LocalDate;
import java.util.List;

public interface VacationDayRepo extends CrudRepository<VacationDay,Integer> {
    List<VacationDay> findByVacationDate(LocalDate vacationDate);

    List<VacationDay> findByApproved(boolean approved);

}


