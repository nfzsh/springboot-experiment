package com.example.experiment.component;

import com.example.experiment.entity.Exam;
import com.example.experiment.entity.User;
import com.example.experiment.service.UserExamService;
import com.example.experiment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class MyTimeJian {
    @Autowired
    private UserService userService;
    @Autowired
    private UserExamService userExamService;

    @Scheduled(cron = "5,10,15,20,25,30 * * * * *")
    public void check(){
        List<User> users=userService.getUList();
        for (int i=0;i<users.size();i++){
            List<Exam> exams=userExamService.getExamList(users.get(i).getId());
            for (int k=0;k<exams.size();k++){
                LocalDateTime start=exams.get(k).getStartTime();
                LocalDateTime end=exams.get(k).getEndTime();
                for (int j=k+1;j<exams.size();j++){
                    if(start.compareTo(exams.get(j).getEndTime())>0 ||
                            end.compareTo(exams.get(j).getStartTime())<0){
                        continue;
                    }else {
                        log.debug(exams.get(k).getName()+" 考试与 "+exams.get(j).getName()+
                                " 考试在时间上冲突，请"+users.get(i).getName()+"老师注意");
                    }
                }
            }
        }
    }
}
