package com.example.experiment.service;
import com.example.experiment.entity.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Service
@Transactional
public class AddMessageService {
    @PersistenceContext
    private EntityManager em;
    public void setMessage(String name, String description, LocalDateTime endTime)
    {
        Message message=new Message(name,description,endTime);
        em.persist(message);

    }
}
