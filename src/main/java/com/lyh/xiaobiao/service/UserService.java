package com.lyh.xiaobiao.service;


import com.lyh.xiaobiao.entity.User;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
<<<<<<< HEAD

	Map<String,Object> selectPage(int pageNum, int pageSize, String userName,
	                        Long id);

=======
	
	Map<String,Object> selectPage(int pageNum, int pageSize, String userName,
	                        Long id);
	
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
	User selectOne(User user);
	
	void insertUser(User user);
	
	void update(User user);
	
	Map<String,List> countUserByDate();
	
	int upfocous(Map<String, Long[]> map, Long id);
}
