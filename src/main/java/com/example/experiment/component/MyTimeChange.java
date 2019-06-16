package com.example.experiment.component;

import com.example.experiment.entity.Exam;
import com.example.experiment.entity.User;
import com.example.experiment.service.ExamService;
import com.example.experiment.service.UpdateExamService;
import com.example.experiment.service.UpdateUserService;
import com.example.experiment.service.UserExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Component
public class MyTimeChange {
    @Autowired
    private ExamService examService;
    @Autowired
    private UserExamService userExamService;
    @Autowired
    private UpdateExamService updateExamService;
    @Autowired
    private UpdateUserService updateUserService;

    //检查当天是否有考试完成，并修改状态
    @Scheduled(cron = "5,10,15,20,25,30 * * * * *")
    public void changeFlag(){
        LocalDateTime time=LocalDateTime.now();
        int today=time.getDayOfMonth();
        int nowMonth=time.getMonthValue();
        int nowYear=time.getYear();
        List<Exam> exams=examService.getExamList();
        for (int i=0;i<exams.size();i++){
            int day=exams.get(i).getEndTime().getDayOfMonth();
            int month=exams.get(i).getEndTime().getMonthValue();
            int year=exams.get(i).getEndTime().getYear();
            if(year==nowYear&&month==nowMonth&&today==day){
                updateExamService.UpdateFlag(3,exams.get(i).getId());
                log.debug(exams.get(i).getName()+
                        "考试已经完成，考试状态改为："+exams.get(i).getFlag());
                List<User> users=userExamService.getUserList(exams.get(i).getId());
                for (int j=0;j<users.size();j++){
                    updateUserService.UpdateNum(users.get(j).getId());
                    log.debug(users.get(j).getName()+
                            "的监考次数变为："+users.get(j).getJiankaonum());
                }
            }
        }
    }
}
