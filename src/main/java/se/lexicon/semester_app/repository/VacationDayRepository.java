package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.VacationDay;

public interface VacationDayRepository extends CrudRepository<VacationDay, Integer> {
}
