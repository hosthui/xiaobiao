package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.User;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface UserTk extends Mapper<User> {
	
	
	@SelectProvider(type = UserTkSqlProvider.class,method = "pageSql")
	 List<User> selectPage(String userName);
	
}
