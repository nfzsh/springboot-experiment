package com.example.experiment.service;

import com.example.experiment.repository.DeleteUserRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteUserService {
    @Autowired
    private DeleteUserRepoistory deleteUserRepoistory;
    public void DeleteUser(String number){
        deleteUserRepoistory.Delete(number);
    }
}
