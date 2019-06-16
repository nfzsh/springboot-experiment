package com.example.experiment.service;

import com.example.experiment.entity.Exam;
import com.example.experiment.entity.User;
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
    public List<User> getUserList(int id){
        return userExamRepository.findU(id);
    }
    public List<Exam> getExamList(int id){
        return userExamRepository.findE(id);
    }
}
