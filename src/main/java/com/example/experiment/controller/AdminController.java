package com.example.experiment.controller;

import com.example.experiment.entity.Exam;
import com.example.experiment.entity.User;
import com.example.experiment.service.*;
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

    //sxr添加
    @Autowired
    AddExamService addExamService;
    @Autowired
    ExamService examService;
    @Autowired
    UpdateExamService updateExamService;

    @PostMapping("/addexam")
    public void addExam(@RequestBody Exam exam,HttpServletResponse response){
        Optional.ofNullable(exam)
                .ifPresentOrElse(e->{
                    addExamService.setExam(exam.getName(),
                            exam.getClassRoom(), exam.getUserNum(),
                            exam.getStartTime(),exam.getEndTime()
                            );
                },() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "信息不能为空！");
                });
    }
    //添加  依据id查询考试
    @PostMapping("/selectexam")
    public @ResponseBody
    Exam SelectExam(@RequestBody Exam exam,HttpServletResponse response){
        return examService.getExamById(exam.getId());
    }
    //添加  依据name查询考试
    @PostMapping("/selectname")
    public @ResponseBody
    Exam SelectName(@RequestBody Exam exam,HttpServletResponse response){
        return examService.getExamByName(exam.getName());
    }
    //添加update exam信息
    @PostMapping("/updateexam")
    public void UpdateExam(@RequestBody Exam exam,HttpServletResponse response){
        Optional.ofNullable(exam)
                .ifPresentOrElse(e->{
                    updateExamService.updateAll(exam.getName(),
                            exam.getClassRoom(), exam.getUserNum(),
                            exam.getStartTime(), exam.getEndTime(), exam.getId());
                },() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "信息不能为空！");
                });
    }
    //添加结束

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
