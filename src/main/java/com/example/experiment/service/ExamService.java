package com.example.experiment.service;

import com.example.experiment.entity.Exam;
import com.example.experiment.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    //增加通过考试科目名或考试id查询考试安排的方法
    public Exam getExamByName(String name) {
        return examRepository.find(name);
    }

    public Exam getExamById(int id) {
        return examRepository.findId(id);
    }

    public Exam getExamByClass(String classRoom) {
        return examRepository.findClass(classRoom);
    }

}
