package com.example.experiment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MessageUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    //回复
    private  String text;
    //完成时间
    private LocalDateTime receiveTime;
    //完成结果
    private String result="未完成";
    @ManyToOne
    Message message;
    @ManyToOne
    User user;
    public MessageUser(User user, Message message){
        this.user=user;
        this.message=message;
    }
}
