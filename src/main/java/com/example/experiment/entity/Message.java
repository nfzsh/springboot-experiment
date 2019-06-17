package com.example.experiment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    //任务名称
    private  String name;
    //描述
    private  String description;
    //截止时间
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    //任务状态
    private  String status="open";

    public  Message(String name,String description,LocalDateTime endTime)
    {
        this.name=name;
        this.description=description;
        this.endTime=endTime;
    }
}
