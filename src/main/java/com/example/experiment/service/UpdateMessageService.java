package com.example.experiment.service;

import com.example.experiment.repository.MessageRepository;
import com.example.experiment.repository.MessageUserRepository;
import com.example.experiment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class UpdateMessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageUserRepository messageUserRepository;
    @Autowired
    private UserRepository userRepository;

    public int updateMessage(String description,String name){
        if (messageRepository.updateMessage(name,description)!=0) {
            return messageRepository.updateMessage(name,description);
        }else {
            log.debug("任务不存在");
        }
        return -1;
    }
    public void closeMessage(String name){
        messageRepository.closeMessage(name);
    }
}
