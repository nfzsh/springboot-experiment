package com.example.experiment.repository;

import com.example.experiment.entity.Exam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UpdateExamRepository extends CustomizedRepoistory<Exam,Integer>{
    @Modifying
    @Query("UPDATE Exam e SET e.name=:name,e.classRoom=:classRoom,e.userNum=:userNum," +
            "e.startTime=:startTime,e.endTime=:endTime WHERE e.id=:id")
    void Update(@Param("name") String name, @Param("classRoom") String classRoom,
                @Param("userNum") int userNum, @Param("startTime") LocalDateTime startTime,
                @Param("endTime") LocalDateTime endTime,@Param("id") int id);

}
