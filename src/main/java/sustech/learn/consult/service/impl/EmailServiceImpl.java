package sustech.learn.consult.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.learn.consult.config.EmailSender;
import sustech.learn.consult.controller.test;
import sustech.learn.consult.entity.Record;
import sustech.learn.consult.entity.Tutor;
import sustech.learn.consult.repository.RecordRepository;
import sustech.learn.consult.service.EmailService;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailSender emailSender;
    @Resource
    private RecordRepository recordRepository;

    @Override
    public void sendActiveEmail(String SID,String code) {
        String subject = "学业咨询预约确认";
        String to = SID+"@mail.sustech.edu.cn";
        //String token = tokenService.encode(user.getUsername());
        //String redirectUrl = "https://coanno.com/sign-in/active/" + URLEncoder.encode(token);
        String template = "您正在进行学业咨询预约，您的验证码为\n"+ code+ "";
        emailSender.send(to, subject, template);
    }


    public void sendConfirmEmail_to_student(Tutor tutor, Record record) throws ParseException {
        test t = new test();
        Date date = t.calc(record.getWeedId().intValue(),record.getDayId().intValue());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        String content = "亲爱的" +record.getAdvisorName()+ ":\n"+
                record.getStudentName()+"同学已成功预约了您的学业咨询，信息如下：\n" +
                "学生基本情况：\n" +
                "学号：" + record.getStudentId() + "\n" +
                "手机号码：" + record.getStudentPhone()+"\n" +
                "咨询内容： " + record.getConsultContent()+ "\n" +
                "时间：第"+record.getWeedId()+"周 （星期"+record.getDayId()+"） ("+dateString+")"+record.getTimeId()+":00-"+(record.getTimeId()+1)+":00\n" +
                "地点：学生宿舍11栋205\n" + "\n" +
                "\n" +
                "感谢您选择担任学业咨询的导生，希望您能为学生提供学业支持与帮助。\n" +
                "如需取消改变或取消已经预约好的咨询时间，请至少提前一个工作日通过邮件通知学习中心learningc@sustech.edu.cn。\n" +
                "文件使用指南：\n" +
                "1.《学业咨询知情同意书》\n" +
                "为了保障您和学生的权益，在每次咨询前应签署知情同意书。\n" +
                "若您使用的咨询场地为学习中心提供的咨询室，我们将提前为您打印好表格，放置在咨询室的桌子上。待双方签署后，请您将表格放在指定的柜子。\n" +
                "2.《学业咨询记录表》\n" +
                "请您在咨询结束后一周内完成该表格，发送至学习中心邮箱learningc@sustech.edu.cn，邮件主题请按照【日期-学业咨询记录表-姓名】格式命名。\n" +
                "\n" +
                "如果你有任何疑问，请随时发送邮件至learningc@sustech.edu.cn，我们期待您的建议！\n" +
                "\n" +
                "\n" +
                "学生工作部学习中心";
        String content2 = "亲爱的"+record.getStudentName()+ "同学：\n" +
                "你已成功预约导生的学业咨询：\n" +
                "时间：第"+record.getWeedId()+"周 （星期"+record.getDayId()+"） ("+dateString+")"+record.getTimeId()+":00-"+(record.getTimeId()+1)+":00\n" +
                "地点：学生宿舍11栋205\n" +
                "导生："+record.getAdvisorName()+"\n" +
                "\n感谢你选择由学习中心统筹开展的学业咨询项目，希望学业咨询能为你提供学业上的支持与帮助。请你可以提前5-10分钟到达咨询地点，熟悉环境，便于咨询开展。\n" +
                "如需取消改变或取消已经预约好的咨询时间，请至少提前一个工作日通过邮件通知学习中心(learningc@sustech.edu.cn)。\n" +
                "若没有通知改变或取消已经预约好的咨询时间，超过咨询开始时间的20分钟未到，很可惜，中心将按放弃咨询处理（非可控的突发事件除外），扣减你本学期的免费咨询次数一次，若仍需咨询，需重新预约时间。\n" +
                "\n主动向外寻求帮助是成功的必备，相信追求进步的你一定会在本次咨询中有所收获。\n" +
                "我们期待你的到来！\n" +
                "\n" +
                "学生工作部学习中心";
        emailSender.send(tutor.getAdvisorId()+"@mail.sustech.edu.cn", "【学习中心】导生学业咨询预约通知", content);
        emailSender.send(record.getStudentId()+"@mail.sustech.edu.cn", "【学习中心】导师/导生学业咨询预约通知", content2);
        //emailSender.send(
    }

    public void sendConfirmEmail_to_prof(Tutor tutor, Record record) throws ParseException {
        test t = new test();
        Date date = t.calc(record.getWeedId().intValue(),record.getDayId().intValue());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        String content = "亲爱的" +record.getAdvisorName()+ "老师:\n"+
                record.getStudentName()+"同学已成功预约了您的学业咨询，信息如下：\n" +
                "学生基本情况：\n" +
                "学号：" + record.getStudentId() + "\n" +
                "手机号码：" + record.getStudentPhone()+"\n" +
                "咨询内容： " + record.getConsultContent()+ "\n" +
                "时间：第"+record.getWeedId()+"周 （星期"+record.getDayId()+"） ("+dateString+")"+record.getTimeId()+":00-"+(record.getTimeId()+1)+":00\n" +
                "地点：" + tutor.getPlace()+"\n" +
                "\n" +
                "感谢您选择担任学业咨询的导师，希望您能为学生提供学业支持与帮助。\n" +
                "如需取消改变或取消已经预约好的咨询时间，请至少提前一个工作日通过邮件通知学习中心learningc@sustech.edu.cn。\n" +
                "文件使用指南：\n" +
                "1.《学业咨询知情同意书》\n" +
                "为了保障您和学生的权益，在每次咨询前应签署知情同意书。\n" +
                "若您使用的咨询场地为学习中心提供的咨询室，我们将提前为您打印好表格，放置在咨询室的桌子上。待双方签署后，请您将表格放在指定的柜子。\n" +
                "2.《学业咨询记录表》\n" +
                "请您在咨询结束后一周内完成该表格，发送至学习中心邮箱learningc@sustech.edu.cn，邮件主题请按照【日期-学业咨询记录表-姓名】格式命名。\n" +
                "\n" +
                "如果你有任何疑问，请随时发送邮件至learningc@sustech.edu.cn，我们期待您的建议！\n" +
                "\n" +
                "\n" +
                "学生工作部学习中心";
        String content2 = "亲爱的"+record.getStudentName()+ "同学：\n" +
                "你已成功预约导师的学业咨询：\n" +
                "时间：第"+record.getWeedId()+"周 （星期"+record.getDayId()+"） ("+dateString+")"+record.getTimeId()+":00-"+(record.getTimeId()+1)+":00\n" +
                "地点：" + tutor.getPlace()+ "\n" +
                "导师："+record.getAdvisorName()+"\n" +
                "\n感谢你选择由学习中心统筹开展的学业咨询项目，希望学业咨询能为你提供学业上的支持与帮助。请你可以提前5-10分钟到达咨询地点，熟悉环境，便于咨询开展。\n" +
                "如需取消改变或取消已经预约好的咨询时间，请至少提前一个工作日通过邮件通知学习中心(learningc@sustech.edu.cn)。\n" +
                "若没有通知改变或取消已经预约好的咨询时间，超过咨询开始时间的20分钟未到，很可惜，中心将按放弃咨询处理（非可控的突发事件除外），扣减你本学期的免费咨询次数一次，若仍需咨询，需重新预约时间。\n" +
                "\n主动向外寻求帮助是成功的必备，相信追求进步的你一定会在本次咨询中有所收获。\n" +
                "我们期待你的到来！\n" +
                "\n" +
                "学生工作部学习中心";

        if (tutor.getAdvisorId().equals("panyp")||tutor.getAdvisorId().equals("caixf")){
            emailSender.send(tutor.getAdvisorId()+"@mail.sustech.edu.cn", "【学习中心】导师学业咨询预约通知", content);
            emailSender.send(record.getStudentId()+"@mail.sustech.edu.cn", "【学习中心】导师/导生学业咨询预约通知", content2);
        }else{
            emailSender.send(tutor.getAdvisorId()+"@sustech.edu.cn", "【学习中心】导师学业咨询预约通知", content);
            emailSender.send(record.getStudentId()+"@mail.sustech.edu.cn", "【学习中心】导师/导生学业咨询预约通知", content2);
        }

    }

    public void sendConfirmEmail(String type,String SID,String id,String content,String student,String date) {
        String subject = "【学习中心】导师/导生学业咨询预约成功通知";
        String to = SID+"@mail.sustech.edu.cn";

        Record record = recordRepository.findFirstById(Long.parseLong(id));
        String tutorId = record.getAdvisorId();
        String content2 = "时间：\n\n      周次:"+record.getWeedId()+"(以校历为准)\n      星期"+record.getDayId()+"("+date+")"
                +"\n      时间段："+record.getTimeId()+":00-"+(record.getTimeId()+1)+":00\n\n地点：学生宿舍11栋205\n\n感谢你选择学习中心学业咨询。你可以提前几分钟到达咨询室，熟悉下环境。\n" +
                "\n" +

                "\n" +
                "学生工作部学习中心";
        //String token = tokenService.encode(user.getUsername());
        //String redirectUrl = "https://coanno.com/sign-in/active/" + URLEncoder.encode(token);
        //String template = "您正在进行学业咨询预约，您的验证码为\n"+ code+ "";
        if (type.equals("0")){
            String to2 = tutorId+"@mail.sustech.edu.cn";
            emailSender.send(to, subject, student);
            emailSender.send(to2, subject, content+content2);
            emailSender.send("learningc@sustech.edu.cn", subject, content+content2);
        }else {
            String to2 = tutorId+"@sustech.edu.cn";
            emailSender.send(to, subject, student);
            emailSender.send(to2, subject, content+content2);
            emailSender.send("learningc@sustech.edu.cn", subject, content+content2);
        }


    }

    @Override
    public void sendInquireEmail(String SID,String code) {
        String subject = "学业咨询预约查询";
        String to = SID+"@mail.sustech.edu.cn";
        //String token = tokenService.encode(user.getUsername());
        //String redirectUrl = "https://coanno.com/sign-in/active/" + URLEncoder.encode(token);
        String template = "您正在进行学业咨询查询，您的验证码为\n"+ code+ "";
        emailSender.send(to, subject, template);
    }

    @Override
    public void sendContraEmail(Long timeId,Long dayId,Long weekId) {
        String subject = "冲突-学业咨询预约";
        String to = "learningc@sustech.edu.cn";
        //String token = tokenService.encode(user.getUsername());
        //String redirectUrl = "https://coanno.com/sign-in/active/" + URLEncoder.encode(token);
        String template = timeId+" \n"+ dayId+ " "+" "+weekId;
        emailSender.send(to, subject, template);
    }
    @Override
    public void sendCancelEmail(String SID,String code) {
        String subject = "学业咨询预约取消";
        String to = SID+"@mail.sustech.edu.cn";
        //String token = tokenService.encode(user.getUsername());
        //String redirectUrl = "https://coanno.com/sign-in/active/" + URLEncoder.encode(token);
        String template = "您正在进行学业咨询取消，您的验证码为\n"+ code+ "";
        emailSender.send(to, subject, template);
    }
}
