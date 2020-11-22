package com.lyh.xiaobiao.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @RequestMapping
    public ModelAndView todept(){
        return new ModelAndView("/html/department.html");
    }
}
