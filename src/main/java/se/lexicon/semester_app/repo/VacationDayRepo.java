package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.VacationDay;

public interface VacationDayRepo extends CrudRepository<VacationDay,Integer> {
}
