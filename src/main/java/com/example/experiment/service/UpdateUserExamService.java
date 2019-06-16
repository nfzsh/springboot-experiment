package com.example.experiment.service;

import com.example.experiment.entity.Exam;
import com.example.experiment.entity.User;
import com.example.experiment.entity.UserExam;
import com.example.experiment.repository.UserExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateUserExamService {
    @Autowired
    private UserExamRepository userExamRepository;
    //更改考试安排
    public UserExam updateUserExam(UserExam userExam){
        int id=userExam.getId();
        UserExam ue=userExamRepository.find(id);
        ue.setUser(userExam.getUser());
        ue.setExam(userExam.getExam());
        userExamRepository.save(ue);
        return ue;
    }
}
