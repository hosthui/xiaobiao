package com.lyh.xiaobiao.service;


import com.lyh.xiaobiao.entity.User;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Map;

public interface UserService {
	
	Map<String,Object> selectPage(int pageNum, int pageSize, String userName,
	                        Long id);
	
	User selectOne(User user);
	
	void insertUser(User user);
	
	void update(User user);
	
	long countUserByDate(Date date);
}
