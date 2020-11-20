package com.lyh.xiaobiao;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.github.pagehelper.PageInfo;
import com.lyh.xiaobiao.dao.UserFocusdao;
import com.lyh.xiaobiao.dao.UserTk;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.entity.UserFocus;
import com.lyh.xiaobiao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
class XiaobiaoApplicationTests {
	
	@Autowired
	UserService userService;

	@Autowired
	UserFocusdao userFocusdao;
	
	@Test
	void contextLoads() {
		Map<String, Object> stringObjectMap = userService.selectPage(1, 5, "", 1L);
		System.out.println("123");
//		List<UserFocus> findfocous = userFocusdao.findfocous(1L);
//		for ( UserFocus userFocus : findfocous ) {
//			System.out.println(userFocus);
//		}
//		List<Long> list=new ArrayList<>();
//		list.add(1L);
//		list.add(2L);
//		userFocusdao.findAllById(list);
//		PageInfo<User> xiao = userService.selectPage(1, 5, "");
//
//		for ( User user : xiao.getList()) {
//
//			System.out.println(user);
//		}
		
		
	}

}
