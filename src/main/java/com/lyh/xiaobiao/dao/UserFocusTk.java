package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.UserFocus;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface UserFocusTk extends Mapper<UserFocus> {
	
	
	@InsertProvider(type = UserFocusTkSqlProvider.class,method = "insertsql")
	int insertBatch(@Param("focusIds") Long[] focusIds,@Param("UserId") Long UserId);

	@DeleteProvider(type = UserFocusTkSqlProvider.class,method = "delsql")
	void delBatch(@Param("focusIds") Long[] focusIds,@Param("UserId") Long UserId);


}
