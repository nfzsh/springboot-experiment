package com.example.experiment.component;

import com.example.experiment.entity.Exam;
import com.example.experiment.entity.User;
import com.example.experiment.service.ExamService;
import com.example.experiment.service.UserExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class MyTime {
    @Autowired
    private ExamService examService;
    @Autowired
    private UserExamService userExamService;

    @Scheduled(cron = "10,20,30,40,50 * * * * *")
    public void sendMassage(){
        LocalDateTime nowTime=LocalDateTime.now();
        int today=nowTime.getDayOfMonth();
        int nextDay=today+1;
        int nowMonth=nowTime.getMonthValue();
        int nowYear=nowTime.getYear();
        List<Exam> exams=examService.getExamList();
        for (int i=0;i<exams.size();i++){
            int day=exams.get(i).getStartTime().getDayOfMonth();
            int month=exams.get(i).getStartTime().getMonthValue();
            int year=exams.get(i).getStartTime().getYear();
            if(year==nowYear&&month==nowMonth&&nextDay==day){
                List<User> users=userExamService.getUserList(exams.get(i).getId());
                String name="";
                for (int j=0;j<users.size();j++){
                    name=name+users.get(j).getName()+", ";
                }
                log.debug("考试科目："+exams.get(i).getName()+
                        "  考试地点："+exams.get(i).getClassRoom()+
                        "  考试开始时间："+exams.get(i).getStartTime()+
                        "  考试结束时间："+exams.get(i).getEndTime());
                log.debug("        "+name);
            }
        }
    }
}
