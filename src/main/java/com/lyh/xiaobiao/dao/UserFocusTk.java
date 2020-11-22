package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.UserFocus;
<<<<<<< HEAD
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

=======
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67

@org.apache.ibatis.annotations.Mapper
public interface UserFocusTk extends Mapper<UserFocus> {
	
	
	@InsertProvider(type = UserFocusTkSqlProvider.class,method = "insertsql")
	int insertBatch(@Param("focusIds") Long[] focusIds,@Param("UserId") Long UserId);
<<<<<<< HEAD

	@DeleteProvider(type = UserFocusTkSqlProvider.class,method = "delsql")
	void delBatch(@Param("focusIds") Long[] focusIds,@Param("UserId") Long UserId);


=======
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
}
