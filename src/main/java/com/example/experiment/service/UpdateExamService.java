package com.example.experiment.service;

import com.example.experiment.repository.UpdateExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class UpdateExamService {
    @Autowired
    private UpdateExamRepository updateExamRepository;
    public void updateAll(String name,String classRoom,int userNum,LocalDateTime startTime,
                          LocalDateTime endTime,int id){
         updateExamRepository.Update(name, classRoom, userNum,
                 startTime, endTime, id);
    }
    public void UpdateFlag(int flag,int id){
        updateExamRepository.Update(flag,id);
    }
}
