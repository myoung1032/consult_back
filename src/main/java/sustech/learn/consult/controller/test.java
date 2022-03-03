package sustech.learn.consult.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class test {

    public static void main(String[] args) throws ParseException {
       // System.out.println(calc(4,1));
        test t = new test();

        ArrayList<Integer> arr=t.change(new Date());

        Date currentTime = t.calc(3,1);
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);
        System.out.println(arr.get(0));//第几周
        System.out.println(arr.get(1));//星期几


    }
    public  Date calc(int weekId,int dayId) throws ParseException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate1 = dateFormat1.parse("2022-02-13");
        System.out.println(myDate1.getMonth());
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(myDate1);
//        if (weekId>4){
//            calendar.add(Calendar.DATE,(weekId+1)*7+dayId);
//
//        }else {
            calendar.add(Calendar.DATE,(weekId-1)*7+dayId);
//
//        }
        Date date=calendar.getTime();
        return date;
    }

    public  ArrayList<Integer> change(Date date) throws ParseException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat1.parse("2022-02-13");
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        int year1=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        month1++;
        int day1=calendar.get(Calendar.DATE);
        Date target= dateFormat1.parse(year1+"-"+month1+"-"+day1);
        int day=(int)((target.getTime()-start.getTime())/(1000*3600*24));
        int week=day/7+1;
        int dayOfWeek=day%7;
        ArrayList<Integer> arr=new ArrayList<>();
        arr.add(week);
        arr.add(dayOfWeek);
        return arr;
    }

    public  ArrayList<Long> change2(Date date) throws ParseException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat1.parse("2022-02-13");
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        int year1=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        month1++;
        int day1=calendar.get(Calendar.DATE);
        Date target= dateFormat1.parse(year1+"-"+month1+"-"+day1);
        long day=(long)((target.getTime()-start.getTime())/(1000*3600*24));
        Long week=day/7;
        Long dayOfWeek=day%7;
        ArrayList<Long> arr=new ArrayList<>();
        arr.add(week);
        arr.add(dayOfWeek);
        return arr;
    }/*
    public  Date calc(int weekId,int dayId) throws ParseException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate1 = dateFormat1.parse("2021-08-29");
        System.out.println(myDate1.getMonth());
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(myDate1);
        if (weekId>4){
            calendar.add(Calendar.DATE,(weekId+1)*7+dayId);

        }else {
            calendar.add(Calendar.DATE,(weekId)*7+dayId);

        }
        Date date=calendar.getTime();
        return date;
    }

    public  ArrayList<Integer> change(Date date) throws ParseException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat1.parse("2021-08-29");
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        int year1=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        month1++;
        int day1=calendar.get(Calendar.DATE);
        Date target= dateFormat1.parse(year1+"-"+month1+"-"+day1);
        int day=(int)((target.getTime()-start.getTime())/(1000*3600*24));
        int week=day/7;
        int dayOfWeek=day%7;
        ArrayList<Integer> arr=new ArrayList<>();
        arr.add(week);
        arr.add(dayOfWeek);
        return arr;
    }

    public  ArrayList<Long> change2(Date date) throws ParseException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat1.parse("2021-08-29");
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        int year1=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        month1++;
        int day1=calendar.get(Calendar.DATE);
        Date target= dateFormat1.parse(year1+"-"+month1+"-"+day1);
        long day=(long)((target.getTime()-start.getTime())/(1000*3600*24));
        Long week=day/7;
        Long dayOfWeek=day%7;
        ArrayList<Long> arr=new ArrayList<>();
        arr.add(week);
        arr.add(dayOfWeek);
        return arr;
    }*/
}