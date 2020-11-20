package com.lyh.xiaobiao.controller;


import com.baomidou.kaptcha.Kaptcha;
import com.lyh.xiaobiao.entity.Result;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.service.UserService;
import com.lyh.xiaobiao.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("login")
public class LoginController {
	@Autowired
	UserService userService;
	
	@Autowired
	Kaptcha kaptcha;
	
	@RequestMapping
	public  ModelAndView tologin(){
		return new ModelAndView("/index.html");
	}
	
	@RequestMapping("/loginIn")
	public Result loginin(@RequestBody Map<String,String> logmsg,
	                      HttpSession session){
		
		if (kaptcha.validate(logmsg.get("code"))){
			User user = new User();
			user.setUsername(logmsg.get("username"));
			user.setPassword(logmsg.get("password"));
			User loginUser = userService.selectOne(user);
			if ( loginUser!=null ){
				loginUser.setLoginTime(new Date());
				userService.update(loginUser);
				loginUser.setPassword(null);
				session.setAttribute("loginuser",loginUser);
				return new Result(true,"登录成功");
			}
		}
		return new Result(false,"登录失败,用户名或密码错误");
	}
	
	@RequestMapping("/register")
	public Result Register(@RequestBody User user){
		userService.insertUser(user);
		return new Result(true,"注册成功");
	}
	
	@RequestMapping("/send")
	public Result  sendEmail(@RequestParam String email, HttpSession session){
		if ( StringUtils.isEmpty(email) ){
			return new Result(false,"邮箱不能为空");
		}
		User user = new User();
		user.setEmail(email);
		User one = userService.selectOne(user);
		if ( one!=null ){
			String code = MailUtils.verifyCode();
			MailUtils.sendMail("yun89688@gmail.com",code);
			session.setAttribute("verifyCode",code);
			return new Result(true,"验证吗已发送");
		}
		return  new Result(false,"邮箱未注册");
	}
	
	
	@RequestMapping("/reset/{code}")
	public Result resetPws(@PathVariable String code, @RequestBody User user,
	                       HttpSession session){
		String verifyCode = (String)session.getAttribute("verifyCode");
		if ( verifyCode.equals(code) ){
			userService.update(user);
			return new Result(true,"修改成功");
		}else {
			session.removeAttribute("verifyCode");
			return new Result(false,"验证码错误，请重新发送");
		}
	}
}
