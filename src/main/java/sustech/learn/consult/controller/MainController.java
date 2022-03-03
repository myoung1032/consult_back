package sustech.learn.consult.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import sustech.learn.consult.cache.CommonCache;
import sustech.learn.consult.entity.R;
import sustech.learn.consult.entity.RespEntity;
import sustech.learn.consult.entity.Result;
import sustech.learn.consult.entity.Tutor;
import sustech.learn.consult.service.EmailService;
import sustech.learn.consult.service.MainService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


@Api
@CrossOrigin
@RequestMapping("/api/main")
@RestController
public class MainController {


    private static CommonCache commonCache;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MainService mainService;


    @Scheduled(cron = "0 00 23 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void schdule() throws ParseException {
        Date date = new Date();
        test test1 = new test();
        System.out.println(date);
        ArrayList<Long> arr = test1.change2(date);
        arr.set(0,arr.get(0)-1);
        System.out.println(arr);
        if(arr.get(1).equals(7L)){
            mainService.remove(20211L, arr.get(0)+1, 1L);

        }else{
            mainService.remove(20211L, arr.get(0), arr.get(1)+1);
        }

        System.out.println("ok,executed");


        //System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }

    @CrossOrigin
    @PostMapping("/remove-by-day")
    public ResponseEntity remove(@RequestBody Payload2 payload2){
        String code = "L@clog_in";
        if (code == null) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
        }
        if (!payload2.code.equals(code)) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }
        if (mainService.remove(20211L,Long.parseLong(payload2.weekId),Long.parseLong(payload2.dayId))){
            return ResponseEntity.status(200).body(RespEntity.code(200).message("好,可以了").build());

        }

        return ResponseEntity.status(502).body(RespEntity.code(502).message("出问题").build());

    }

//    @Scheduled(cron = "0 0 23 * * ?")
//    //或直接指定时间间隔，例如：5秒
//    //@Scheduled(fixedRate=5000)
//    private void schdule() throws ParseException {
//        Date date = new Date();
//        test test1 = new test();
//        ArrayList<Long> arr = test1.change2(date);
//        if(arr.get(1).equals(7L)){
//
//        }
//        mainService.remove(20211L, arr.get(0), arr.get(1)+1);
//        System.out.println(date);
//
//        //System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }

//    @Scheduled(cron = "*/5 * * * *")
//    //或直接指定时间间隔，例如：5秒
//    //@Scheduled(fixedRate=5000)
//    private void test() throws ParseException {
//
//
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//
//
//        //System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }
    @CrossOrigin
    @PostMapping("/remove-by-id")
    public ResponseEntity remove2(@RequestParam String id,@RequestBody Payload2 payload2){
        String code = "L@clog_in";
        if (code == null) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
        }
        if (!payload2.code.equals(code)) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }
        if (mainService.remove2(Long.parseLong(id))){
            return ResponseEntity.status(200).body(RespEntity.code(200).message("好,可以了").build());

        }

        return ResponseEntity.status(502).body(RespEntity.code(502).message("出问题").build());

    }
    //@CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders = "*")
    @CrossOrigin
    @GetMapping("/get-tutor")
    public ResponseEntity getTutor(@RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "50") Integer size){

        //Page page1 = mainService.getTutor(PageRequest.of(page - 1, size));
        //page1.getContent();
        //return R.ok().data("items", page1.getContent());
        //return Result.ok().data(page1.getContent());
       // Result result = new Result();
        //result.code()
        //return Result(DataEnum.SUCCESS,null);
        return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getTutor(PageRequest.of(page - 1, size))).build());

      //  return ResponseEntity.status(200).body(mainService.getTutor(PageRequest.of(page - 1, size)));
    }

    //@CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders = "*")
    @CrossOrigin
    @GetMapping("/get-advisor-id")
    public ResponseEntity getAdvisorId(@RequestParam String advisorId,@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "50") Integer size) {
        return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByAdvisorId(advisorId,PageRequest.of(page - 1, size))).build());

        //return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByAdvisorId(advisorId, PageRequest.of(page - 1, size))));
    }
    @CrossOrigin
    @GetMapping("/get-advisor-info")
    public ResponseEntity getAdvisorInfo(@RequestParam String advisorId,@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "50") Integer size) {
        return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByAdvisorId(advisorId,PageRequest.of(page - 1, size))).build());

        //return ResponseEntity.status(200).body(RespEntity.code(200).body(mainService.getByAdvisorId(advisorId, PageRequest.of(page - 1, size))));
    }
    //@CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders = "*")
    @CrossOrigin
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody Payload payload) {
        if(payload.getAdvisorId().equals("11810916")
                ||payload.getAdvisorId().equals("11911831")){
            System.out.println("导生正在进行记录上传"+payload.getAdvisorId());

        }else {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("你好像不是导生唉，可以试着去学习中心注册成为导生或者是贿赂一下开发者").build());

        }

        Object recvCode = commonCache.get(payload.getAdvisorId()+"9");
        String code = String.valueOf(recvCode);
        if (code == null) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
        }


        if (code == null) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请重新获取验证码").build());
        }
        if (!code.equals(payload.getCode())) {
            return ResponseEntity.status(400).body(RespEntity.code(400).message("验证码错误").build());
        }
        commonCache.remove(payload.getAdvisorId());
        return ResponseEntity.status(200).body(mainService.post(payload.getAdvisorId(),payload.advisorName,payload.weekId,payload.dayId,payload.timeId,payload.semesterId,"0"));
    }

    public static String RandomCode(){
        Integer random=(int)((Math.random()*9+1)*100000);
        String code=random.toString();
        return code;
    }
    // @CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders = "*")
    @GetMapping("/send-update-code")
    public ResponseEntity loginByPhoneCode(@RequestParam String SID) {
        if (null!=commonCache.get(SID)){
            return ResponseEntity.status(401).body(RespEntity.code(401).message("请勿重复获取").build());
        }

        String code = RandomCode();
        commonCache.put(SID+"9", code);
        emailService.sendActiveEmail(SID, code);
        System.out.println(code);
        return ResponseEntity.status(200).body(RespEntity.code(200).message("发送成功").build());

        // return ResponseEntity.status(200).body(RespEntity.code(200).message("验证码已发送，请注意查收").build());
    }
/*
    @GetMapping("/get-by-advisor")
    public ResponseEntity get(@RequestParam String weekId,@RequestParam String advisorName,@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "50") Integer size) {
        return ResponseEntity.status(200).body(mainService.getByAdvisor(weekId,advisorName, PageRequest.of(page - 1, size)));
    }
*/
@CrossOrigin
    @GetMapping("/get-by-week")
    public ResponseEntity getByWeek(@RequestParam String weekId,@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "50") Integer size) {
        return ResponseEntity.status(200).body(mainService.getByWeek(weekId, PageRequest.of(page - 1, size)));
    }
    @CrossOrigin
    @GetMapping("/get-by-week2")
    public ResponseEntity getByWeek2(@RequestParam String weekId) {
        return ResponseEntity.status(200).body(mainService.getWeekName(Long.valueOf(weekId)));
    }
    @CrossOrigin
    @GetMapping("/get-by-week3")
    public ResponseEntity getByWeek3(@RequestParam String weekId) {


        return ResponseEntity.status(200).body(mainService.getWeekStatus(Long.valueOf(weekId)));
    }

    @CrossOrigin
    @GetMapping("/get-by-new-week2")
    public ResponseEntity getByNewWeek2(@RequestParam String weekId,@RequestParam String type) {
        return ResponseEntity.status(200).body(mainService.getWeekName2(Long.valueOf(weekId),type));
    }
    @CrossOrigin
    @GetMapping("/get-by-new-week3")
    public ResponseEntity getByNewWeek3(@RequestParam String weekId,@RequestParam String type) {


        return ResponseEntity.status(200).body(mainService.getWeekStatus2(Long.valueOf(weekId),type));
    }

    //时间类接口

    @CrossOrigin
    @GetMapping("/wd2date")
    @ApiOperation("T1.根据周次天次输出具体日期") // 用户对该接口进行说明的注解
    public ResponseEntity wd2date(@RequestParam int weekId,@RequestParam int timeId) throws ParseException {
        test test1 = new test();
        Date currentTime = test1.calc(weekId,timeId);
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);
        return ResponseEntity.status(200).body(dateString);
    }

    @CrossOrigin
    @GetMapping("/wd2date2")
    @ApiOperation("T2.根据周次输出一周的具体日期") // 用户对该接口进行说明的注解
    public ResponseEntity wd2date2(@RequestParam int weekId) throws ParseException {
        test test1 = new test();
        //String time="";
        ArrayList arr = new ArrayList();
        for (int i = 1; i < 8; i++) {
            Date currentTime = test1.calc(weekId,i);
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
            String dateString = formatter.format(currentTime);
            String time2 = ""+i+"\n"+dateString;
            arr.add(time2);
        }

        //System.out.println(dateString);
        return ResponseEntity.status(200).body(arr);
    }


    @CrossOrigin
    @GetMapping("/get-week-number")
    @ApiOperation("T3.根据目前日期计算周次") // 用户对该接口进行说明的注解
    public ResponseEntity getWeekNumber(@RequestParam(defaultValue = "0", name = "null", required = false) int fro) throws ParseException {

        Date date = new Date();
        test test1 = new test();
        System.out.println(date);
        ArrayList<Long> arr = test1.change2(date);
        arr.set(0,arr.get(0)+1);
        System.out.println(arr);
        return ResponseEntity.status(200).body(RespEntity.code(200).message(String.valueOf(arr.get(0))).build());

    }
    @CrossOrigin
    @GetMapping("/get-by-day")
    public ResponseEntity getByDay(@RequestParam String weekId,@RequestParam String dayId,@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "50") Integer size) {
        return ResponseEntity.status(200).body(mainService.getByWeek(weekId, PageRequest.of(page - 1, size)));
    }
    @CrossOrigin
    @GetMapping("/get-by-square")
    public ResponseEntity getBySquare(@RequestParam String dayId,@RequestParam String timeId,@RequestParam String weekId,@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "50") Integer size) {
        return ResponseEntity.status(200).body(mainService.getBySquare(dayId,timeId, weekId,PageRequest.of(page - 1, size)));
    }

    @CrossOrigin
    @GetMapping("/get-by-square2")
    public ResponseEntity getBySquare2(@RequestParam String dayId,@RequestParam String timeId,@RequestParam String weekId,@RequestParam String type,@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "50") Integer size) {
        return ResponseEntity.status(200).body(mainService.getBySquare2(dayId,timeId, weekId,type,PageRequest.of(page - 1, size)));
    }
    @CrossOrigin
    @GetMapping("/tutor")
    public ResponseEntity getTutor(@RequestParam String SID) {

       // return ResponseEntity.status(200).body(mainService.getByWeek(weekId, PageRequest.of(page - 1, size)));
        Tutor tutor = mainService.getTutor2(SID);
        //System.out.println(tutor);
        return ResponseEntity.status(200).body(tutor);

        //return ResponseEntity.status(200).body(mainService.getTutor2(SID));
    }




/*
    @PostMapping("/create")
    public ResponseEntity create(@RequestParam String semesterId,@RequestParam String weekId) {
        if (Integer.parseInt(weekId)<1|Integer.parseInt(weekId)>18){
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请输入正确的周数").build());
        }
        if (mainService.create(new Long((long)Integer.parseInt(weekId)),new Long((long)Integer.parseInt(semesterId)))) {
            return ResponseEntity.status(201).body(RespEntity.code(201).message("创建成功").build());
        }
        return ResponseEntity.status(400).body(RespEntity.code(400).message("未知内部错误").build());
    }

    @PostMapping("/order")
    public ResponseEntity order(@RequestParam String semesterId,@RequestParam String weekId) {
        if (Integer.parseInt(weekId)<1|Integer.parseInt(weekId)>18){
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请输入正确的周数").build());
        }
        if (mainService.create(new Long((long)Integer.parseInt(weekId)),new Long((long)Integer.parseInt(semesterId)))) {
            return ResponseEntity.status(201).body(RespEntity.code(201).message("创建成功").build());
        }
        return ResponseEntity.status(400).body(RespEntity.code(400).message("未知内部错误").build());
    }*/
/*
    @PostMapping("/cancel")
    public ResponseEntity cancel(@RequestParam String semesterId,@RequestParam String weekId) {
        if (Integer.parseInt(weekId)<1|Integer.parseInt(weekId)>18){
            return ResponseEntity.status(400).body(RespEntity.code(400).message("请输入正确的周数").build());
        }
        if (mainService.create(new Long((long)Integer.parseInt(weekId)),new Long((long)Integer.parseInt(semesterId)))) {
            return ResponseEntity.status(201).body(RespEntity.code(201).message("创建成功").build());
        }
        return ResponseEntity.status(400).body(RespEntity.code(400).message("未知内部错误").build());
    }
*/



    @Data
    private static class Payload {
        private String advisorId;
        private String advisorName;
        private String weekId;
        private String dayId;
        private String timeId;
        private String semesterId;
        private String code;
      //  private String intro;
       // private String avatar;
    }




    @Data
    private static class Payload2 {

        private String weekId;
        private String dayId;
        private String code;
    }



    }
