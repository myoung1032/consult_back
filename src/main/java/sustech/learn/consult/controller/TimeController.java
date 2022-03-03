package sustech.learn.consult.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import sustech.learn.consult.entity.RespEntity;
import sustech.learn.consult.service.MainService;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


@Api
@CrossOrigin
@RequestMapping("/api/time")
@RestController
public class TimeController {


    @Autowired
    private MainService mainService;

    @CrossOrigin
    @GetMapping("/wd2date")
    @ApiOperation("T1.根据周次天次输出具体日期") // 用户对该接口进行说明的注解
    public ResponseEntity wd2date(@RequestParam int weekId, @RequestParam int timeId) throws ParseException {
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

//    @Scheduled(cron = "0 35 10 * * ?")
//    //或直接指定时间间隔，例如：5秒
//    //@Scheduled(fixedRate=5000)
//    private void schdule() throws ParseException {
//        Date date = new Date();
//        test test1 = new test();
//        System.out.println(date);
//        ArrayList<Long> arr = test1.change2(date);
//        if(arr.get(1).equals(7L)){
//            mainService.remove(20211L, arr.get(0)+1, 1L);
//
//        }else{
//            mainService.remove(20211L, arr.get(0), arr.get(1)+1);
//        }
//
//        System.out.println("ok,executed");
//
//
//        //System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }

//    @Scheduled(cron = "0/5 * * * * ?")
//    //或直接指定时间间隔，例如：5秒
//    //@Scheduled(fixedRate=5000)
//    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }

    //在一个特定的时间执行这个方法
    //cron表达式
    //秒  分  时  日  月  周几
    //@Scheduled(cron = "*/2  *  *  *  *  ?")
/*
    @Scheduled(cron = "0 0 23 * * ?")
    public void r8() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,0L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
    }
*/
/*
    @Scheduled(cron = "0 0 8 * * ?")
    public void r8() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,0L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
}

    @Scheduled(cron = "0 0 9 * * ?")
    public void r9() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,1L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void r10() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,2L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");

    }
    @Scheduled(cron = "0 0 11 * * ?")
    public void r11() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,3L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
    }
    @Scheduled(cron = "0 0 14 * * ?")
    public void r14() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,4L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
    }
    @Scheduled(cron = "0 0 15 * * ?")
    public void r15() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,5L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
    }
    @Scheduled(cron = "0 0 16 * * ?")
    public void r16() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,6L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
    }
    @Scheduled(cron = "0 0 19 * * ?")
    public void r19() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,7L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
    }
    @Scheduled(cron = "0 0 20 * * ?")
    public void r20() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,8L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
    }
    @Scheduled(cron = "0 0 21 * * ?")
    public void r21() {
        Calendar cal = Calendar.getInstance();
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);
        Long weekId = mainService.calculate(month,day);
        if (weekId.equals(99L)){
            System.out.println("未知错误");
        }
        if(mainService.remove(20211L,weekId, (long) WeekOfYear,9L)){
            System.out.println("成功"+new Date());
        }
        System.out.println("错误");
    }*/
}
