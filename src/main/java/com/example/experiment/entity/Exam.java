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
    private int userNum;//监考人数
    private int flag=1;//状态：1未分配，2已分配，3完成
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;//考试开始时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;//考试结束时间
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
    updatable = false,
    insertable = false)
    private LocalDateTime insertTime;
    public Exam(String name,String classRoom,int userNum,
                LocalDateTime startTime,LocalDateTime endTime){
        this.name=name;
        this.classRoom=classRoom;
        this.userNum=userNum;
        this.startTime=startTime;
        this.endTime=endTime;
    }
}
