package com.example.experiment.repository;

import com.example.experiment.entity.Message;
import com.example.experiment.entity.MessageUser;
import com.example.experiment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageUserRepository extends JpaRepository<MessageUser,Integer> {
    //查找指定老师的指定任务
    @Query("select mu from MessageUser mu where mu.user = :user and mu.message =:message")
    MessageUser findMessageUser(@Param("user") User user, @Param("message") Message message);
    //查找全部任务
    @Query("SELECT mu FROM MessageUser mu")
    List<MessageUser> mu();

    // 回复
    @Modifying
    @Query("UPDATE MessageUser mu SET mu.text = :text,mu.result=:result,mu.receiveTime=:time where mu.user=:user and mu.message = :Message")
    Integer writeText(@Param("text") String text, @Param("user") User user, @Param("Message") Message message, @Param("result") String result, @Param("time") LocalDateTime time);

    //查找某一老师的所有任务
    @Query("select mu.message from  MessageUser mu where mu.user.number =:number")
    List<Message> findMessageByUser(@Param("number") String number);

    //查找某个任务的所有老师的回复信息及其结果
    @Query("select mu from MessageUser mu where mu.message.name = :name")
    List<MessageUser> findUserByMessage(@Param("name") String name);
}
