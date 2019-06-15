package com.example.experiment.service;

import com.example.experiment.entity.Exam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Service
@Transactional
public class AddExamService {
    @PersistenceContext
    private EntityManager em;
    public void setExam(String name, String classRoom,int userNum,
                        LocalDateTime startTime,
                        LocalDateTime endTime){

        Exam e1=new Exam(name,classRoom,
                userNum, startTime, endTime);
        em.persist(e1);
    }
}
