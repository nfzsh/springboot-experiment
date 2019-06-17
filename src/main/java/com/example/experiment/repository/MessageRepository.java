package com.example.experiment.repository;

import com.example.experiment.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MessageRepository  extends JpaRepository<Message,Integer> {
    //管理员修改任务
    @Modifying
    @Query("UPDATE Message t SET t.description = :description where t.status ='open'and t.name = :name")
    Integer updateMessage(@Param("name") String name, @Param("description") String description);

    //管理员关闭任务
    @Modifying
    @Query("UPDATE Message t SET t.status='close' where  t.name = :name")
    void closeMessage(@Param("name") String name);

    //根据任务名查询任务
    @Query("select m from Message m where m.name=:name")
    Message findByName(@Param("name") String name);

    //获取截至时间
    @Query("select m.endTime from Message m where m.id = :id")
    LocalDateTime getTime(@Param("id") int Id);

}
