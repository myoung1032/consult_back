package sustech.learn.consult.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sustech.learn.consult.entity.Record;
import sustech.learn.consult.entity.Tutor;

public interface MainService {
    public Page<Record> getBySquare2(String dayId,String timeId, String weekId,String sort,Pageable pageable) ;


    public Page<Record> getByWeek2(String weekId,String sort,  Pageable pageable) ;

    public String[][] getWeekName2(Long weedId,String type) ;
    public boolean[] getWeekStatus2(Long weedId,String type) ;


    public Page<Record> getByDay2(String weekId, String dayId, String type,Pageable pageable) ;

    public boolean remove2(Long id);

    public String[][] getWeekName(Long weedId);

    public boolean[] getWeekStatus(Long weedId);
    public Record findById(String id);
    public boolean findContradiction( Long weekId,Long dayId,Long timeId);

        public Tutor isTutor(String SID) ;

    public Page<Record> getByStudentId( String advisorId, Pageable pageable) ;
    public Page<Record> getBySquare(String dayId,String timeId, String weekId,Pageable pageable);

        public Page<Tutor> getTutor(Pageable pageable);

    public Page<Record> getByAdvisorId( String advisorId, Pageable pageable);
    public Tutor findTutor(String name);

     //   boolean create(Long semesterId,Long weekId);
    public Record post(String advisorId,String advisorName, String weekId, String dayId, String timeId, String semesterId,String type);
    public Page<Record> getByAdvisor(String weekId, String advisorName, Pageable pageable) ;
    public Page<Record> getByWeek(String weekId,  Pageable pageable) ;
    public Page<Record> getByDay(String weekId, String dayId, Pageable pageable) ;
    public Record makeOrder(String id,String studentName,String studentId,String studentPhone,String consultContent,String qqNumber,String college);
    public Tutor getTutor2(String SID) ;
    public boolean remove(Long semesterId,Long weekId,Long dayId);

    public Long calculate(int month,int date);
    public boolean remove(Long semesterId,Long weekId,Long dayId,Long timeId);
}
