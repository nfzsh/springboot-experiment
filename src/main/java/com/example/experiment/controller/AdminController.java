package com.example.experiment.controller;

import com.example.experiment.entity.Exam;
import com.example.experiment.entity.User;
import com.example.experiment.entity.UserExam;
import com.example.experiment.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AddUserService addUserService;
    @Autowired
    private UpdateUserService updateUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeleteUserService deleteUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //sxr添加
    @Autowired
    private AddExamService addExamService;
    @Autowired
    private ExamService examService;
    @Autowired
    private UpdateExamService updateExamService;
    @Autowired
    private AddUserExamService addUserExamService;
    @Autowired
    private UserExamService userExamService;
    @Autowired
    private UpdateUserExamService updateUserExamService;

    @PostMapping("/addexam")
    public void addExam(@RequestBody Exam exam, HttpServletResponse response) {
        Optional.ofNullable(exam)
                .ifPresentOrElse(e -> {
                    Exam dexam = examService.getExamByClass(exam.getClassRoom().trim());
                    if (dexam != null) {
                        LocalDateTime startTime = dexam.getStartTime();
                        LocalDateTime endTime = dexam.getEndTime();
                        if ((exam.getStartTime().compareTo(startTime) > 0 &&
                                endTime.compareTo(exam.getStartTime()) > 0) ||
                                (exam.getEndTime().compareTo(startTime) > 0 &&
                                        endTime.compareTo(exam.getEndTime()) > 0)) {
                            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "时间冲突！");
                        } else if (exam.getStartTime().compareTo(startTime) <= 0 &&
                                endTime.compareTo(exam.getEndTime()) <= 0) {
                            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "时间冲突！");
                        }
                        else if(exam.getStartTime().compareTo(startTime) >= 0 &&
                                endTime.compareTo(exam.getEndTime()) >= 0)
                            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "时间冲突！");
                    }
                    addExamService.setExam(exam.getName(), exam.getClassRoom(), exam.getUserNum(),
                            exam.getStartTime(), exam.getEndTime());
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "信息不能为空！");
                });
    }
    //添加  依据id查询考试
    @PostMapping("/selectexam")
    public @ResponseBody
    Exam SelectExam(@RequestBody Exam exam, HttpServletResponse response) {
        return examService.getExamById(exam.getId());
    }
    //添加  依据name查询考试
    @PostMapping("/selectname")
    public @ResponseBody
    Exam SelectName(@RequestBody Exam exam, HttpServletResponse response) {
        return examService.getExamByName(exam.getName());
    }
    //查询所有考试信息
    @PostMapping("/selectlist")
    public List<Exam> SelectList(HttpServletResponse response){
        return examService.getExamList();
    }
    //添加update exam信息
    @PostMapping("/updateexam")
    public void UpdateExam(@RequestBody Exam exam, HttpServletResponse response) {
        Optional.ofNullable(exam)
                .ifPresentOrElse(e -> {
                    updateExamService.updateAll(exam.getName(),
                            exam.getClassRoom(), exam.getUserNum(),
                            exam.getStartTime(), exam.getEndTime(), exam.getId());
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "信息不能为空！");
                });
    }
    //添加userexam表
    @PostMapping("/adduserexam")
    public Map AddUserExam(@RequestBody UserExam userExam){
        updateExamService.UpdateFlag(2, userExam.getExam().getId());
        addUserExamService.setUserExam(userExam);
        //增加分配时的消息
        log.debug(userExam.getExam().getName()+"  考试时间为："+userExam.getExam().getStartTime()+
                " 至 "+userExam.getExam().getEndTime()+" 考试地点在： "+userExam.getExam().getClassRoom()+
                " 监考老师为： "+userExam.getUser().getName()+" 他/她的监考次数为： "+
                userExam.getUser().getJiankaonum());
        return Map.of("newUserExam",userExam);
    }
    //查询所有考试和安排信息
    @PostMapping("/selectuserexamlist")
    public Map SelectUserExamList(){
        return Map.of("UserExamList",userExamService.getUserExamList());
    }
    @PostMapping("/updateuserexam")
    public Map UpdateUserExam(@RequestBody UserExam userExam){
        System.out.println("sakhcba"+userExam.getId());
        return Map.of("UpdateUserExam", updateUserExamService.updateUserExam(userExam));
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
