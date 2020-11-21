package com.lyh.xiaobiao;

import com.lyh.xiaobiao.dao.UserFocusDao;
import com.lyh.xiaobiao.dao.UserTk;
import com.lyh.xiaobiao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class XiaobiaoApplicationTests {
	
	@Autowired
	UserService userService;

	@Autowired
	UserFocusDao userFocusdao;
	
	@Autowired
	UserTk userTk;
	
	@Test
	void contextLoads() {
//		Map<String, Object> stringObjectMap = userService.selectPage(1, 5, "", 1L);
//		System.out.println("123");
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
		
		List<Map<String,Object>> stringMap = userTk.SelectCount();
		Collections.reverse(stringMap);
		System.out.println("123");
	}

}
