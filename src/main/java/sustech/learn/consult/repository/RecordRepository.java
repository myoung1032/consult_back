package sustech.learn.consult.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sustech.learn.consult.entity.Record;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Long> {


    List<Record> findAllByWeedIdAndDayId(Long weekId, Long dayId);
    Record findFirstById(Long id);
    Page<Record> findAllByWeedId(Long weekId,Pageable pageable);
    List<Record> findAllByWeedIdAndSort(Long weekId,Record.Sort sort );

    List<Record> findAllByWeedId(Long weekId);
  //  List<Record> findAllByWeedIdAndSort(Long weekId,Record.Sort sort );

    Page<Record> findAllByWeedIdAndDayIdAndSort(Long weekId, Long dayId,Record.Sort sort, Pageable pageable);

    Page<Record> findAllByWeedIdAndDayId(Long weekId, Long dayId, Pageable pageable);
    Page<Record> findAllByDayIdAndTimeIdAndWeedId(Long dayId, Long timeId, Long weekId,Pageable pageable);
    Page<Record> findAllByDayIdAndTimeIdAndWeedIdAndSort(Long dayId, Long timeId, Long weekId,Record.Sort sort,Pageable pageable);

   // Record findFirstByDayIdAndTimeIdAndWeedIdAndStatus(Long dayId, Long timeId, Long weekId,Record.Sort sort,Record.Status status);

    //  Page<Record> findAllByAdvisorId( String advisorId, Pageable pageable);
    Page<Record> findAllByAdvisorIdAndStatus( String advisorId, Record.Status status,Pageable pageable);
    Page<Record> findAllByStudentIdAndStatus( String studentId, Record.Status status,Pageable pageable);
    Page<Record> findAllByAdvisorId( String advisorId, Pageable pageable);


    Page<Record> findAllBySemesterIdAndWeedIdAndDayId(Long semesterId,Long weekId,Long dayId,Pageable pageable);
    Page<Record> findAllByWeedIdAndAdvisorName(Long weekId, String advisorName, Pageable pageable);
    List<Record> findFirstByWeedIdAndDayIdAndTimeId(Long weekId,Long dayId,Long timeId);

    Record findFirstByDayIdAndTimeIdAndWeedIdAndStatus(Long dayId, Long timeId, Long weekId, Record.Status normal);
}
