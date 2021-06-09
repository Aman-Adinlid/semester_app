package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.VacationDay;

import java.time.LocalDate;
import java.util.List;

public interface VacationDayRepo extends CrudRepository<VacationDay, Integer> {
    VacationDay findByVacationDate(LocalDate vacationDate);

    List<VacationDayDto> findByApproved(boolean approved);
}
