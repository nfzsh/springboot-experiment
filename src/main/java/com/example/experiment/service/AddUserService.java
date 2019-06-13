package com.example.experiment.service;

import com.example.experiment.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class AddUserService {
    @PersistenceContext
    private EntityManager em;

    public void setUser(String number, String name, String pro, String intro,
                        String phonenum, String password, int authority) {

        User user = new User(number, name, pro, intro, phonenum, password, authority);

        em.persist(user);
    }
}
