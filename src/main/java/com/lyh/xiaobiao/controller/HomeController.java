package com.lyh.xiaobiao.controller;


import com.lyh.xiaobiao.entity.Result;
import com.lyh.xiaobiao.service.ArticleService;
import com.lyh.xiaobiao.service.MeetingService;
import com.lyh.xiaobiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MeetingService meetingService;
	
	@Autowired
	ArticleService articleService;
	
	
	@RequestMapping
	public ModelAndView toHome(){
		return new ModelAndView("html/home.html");
	}
	
	@RequestMapping("selectAll")
	public Result selectall(@RequestBody String[] dates){
		List<Long> ArticleCount=new ArrayList<>();
		List<Long> MeetingCount=new ArrayList<>();
		List<Long> UserCount=new ArrayList<>();
		Map<String, List<Long>> AllCount=new HashMap<>();
		Calendar instance = Calendar.getInstance();
		int year = instance.get(Calendar.YEAR);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
			try {
				for ( String date : dates ) {
					Date parse = simpleDateFormat.parse(year + "." + date);
					UserCount.add(userService.countUserByDate(parse));
					MeetingCount.add(meetingService.countMeetingByDate(parse));
					ArticleCount.add(articleService.countArticleByDate(parse));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			AllCount.put("user",UserCount);
			AllCount.put("meeting",MeetingCount);
			AllCount.put("article",ArticleCount);
		return new Result(true,"查询成功",AllCount);
	}
}
