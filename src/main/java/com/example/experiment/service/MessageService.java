package com.example.experiment.service;

import com.example.experiment.entity.Message;
import com.example.experiment.entity.MessageUser;
import com.example.experiment.entity.User;
import com.example.experiment.repository.MessageRepository;
import com.example.experiment.repository.MessageUserRepository;
import com.example.experiment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageUserRepository messageUserRepository;
    @Autowired
    private UserRepository userRepository;


    //获取期限时间
    private LocalDateTime nowtime = LocalDateTime.now();
    //获取截至时间
    public LocalDateTime getTime(int id)
    {
        LocalDateTime time=messageRepository.getTime(id);
        System.out.println(time);
        return time;

    }

    //写回复并提交数据，判断是否超时
    public MessageUser getEndTime(String text, User user, Message message){
        LocalDateTime endtime=getTime(message.getId());
        //System.out.println(nowtime);
        //System.out.println("endtime:"+endtime);
        if(endtime.isAfter(nowtime)){
            String string="在规定时间内完成";
            //System.out.println(string);
            messageUserRepository.writeText(text,user,message,string,nowtime);
        }
        else{
            String string="未在规定时间完成";
            //System.out.println(string);
            messageUserRepository.writeText(text,user,message,string,nowtime);
        }
        MessageUser messageUser=messageUserRepository.findMessageUser(user, message);
        return messageUser;
    }
    //获取某个老师的全部任务
    public List<Message> getMessageByUser(String number){
        List<Message> messages = messageUserRepository.findMessageByUser(number);
        for (Message message:messages){
            log.debug("任务名："+message.getName()+"   任务描述："+message.getDescription()+"   任务截止时间："+message.getEndTime());
        }
        return messages;
    }
    //获取某个任务的全部老师
    /*public List<MessageUser> findUserByMessage(String name){
        List<MessageUser> messageUsers = messageUserRepository.findUserByMessage(name);
        for (MessageUser messageUser:messageUsers){
            log.debug("老师名："+messageUser.getUser().getName()+"    回复内容"+messageUser.getText()+"    回复状态"+messageUser.getResult());
        }
        return messageUsers;
    }*/
    //查找全部任务
    /*public List<MessageUser> findAllMessage()
    {
        List<MessageUser> messageUsers=messageUserRepository.mu();
        return messageUsers;
    }*/

}
