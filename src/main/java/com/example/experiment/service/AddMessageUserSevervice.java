package com.example.experiment.service;

import com.example.experiment.entity.MessageUser;
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
public class AddMessageUserSevervice {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageUserRepository messageUserRepository;
    @Autowired
    private UserRepository userRepository;

    public void createMessageUser(MessageUser messageUser) {

        messageUserRepository.save(messageUser);
    }
}

