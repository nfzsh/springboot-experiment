package com.example.experiment.controller;

import com.example.experiment.component.EncryptorComponent;
import com.example.experiment.entity.User;
import com.example.experiment.service.UpdateUserService;
import com.example.experiment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    private static final String STUDENT_ROLE = "bb63e5f7e0f2ffae845c";
    private static final String ADMIN_ROLE = "6983f953b49c88210cb9";
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;
    @Autowired
    private UpdateUserService updateUserService;

    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(userService.getUser(user.getNumber()))
                .ifPresentOrElse(u -> {
                    if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                    }
                    Map map = Map.of("uid", u.getId(), "aid", u.getAuthority());
                    // 生成加密token
                    String token = encryptorComponent.encrypt(map);
                    // 在header创建自定义的权限
                    response.setHeader("token", token);
                    String role = null;
                    if (u.getAuthority() == User.USER_AUTHORITY) {
                        role = STUDENT_ROLE;
                    } if (u.getAuthority() == User.ADMIN_AUTHORITY) {
                        role = ADMIN_ROLE;
                    }
                    response.setHeader("role", role);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                });
    }
    @PostMapping("/updateuser")
    public void UpdateUser(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(user)
                .ifPresentOrElse(u -> {
                    String swd;
                    if (user.getPassword().equals("") || user.getPassword() == null)
                        swd = "";
                    else
                        swd = passwordEncoder.encode(user.getPassword());
                    updateUserService.UpdateUser(user.getNumber(), user.getName(), user.getPro(),
                            user.getIntro(), user.getPhonenum(), swd, user.getAuthority(), user.getId());
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "信息不能为空！");
                });
    }
}
