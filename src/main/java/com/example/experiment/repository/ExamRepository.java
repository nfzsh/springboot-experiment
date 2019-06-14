package com.example.experiment.repository;

import com.example.experiment.entity.Exam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends CustomizedRepoistory<Exam,Integer>{
    //根据id找考试
    @Query("SELECT e FROM Exam e WHERE e.id=:id")
    Exam find(@Param("id") int id);
}
