package com.example.experiment.repository;

import com.example.experiment.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CustomizedRepoistory<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.number=:number")
    User find(@Param("number") String number);
    @Query("SELECT u FROM User u WHERE u.id=:id")
    User findu(@Param("id") int id);
    @Query("SELECT u FROM User u")
    List<User> list();
}
