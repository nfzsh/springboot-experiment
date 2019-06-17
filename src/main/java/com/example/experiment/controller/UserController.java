package com.example.experiment.controller;

import com.example.experiment.entity.Message;
import com.example.experiment.entity.MessageUser;
import com.example.experiment.entity.User;
import com.example.experiment.service.MessageService;
import com.example.experiment.service.UpdateUserService;
import com.example.experiment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private MessageService messageService;//ghw

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
    //ghw
    //回复信息，确认完成时间
    @PostMapping("/endtime")
    public Map getEndTime(@RequestBody MessageUser messageUser)
    {
        //System.out.println(messageUser.getText()+messageUser.getUser()+messageUser.getMessage());
        MessageUser messageUser1=messageService.getEndTime(messageUser.getText(),messageUser.getUser(),messageUser.getMessage());
        return Map.of("MessageUser",messageUser1);
    }
    //根据执行人number返回任务
    @PostMapping("/mbyu")
    public Map getMessageByUser(@RequestBody User user)
    {
        List<Message> messages=messageService.getMessageByUser(user.getNumber());
        return Map.of("UserExamList",messages);
    }
    //ghw部分结束
}
