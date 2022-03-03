package sustech.learn.consult.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sustech.learn.consult.entity.Record;
import sustech.learn.consult.entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor,Long> {

    Tutor findFirstByAdvisorIdAndStatus(String AdvisorId,Tutor.Status status);
    Tutor findFirstByAdvisorId(String AdvisorId);
    Tutor findFirstByAdvisorName(String AdvisorId);
    Page<Tutor> findAllByStatus(Tutor.Status status,Pageable pageable);
}
