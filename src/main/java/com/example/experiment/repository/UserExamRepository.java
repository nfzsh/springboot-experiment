package com.example.experiment.repository;

import com.example.experiment.entity.Exam;
import com.example.experiment.entity.User;
import com.example.experiment.entity.UserExam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExamRepository extends CustomizedRepoistory<UserExam,Integer>{
    //依据老师id和考试id查询监考安排
    @Query("SELECT ue FROM UserExam ue WHERE ue.user.id=:uid AND ue.exam.id=:eid")
    UserExam find(@Param("uid") int uid,@Param("eid") int eid);

    @Query("SELECT ue FROM UserExam ue WHERE ue.id=:id")
    UserExam find(@Param("id") int id);
    //查询所有监考安排
    @Query("SELECT ue FROM UserExam ue")
    List<UserExam> listue();
    //依据考试id查监考教师
    @Query("SELECT ue.user FROM UserExam ue WHERE ue.exam.id=:eid")
    List<User> findU(@Param("eid") int eid);
    //依据教师id查考试
    @Query("SELECT ue.exam FROM UserExam ue WHERE ue.user.id=:id")
    List<Exam> findE(@Param("id") int id);
}
