package sustech.learn.consult.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sustech.learn.consult.entity.Record;
import sustech.learn.consult.entity.Tutor;
import sustech.learn.consult.repository.RecordRepository;
import sustech.learn.consult.repository.TutorRepository;
import sustech.learn.consult.service.MainService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {


    @Resource
    private RecordRepository recordRepository;
    @Resource
    private TutorRepository tutorRepository;

    public Tutor isTutor(String SID) {
        return tutorRepository.findFirstByAdvisorIdAndStatus(SID,Tutor.Status.normal);
        //return tutorRepository.findAllByStatus(Tutor.Status.normal);
    }

    public Page<Tutor> getTutor(Pageable pageable) {
        return tutorRepository.findAll(pageable);
        //return tutorRepository.findAllByStatus(Tutor.Status.normal);
    }

    public Tutor getTutor2(String SID) {
        Tutor tutor= tutorRepository.findFirstByAdvisorId(SID);
        return tutor;
        //return tutorRepository.findAllByStatus(Tutor.Status.normal);
    }




    public Page<Record> getByAdvisor(String weekId, String advisorName, Pageable pageable) {

        return recordRepository.findAllByWeedIdAndAdvisorName(Long.parseLong(weekId),advisorName,pageable);


    }

    public Page<Record> getByAdvisorId( String advisorId, Pageable pageable) {

        //return recordRepository.findAllByAdvisorId(advisorId,pageable);

        return recordRepository.findAllByAdvisorId(advisorId,pageable);

    }

    public Page<Record> getByStudentId( String advisorId, Pageable pageable) {

        //return recordRepository.findAllByAdvisorId(advisorId,pageable);

        return recordRepository.findAllByStudentIdAndStatus(advisorId,Record.Status.normal,pageable);

    }
    public boolean findContradiction( Long weekId,Long dayId,Long timeId) {

        //return recordRepository.findAllByAdvisorId(advisorId,pageable);
        if (null!=recordRepository.findFirstByDayIdAndTimeIdAndWeedIdAndStatus(dayId,timeId,weekId, Record.Status.normal)
        ){
            return true;
        }else{
            return false;
        }

    }


    @Override
    public Page<Record> getBySquare(String dayId,String timeId, String weekId,Pageable pageable) {
        return recordRepository.findAllByDayIdAndTimeIdAndWeedId( Long.valueOf(dayId),Long.valueOf(timeId),Long.valueOf(weekId),pageable);
    }

    @Override
    public Page<Record> getBySquare2(String dayId,String timeId, String weekId,String type,Pageable pageable) {

        if (type.equals("1")){
            return recordRepository.findAllByDayIdAndTimeIdAndWeedIdAndSort( Long.valueOf(dayId),Long.valueOf(timeId),Long.valueOf(weekId),Record.Sort.prof,pageable);

        }else {
            return recordRepository.findAllByDayIdAndTimeIdAndWeedIdAndSort( Long.valueOf(dayId),Long.valueOf(timeId),Long.valueOf(weekId),Record.Sort.tutor,pageable);

        }
    }

    public Page<Record> getByWeek(String weekId,  Pageable pageable) {

        return recordRepository.findAllByWeedId(Long.parseLong(weekId),pageable);


    }

    @Override
    public Page<Record> getByDay(String weekId, String dayId, Pageable pageable) {
        return recordRepository.findAllByWeedIdAndDayId(Long.parseLong(weekId),Long.parseLong(dayId),pageable);
    }



    public Page<Record> getByWeek2(String weekId,String type,  Pageable pageable) {
/*
        if (type.equals("1")){
            return recordRepository.findAllByWeedIdAndSort(Long.parseLong(weekId),Record.Sort.prof,pageable);

        }else {
            return recordRepository.findAllByWeedIdAndSort(Long.parseLong(weekId),Record.Sort.tutor,pageable);

        }*/
return null;

    }

    @Override
    public Page<Record> getByDay2(String weekId, String dayId, String type,Pageable pageable) {

        if (type.equals("1")){
            return recordRepository.findAllByWeedIdAndDayIdAndSort(Long.parseLong(weekId),Long.parseLong(dayId),Record.Sort.prof,pageable);

        }else {
            return recordRepository.findAllByWeedIdAndDayIdAndSort(Long.parseLong(weekId),Long.parseLong(dayId),Record.Sort.prof,pageable);

        }
    }

    public Tutor findTutor(String name){
         return tutorRepository.findFirstByAdvisorName(name);
    }

    public Record post(String advisorId,String advisorName,String weekId,String dayId,String timeId,String semesterId,String type) {
        Record record = new Record();
        record.setAdvisorId(advisorId);
        record.setAdvisorName(advisorName);
        record.setWeedId(Long.parseLong(weekId));
        record.setDayId(Long.parseLong(dayId));
        record.setTimeId(Long.parseLong(timeId));
        record.setSemesterId(Long.parseLong(semesterId));
        record.setStatus(Record.Status.registering);
        if (type.equals("1")){
            record.setSort(Record.Sort.prof);
        }else {
            record.setSort(Record.Sort.tutor);
        }
        return recordRepository.save(record);
    }
    public Record findById(String id){
        return recordRepository.findFirstById(Long.parseLong(id));
    }
    public Record makeOrder(String id,String studentName,
                            String studentId,String studentPhone,String consultContent
                            ,String qqNumber,String college) {
        Record record = recordRepository.findFirstById(Long.parseLong(id));
        record.setStudentName(studentName);
        record.setStudentId(studentId);
        record.setStudentPhone(studentPhone);
        record.setConsultContent(consultContent);
        record.setUpdatedAt(new Date());
        record.setQqNumber(qqNumber);
        record.setCollege(college);
        record.setStatus(Record.Status.normal);
            return recordRepository.save(record);
    }


    public boolean remove(Long semesterId,Long weekId,Long dayId,Long timeId){
        List<Record> l = recordRepository.findFirstByWeedIdAndDayIdAndTimeId(0L,dayId,timeId);
        for (int i = 0; i < l.size(); i++) {
            Record r=l.get(i);
            r.setStatus(Record.Status.remove);
        }

        return true;
    }
    public boolean remove2(Long id){
        Record record = recordRepository.findFirstById(id);
        if (record==null){
            return false;
        }
        record.setStatus(Record.Status.remove);

        return true;
    }

    @Override
    public String[][] getWeekName(Long weedId) {
        String result[][]= new String[7][16];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 16; j++) {
                result [i][j]="";
            }
        }
        List<Record> l =recordRepository.findAllByWeedId(weedId);
        //开始填充二维数组
        for (int i = 0; i < l.size(); i++) {
            Record r= l.get(i);
            if (result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9].equals("")){
                result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9]=r.getAdvisorName();

            }else{
                result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9]=result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9]+","+r.getAdvisorName();

            }

        }
        return result;
    }
    @Override
    public String[][] getWeekName2(Long weedId,String type) {
        String result[][]= new String[7][16];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 16; j++) {
                result [i][j]="";
            }
        }
        if (type.equals("1")){
            List<Record> l =recordRepository.findAllByWeedIdAndSort(weedId,Record.Sort.prof);
            for (int i = 0; i < l.size(); i++) {
                Record r= l.get(i);
                if (result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9].equals("")){
                    result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9]=r.getAdvisorName();

                }else{
                    result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9]=result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9]+","+r.getAdvisorName();

                }

            }
            return result;
        }else {
            List<Record> l =recordRepository.findAllByWeedIdAndSort(weedId,Record.Sort.tutor);
            for (int i = 0; i < l.size(); i++) {
                Record r= l.get(i);
                if (result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9].equals("")){
                    result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9]=r.getAdvisorName();

                }else{
                    result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9]=result[r.getDayId().intValue()-1][r.getTimeId().intValue()-9]+","+r.getAdvisorName();

                }

            }
            return result;
        }
        //开始填充二维数组

    }
    @Override
    public boolean[] getWeekStatus(Long weedId) {
        boolean result[]= new boolean[112];
        List<Record> l =recordRepository.findAllByWeedId(weedId);
        //开始填充二维数组
        for (int i = 0; i < l.size(); i++) {
            Record r= l.get(i);
            if (r.getStatus().equals(Record.Status.registering))
            result[16*(r.getDayId().intValue()-1)+(r.getTimeId().intValue()-9)]=true;

        }
        return result;
    }

    @Override
    public boolean[] getWeekStatus2(Long weedId,String type) {
        boolean result[]= new boolean[112];
        if (type.equals("1")){
            List<Record> l =recordRepository.findAllByWeedIdAndSort(weedId,Record.Sort.prof);
            //开始填充二维数组
            for (int i = 0; i < l.size(); i++) {
                Record r= l.get(i);
                if (r.getStatus().equals(Record.Status.registering))
                    result[16*(r.getDayId().intValue()-1)+(r.getTimeId().intValue()-9)]=true;

            }
            return result;

        }else {
            List<Record> l =recordRepository.findAllByWeedIdAndSort(weedId,Record.Sort.tutor);
            //开始填充二维数组
            for (int i = 0; i < l.size(); i++) {
                Record r= l.get(i);
                if (r.getStatus().equals(Record.Status.registering))
                    result[16*(r.getDayId().intValue()-1)+(r.getTimeId().intValue()-9)]=true;

            }
            return result;
        }


    }
    public boolean remove(Long semesterId,Long weekId,Long dayId){
        List<Record> list  = recordRepository.findAllByWeedIdAndDayId(weekId,dayId);
        System.out.println(list);
       // List<Record> list2 = list.getContent();

        for (int i = 0; i <list.size() ; i++) {
            Record record =  list.get(i);
            record.setStatus(Record.Status.remove);
            recordRepository.save(record);
        }


        return true;
    }


    //还未完成
    public Long calculate(int month,int date){
        if (month==3&&(date==15||date==16||date==17||date==18||date==19||date==20||date==21)){
            return new Long(5);
        }

        return new Long(99);

    }


/*
    @Override
    public boolean create(Long semesterId, Long weekId) {
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 10; j++) {
                if (null!=recordRepository.findFirstIdAndWeedIdAndDayIdAndTimeId(0L,new Long((long)i),new Long((long)j))){
                    Record record = recordRepository.findFirstIdAndWeedIdAndDayIdAndTimeId(0L,new Long((long)i),new Long((long)j));
                    Record record2 = new Record();

                    record2.setAdvisorId(record.getAdvisorId());
                    record2.setSemesterId(record.getSemesterId());
                    record2.setWeedId(weekId);
                    record2.setDayId(record.getDayId());
                    record2.setTimeId(record.getTimeId());
                    record2.setAdvisorName(record.getAdvisorName());
                    record2.setCreatedAt(new Date());
                    record2.setStatus(Record.Status.registering);
                    return true;


                }

            }
            
        }
        return false;
    }*/
}
