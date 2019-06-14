package com.example.experiment.controller;

import com.example.experiment.entity.User;
import com.example.experiment.service.UpdateUserService;
import com.example.experiment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UpdateUserService updateUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping("/selectuser")
    public User SelectUser(@RequestAttribute int uid) {
        return userService.getUserid(uid);
    }

    @PostMapping("/updateuser")
    public void UpdateUser(@RequestBody User user) {
        String swd;
        if (user.getPassword().equals("") || user.getPassword() == null)
            swd = "";
        else
            swd = passwordEncoder.encode(user.getPassword());
        updateUserService.UpdateUser(user.getNumber(), user.getName(), user.getPro(),
                user.getIntro(), user.getPhonenum(), swd, user.getAuthority(), user.getId());
    }
}
