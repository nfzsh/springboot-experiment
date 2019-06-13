package com.example.experiment.service;

import com.example.experiment.repository.UpdateUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateUserService {
    @Autowired
    private UpdateUserRepository updateUserRepository;

    public void UpdateUser(String number, String name, String pro, String intro,
                           String phonenum, String password, int authority, int id) {
        if (password.equals(""))
            updateUserRepository.Update(number, name, pro, intro, phonenum, authority, id);
        else
            updateUserRepository.Update(number, name, pro, intro, phonenum, password, authority, id);
    }
    public void UpdateNum(int id){
        updateUserRepository.Update(id);
    }
}
