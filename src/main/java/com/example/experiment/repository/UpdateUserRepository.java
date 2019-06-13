package com.example.experiment.repository;

import com.example.experiment.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateUserRepository extends CustomizedRepoistory<User, Integer> {
    @Modifying
    @Query("UPDATE User u SET u.number=:number, u.name=:name, u.pro=:pro, u.intro=:intro, " +
            "u.phonenum=:phonenum, u.password=:password, u.authority=:authority WHERE u.id=:id")
    void Update(@Param("number") String number,@Param("name") String name, @Param("pro") String pro,
                @Param("intro") String intro, @Param("phonenum") String phonenum,
                @Param("password") String password, @Param("authority") int authority,
                @Param("id") int id);
}
