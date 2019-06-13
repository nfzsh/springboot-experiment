package com.example.experiment.controller;

import com.example.experiment.component.EncryptorComponent;
import com.example.experiment.entity.User;
import com.example.experiment.service.AddUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class AddUserController {
    @Autowired
    private AddUserService addUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;
    private static final String STUDENT_ROLE = "bb63e5f7e0f2ffae845c";
    private static final String ADMIN_ROLE = "6983f953b49c88210cb9";

    @PostMapping("/admin/adduser")
    public void AddUser(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(user)
                .ifPresentOrElse(u -> {
                    String swd;
                    swd = passwordEncoder.encode(user.getPassword());
                    addUserService.setUser(user.getNumber(), user.getName(), user.getPro(),
                            user.getIntro(), user.getPhonenum(), swd, user.getAuthority());
        Map map = Map.of("uid", user.getId(), "aid", user.getAuthority());
        // 生成加密token
        String token = encryptorComponent.encrypt(map);
        // 在header创建自定义的权限
        response.setHeader("token", token);
        String role = null;
        if (user.getAuthority() == User.USER_AUTHORITY) {
            role = STUDENT_ROLE;
        } if (user.getAuthority() == User.ADMIN_AUTHORITY) {
            role = ADMIN_ROLE;
        }
        response.setHeader("role", role);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "信息不能为空！");
                });
    }
}
