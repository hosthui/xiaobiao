package com.lyh.xiaobiao.controller;


import com.lyh.xiaobiao.entity.Result;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@RequestMapping
	public ModelAndView toUser(){
		return new ModelAndView("html/user.html");
	}
	
	@RequestMapping("selectAll/{pageSize}/{pageNum}")
	public Result selectall(@PathVariable int pageSize,
	                        @PathVariable int pageNum,
	                        @RequestParam String username,
	                        HttpSession session){
		User user = (User)session.getAttribute("loginuser");
		return new Result(true,"查询成功",userService.selectPage(pageNum,pageSize,username,user.getId()));
	}
<<<<<<< HEAD

=======
	
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
	@RequestMapping(value = "doupdate",method = RequestMethod.PUT)
	public Result doupdate(@RequestBody Map<String,Long[]> map,HttpSession session){
		User user = (User)session.getAttribute("loginuser");
		return new Result(true,"更新成功",userService.upfocous(map,user.getId()));
	}

}
