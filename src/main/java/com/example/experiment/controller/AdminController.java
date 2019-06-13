package com.example.experiment.controller;

import com.example.experiment.entity.User;
import com.example.experiment.service.AddUserService;
import com.example.experiment.service.DeleteUserService;
import com.example.experiment.service.UpdateUserService;
import com.example.experiment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AddUserService addUserService;
    @Autowired
    UpdateUserService updateUserService;
    @Autowired
    UserService userService;
    @Autowired
    DeleteUserService deleteUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/adduser")
    public void AddUser(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(user)
                .ifPresentOrElse(u -> {
                    String swd;
                    swd = passwordEncoder.encode(user.getPassword());
                    addUserService.setUser(user.getNumber(), user.getName(), user.getPro(),
                            user.getIntro(), user.getPhonenum(), swd, user.getAuthority());
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "信息不能为空！");
                });
    }

    @PostMapping("/selectuser")
    public @ResponseBody
    User SelectUser(@RequestBody User user, HttpServletResponse response) {
        return userService.getUser(user.getNumber());
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

    @PostMapping("/deleteuser")
    public void DeleteUser(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(user)
                .ifPresentOrElse(u -> {
                    deleteUserService.DeleteUser(user.getNumber());
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "信息不能为空！");
                });
    }
}
