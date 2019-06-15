package com.example.experiment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    public static final int USER_AUTHORITY = 1;
    public static final int ADMIN_AUTHORITY = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    private String name;
    private String pro;
    private String intro;
    private String phonenum;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    // 在没有声明时默认为1
    private int authority = 1;
    private int jiankaonum = 0;


    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;

    public User(int id) {
        this.id = id;
    }

    public User(String number, String name, String pro, String intro,
                String phonenum, String password, int authority) {
        this.number = number;
        this.name = name;
        this.pro = pro;
        this.intro = intro;
        this.phonenum = phonenum;
        this.password = password;
        this.authority = authority;
    }
}
