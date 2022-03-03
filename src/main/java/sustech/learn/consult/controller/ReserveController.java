package sustech.learn.consult.controller;



import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sustech.learn.consult.cache.CommonCache;
import sustech.learn.consult.entity.R;
import sustech.learn.consult.entity.Record;
import sustech.learn.consult.entity.RespEntity;
import sustech.learn.consult.entity.Tutor;
import sustech.learn.consult.service.EmailService;
import sustech.learn.consult.service.MainService;
import sustech.learn.consult.service.TokenService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Api
@CrossOrigin
@RequestMapping("/api/reserve")
@RestController
public class ReserveController {


    private static CommonCache commonCache;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MainService mainService;


    @Autowired
    private EmailService emailService;

    public ReserveController(@Autowired CommonCache commonCache){
        ReserveController.commonCache = commonCache;
    }
    @CrossOrigin
    @GetMapping("/find-my")
    public ResponseEntity getAdvisorId(@RequestParam String SID,@RequestParam String code2,@RequestParam String type,@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "50") Integer size) {
        if (type.equals("1")){
            Object recvCode = commonCache.get(SID);
            String code = String.valueOf(recvCode);
            //System.out.println("2  "+code);
            if (code == null) {
                return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
            }
            if (!code.equals(code2)) {
                return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
            }
            //ResponseEntity.status(400).body()
            ResponseEntity responseEntity = ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByStudentId(SID,PageRequest.of(page - 1, size))).build());
            //  responseEntity.setMessage
            commonCache.remove(SID);
            return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByAdvisorId(SID,PageRequest.of(page - 1, size))).build());
        }
        else{
            Object recvCode = commonCache.get(SID);
            String code = String.valueOf(recvCode);
            //System.out.println("2  "+code);
            if (code == null) {
                return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
            }
            if (!code.equals(code2)) {
                return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
            }
            //ResponseEntity.status(400).body()
            ResponseEntity responseEntity = ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByStudentId(SID,PageRequest.of(page - 1, size))).build());
            //  responseEntity.setMessage
            return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByStudentId(SID,PageRequest.of(page - 1, size))).build());
        }


//.message(tokenService.encode(Long.parseLong(SID), null)
        //return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByAdvisorId(advisorId, PageRequest.of(page - 1, size))));
    }
    //boolean judge = true;
   // @CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders = "*")

  /*  @GetMapping("/cancel")
    public ResponseEntity cancel(@RequestParam String SID,@RequestParam String code2,@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "50") Integer size) {

        Object recvCode = commonCache.get(SID);
        String code = String.valueOf(recvCode);
        //System.out.println("2  "+code);
        if (code == null) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
        }
        if (!code.equals(code2)) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }
        //ResponseEntity.status(400).body()
        ResponseEntity responseEntity = ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByStudentId(SID,PageRequest.of(page - 1, size))).build());
        //  responseEntity.setMessage
        return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByStudentId(SID,PageRequest.of(page - 1, size))).build());
//.message(tokenService.encode(Long.parseLong(SID), null)
        //return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByAdvisorId(advisorId, PageRequest.of(page - 1, size))));
    }
    //boolean judge = true;
    // @CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders = "*")
*/
  @CrossOrigin
    @PostMapping("/post-2")
    public ResponseEntity post2(@RequestBody Payload2 payload) {
        Tutor tutor = mainService.isTutor(payload.advisorId);
        if(null!=tutor){
            System.out.println("导生正在进行记录上传"+payload.getAdvisorId());
        }else {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("你好像不是导生唉，可以试着去学习中心注册成为导生或者是贿赂一下开发者").build());
        }
        //Object recvCode = commonCache.get(payload.getAdvisorId());
        String code = "L@clog_in";
        if (code == null) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
        }
        if (!payload.code.equals(code)) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }
       /* if (!code.equals(payload.getCode())) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }*/
        //commonCache.remove(payload.getAdvisorId());
        return ResponseEntity.status(200).body(mainService.post(payload.getAdvisorId(),
                payload.advisorName,payload.weekId,payload.dayId,payload.timeId,
                payload.semesterId,payload.type));
    }
    @CrossOrigin
    @PostMapping("/post-3")
    public ResponseEntity post3(@RequestBody Payload2 payload) {
        Tutor tutor = mainService.findTutor(payload.advisorName);
        if(null!=tutor){
            System.out.println("导生正在进行记录上传");
        }else {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("你好像不是导生唉，可以试着去学习中心注册成为导生或者是贿赂一下开发者").build());
        }
        //Object recvCode = commonCache.get(payload.getAdvisorId());
        String code = "L@clog_in";
        if (code == null) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
        }
        if (!payload.code.equals(code)) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }
        if (Integer.parseInt(payload.timeId)<9&&Integer.parseInt(payload.timeId)>23) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("时间不对").build());
        }
        if (Integer.parseInt(payload.dayId)<1&&Integer.parseInt(payload.dayId)>7) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("时间不对").build());
        }
       // Tutor tutor1 = mainService.findTutor(payload.advisorName);
       /* if (!code.equals(payload.getCode())) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }*/
        //commonCache.remove(payload.getAdvisorId());
        return ResponseEntity.status(200).body(mainService.post(tutor.getAdvisorId(),
                payload.advisorName,payload.weekId,payload.dayId,payload.timeId,
                payload.semesterId,payload.type));
    }


    @CrossOrigin
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody Payload2 payload) {
        Tutor tutor = mainService.isTutor(payload.advisorId);
        if(null!=tutor){
            System.out.println("导生正在进行记录上传"+payload.getAdvisorId());
        }else {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("你好像不是导生唉，可以试着去学习中心注册成为导生或者是贿赂一下开发者").build());
        }
        Object recvCode = commonCache.get(payload.getAdvisorId());
        String code = String.valueOf(recvCode);
        code = code+"1";
        if (code == null) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
        }
        if (!code.equals(payload.getCode())) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }
        commonCache.remove(payload.getAdvisorId());
        return ResponseEntity.status(200).body(mainService.post(payload.getAdvisorId(),
                payload.advisorName,payload.weekId,payload.dayId,payload.timeId,
                payload.semesterId,"0"));
    }
    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity create(@RequestParam String id,@RequestBody Payload payload) throws ParseException {
        System.out.println(payload);
        System.out.println(payload.getStudentId());
        Object recvCode = commonCache.get(payload.getStudentId());
        String code = String.valueOf(recvCode);
        System.out.println("2  "+code);
        if (code == null) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
        }
        if (!code.equals(payload.getCode())) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }
        commonCache.remove(payload.getStudentId());
        if (payload.studentName.equals("")||payload.consultContent.equals("")){
            return ResponseEntity.status(400).body(RespEntity.code(400).message("姓名和咨询内容不能为空").build());

        }
        if (!confPhone(payload.studentPhone)){
            return ResponseEntity.status(400).body(RespEntity.code(400).message("同学，你这好像不是手机号吧！！！").build());

        }

//        Pattern pattern2 = Pattern.compile(pat2);
//
//        boolean isMatch2 = match2.matches();

        String regex = "[1-9][0-9]{4,14}";
        Pattern r = Pattern.compile(regex);
        Matcher match2 = r.matcher(payload.qqNumber);
        if (!match2.matches()){
            return ResponseEntity.status(400).body(RespEntity.code(400).message("你这是QQ号吗，我都不想点破你！！！").build());

        }
        Record record2= mainService.findById(id);
        if (record2.getStatus()!=Record.Status.registering){
            return ResponseEntity.status(400).body(RespEntity.code(400).message("不应该出现这种现象").build());

        }
        if (mainService.findContradiction(record2.getWeedId(),record2.getDayId(),record2.getTimeId())){
            emailService.sendContraEmail(record2.getTimeId(),record2.getDayId(),record2.getWeedId());
        }
        Record record = mainService.makeOrder(id, payload.studentName,
                payload.studentId,payload.studentPhone,payload.consultContent
        ,payload.qqNumber,payload.college);
        test t = new test();
        Date date = t.calc(record.getWeedId().intValue(),record.getDayId().intValue());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        String content3 ="亲爱的"+payload.studentName+"同学：\n" +
                "你已成功预约导师/导生的学业咨询：\n" +
                "时间：第"+record.getWeedId()+"周 （星期"+record.getDayId()+"） ("+dateString+")"+record.getTimeId()+":00-"+(record.getTimeId()+1)+":00\n" +
                "\n" +
                "地点：学生宿舍11栋205\n" +
                "导师/导生："+record.getAdvisorName()+"\n" +
                "导师/导生联系方式："+record.getStudentPhone()+"\n" +
                "感谢你选择由学习中心统筹开展的学业咨询与帮扶志愿服务项目，希望学业咨询能为你提供学业上的支持与帮助。请你可以提前5-10分钟到达咨询地点，熟悉环境，便于咨询开展。\n" +
                "如需取消改变或取消已经预约好的咨询时间，请至少提前一个工作日通过邮件通知学习中心(learningc@sustech.edu.cn)。\n" +
                "若没有通知改变或取消已经预约好的咨询时间，超过咨询开始时间的20分钟未到，很可惜，中心将按放弃咨询处理（非可控的突发事件除外），扣减你本学期的免费咨询次数一次，若仍需咨询，需重新预约时间。\n" +
                "主动向外寻求帮助是成功的必备，相信追求进步的你一定会在本次咨询中有所收获。\n" +
                "我们期待你的到来！\n" +
                "\n" +
                "\n" +
                "学生工作部学习中心";
       /* String content2 = "亲爱的"+payload.studentName+"同学：\n" +
                "你已成功预约学业咨询：\n" +
                "\n" +
                "时间：第"+record.getWeedId()+"周 （星期"+record.getDayId()+"） ("+dateString+")"+record.getTimeId()+":00-"+(record.getTimeId()+1)+":00\n" +
                "\n" +
                "地点：学生宿舍11栋205\n" +
                "\n" +
                "导生："+record.getAdvisorName()+"\n" +
                "\n" +
                "手机号："+record.getStudentPhone()+"\n" +
                "\n" +
                "感谢你选择学习中心学业咨询。你可以提前几分钟到达咨询室，熟悉下环境。\n" +
                "\n" +
                "如需取消改变或取消已经预约好的咨询时间，请至少提前一个工作日通过邮件通知学习中心(learningc@sustech.edu.cn)。\n" +
                "\n" +
                "主动向外寻求帮助是成功的必备，相信追求进步的你一定会在本次咨询中有所收获。\n" +
                "\n" +
                "如果你有任何疑问，你可以联系学习中心，我们期待你的到来！\n" +
                "\n" +
                "学生工作部学习中心";*/
        String content = "亲爱的"+record.getAdvisorName()+":\n"+payload.studentName+"同学("+payload.studentId+")已成功预约了您的学业咨询：\n\n信息如下：\n"+
               "\nQQ:"+payload.qqNumber+"\n咨询内容："+payload.consultContent+"\n" +
                "";

        Tutor tutor = mainService.isTutor(record.getAdvisorId());

        if (record2.getSort()!=Record.Sort.prof){
            emailService.sendConfirmEmail_to_student(tutor,record);
        }else
        {
            emailService.sendConfirmEmail_to_prof(tutor,record);

        }
        //return ResponseEntity.status(200).body(RespEntity.code(200).message("预约成功"));
        return ResponseEntity.status(200).body(RespEntity.code(200).body(record).build());

    }
    public static String RandomCode(){
        Integer random=(int)((Math.random()*9+1)*100000);
        String code=random.toString();
        return code;
    }
   // @CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders = "*")
   @CrossOrigin
    @GetMapping("/send-code")
    public ResponseEntity loginByPhoneCode(@RequestParam String SID) {

      // String pat3  = "^((11[5,6,7,8,9])|(12[0,1]))\\d{5}$";
      //  String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";


      //  String str = "";
        String pattern = "^((11[5,6,7,8,9])|(12[0,1]))\\d{5}$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(SID);
       // System.out.println(m.matches());
        if (!m.matches()){

            return ResponseEntity.status(401).body(RespEntity.code(401).message("对不起，要么是我正则写错了，要么你是外校派来的特务").build());

        }
        if (null!=commonCache.get(SID)){
            return ResponseEntity.status(401).body(RespEntity.code(401).message("请勿重复获取").build());
        }

        String code = RandomCode();
        commonCache.put(SID, code);
        emailService.sendActiveEmail(SID, code);
        System.out.println(code);
        return ResponseEntity.status(200).body(RespEntity.code(200).message("发送成功").build());

       // return ResponseEntity.status(200).body(RespEntity.code(200).message("验证码已发送，请注意查收").build());
    }

    @Data
    private static class Payload {
        private String id;
        private String studentName;
        private String studentId;
        private String studentPhone;
        private String consultContent;
        private String code;
        private String qqNumber;
        private String college;
        //  private String intro;
        // private String avatar;
    }

    @Data
    private static class Payload2 {
        private String advisorId;
        private String advisorName;
        private String weekId;
        private String dayId;
        private String timeId;
        private String semesterId;
        private String code;
        private String type;
        //  private String intro;
        // private String avatar;
    }


    public static boolean confPhone(String phone){
        if (phone.length() != 11)
        {
            return false;
        }else{
            /**
             * 移动号段正则表达式
             */
            String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
            /**
             * 联通号段正则表达式
             */
            String pat2  = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
            /**
             * 电信号段正则表达式
             */
            String pat3  = "^((133)|(153)|(177)|(18[0,1,9])|(149))\\d{8}$";
            /**
             * 虚拟运营商正则表达式
             */

            String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

            Pattern pattern1 = Pattern.compile(pat1);
            Matcher match1 = pattern1.matcher(phone);
            boolean isMatch1 = match1.matches();
            if(isMatch1){
                return true;
            }
            Pattern pattern2 = Pattern.compile(pat2);
            Matcher match2 = pattern2.matcher(phone);
            boolean isMatch2 = match2.matches();
            if(isMatch2){
                return true;
            }
            Pattern pattern3 = Pattern.compile(pat3);
            Matcher match3 = pattern3.matcher(phone);
            boolean isMatch3 = match3.matches();
            if(isMatch3){
                return true;
            }
            Pattern pattern4 = Pattern.compile(pat4);
            Matcher match4 = pattern4.matcher(phone);
            boolean isMatch4 = match4.matches();
            if(isMatch4){
                return true;
            }
            return false;
        }
    }
}
