package com.example.experiment.service;

import com.example.experiment.entity.UserExam;
import com.example.experiment.repository.UserExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserExamService {
    @Autowired
    private UserExamRepository userExamRepository;
    public UserExam getUserExam(int uid,int eid){
        return userExamRepository.find(uid,eid);
    }
    //查询所有监考安排
    public List<UserExam> getUserExamList(){
        return userExamRepository.listue();
    }
}
