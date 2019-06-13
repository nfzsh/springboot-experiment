package com.example.experiment.repository;

import com.example.experiment.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteUserRepoistory extends CustomizedRepoistory<User, Integer> {
    @Modifying
    @Query("DELETE FROM User WHERE number=:number")
    void Delete(String number);
}
