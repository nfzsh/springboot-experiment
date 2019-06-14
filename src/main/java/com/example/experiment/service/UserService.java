package com.example.experiment.service;

import com.example.experiment.entity.User;
import com.example.experiment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUser(String number) {
        return userRepository.find(number);
    }
    public User getUserid(int id) {
        return userRepository.findu(id);
    }
}