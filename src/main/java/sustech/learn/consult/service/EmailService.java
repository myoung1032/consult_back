package sustech.learn.consult.service;

import sustech.learn.consult.entity.Record;
import sustech.learn.consult.entity.Tutor;

import java.text.ParseException;

public interface EmailService {
    void sendActiveEmail(String SID,String code);
    public void sendInquireEmail(String SID,String code);
    public void sendConfirmEmail(String type,String SID,String id,String content,String student,String date);
    public void sendCancelEmail(String SID,String code);
  //  public void sendConfirmEmail(String type,String SID,String id,String content,String student);
    public void sendContraEmail(Long timeId,Long dayId,Long weekId) ;
    public void sendConfirmEmail_to_prof(Tutor tutor, Record record) throws ParseException;
        public void sendConfirmEmail_to_student(Tutor tutor, Record record) throws ParseException;
    }
