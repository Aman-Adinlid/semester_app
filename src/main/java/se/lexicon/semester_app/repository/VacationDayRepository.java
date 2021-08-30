package se.lexicon.semester_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.semester_app.entity.VacationDay;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface VacationDayRepository extends CrudRepository<VacationDay, Integer> {

    List<VacationDay> findByVacationDate(LocalDate vacationDate);

    List<VacationDay> findByApproved(boolean approved);






}
