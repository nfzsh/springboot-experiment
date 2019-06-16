package com.example.experiment.service;

import com.example.experiment.entity.UserExam;
import com.example.experiment.repository.UserExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddUserExamService {
    @Autowired
    private UserExamRepository userExamRepository;
    //添加监考安排
    public UserExam setUserExam(UserExam userExam){
        userExamRepository.save(userExam);
        return userExamRepository.refresh(userExam);
    }
    //依据两个id得到userexam
    public UserExam getAccurateUserExam(int uid,int eid){
        return userExamRepository.find(uid, eid);
    }
}
