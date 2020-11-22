package com.lyh.xiaobiao.controller;


import com.lyh.xiaobiao.entity.Result;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.entity.UserFocus;
import com.lyh.xiaobiao.service.UserFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/myuser")
public class MyUserController {

    @Autowired
    UserFocusService userFocusService;

    @RequestMapping
    public ModelAndView toMyUser(){
        return new ModelAndView("html/my_user.html");
    }

    @RequestMapping("selectAll/{pageSize}/{pageNum}")
    public Result selectall(@PathVariable int pageNum, @PathVariable int pageSize, HttpSession session){
        User user = (User)session.getAttribute("loginuser");
        return new Result(true,"查询成功",userFocusService.selectPage(pageNum,pageSize,user.getId()));
    }

    @RequestMapping(value = "doupdate",method = RequestMethod.PUT)
    public Result doUpdate(@RequestBody UserFocus userFocus,HttpSession session){
        User user = (User)session.getAttribute("loginuser");
        userFocus.setUserId(user.getId());
        userFocusService.delOne(userFocus);
        return new Result(true,"取消成功");
    }

    @RequestMapping("detail")
    public ModelAndView toDetail(){
        return new ModelAndView("/html/user_detail.html");
    }
}
