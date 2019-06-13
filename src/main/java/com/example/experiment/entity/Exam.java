package com.example.experiment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;//考试科目
    private String classRoom;//考场
    private int studentNum;//参加考试人数
    private boolean flag=false;//是否分配监考老师
    @JsonFormat(pattern = "YY-MM-DD HH-MM")
    private LocalDateTime startTime;//考试开始时间
    @JsonFormat(pattern = "YY-MM-DD HH-MM")
    private LocalDateTime endTime;//考试结束时间
    @ManyToOne
    User user;
    @Column(columnDefinition = "TIME INSTAMP NOT NULL DEFAULT CURRENT_TIMEINSTAMP",
    updatable = false,
    insertable = false)
    private LocalDateTime insertTime;
    public Exam(String name,String classRoom,int studentNum,LocalDateTime startTime,LocalDateTime endTime,boolean flag){
        this.name=name;
        this.classRoom=classRoom;
        this.studentNum=studentNum;
        this.startTime=startTime;
        this.endTime=endTime;
        this.flag =flag;
    }
}
