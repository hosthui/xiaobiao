package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.User;
<<<<<<< HEAD
import com.lyh.xiaobiao.entity.UserFocus;
import org.apache.ibatis.annotations.*;
=======
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface UserTk extends Mapper<User> {
	
	
	@Select("select a.click_date,b.count " + 
			"from (  SELECT curdate() as click_date " +
			"    union all " +
			"    SELECT date_sub(curdate(), interval 1 day) as click_date " +
			"    union all " +
			"    SELECT date_sub(curdate(), interval 2 day) as click_date " +
			"    union all " +
			"    SELECT date_sub(curdate(), interval 3 day) as click_date " +
			"    union all " +
			"    SELECT date_sub(curdate(), interval 4 day) as click_date " +
			"    union all " +
			"    SELECT date_sub(curdate(), interval 5 day) as click_date " +
			"    union all " +
			"  SELECT date_sub(curdate(), interval 6 day) as click_date " +
			"    union all " +
			"  SELECT date_sub(curdate(), interval 7 day) as click_date " +
			"    union all " +
			"    SELECT date_sub(curdate(), interval 8 day) as click_date ) "+
			" a left join (  select date(register_time) as datetime, count(*) as count " +
			"  from `user` " + "  group by date(register_time) " + ") b on a.click_date = b.datetime;")
	List<Map<String,Object>> SelectCount();
<<<<<<< HEAD


	/**
	 * 										SELECT * FROM `user` WHERE 	`user`.id != 1
	 * select a.*,b.ucount user_fans FROM (SELECT `user`.* FROM userfocus userf,`user` WHERE userf.user_id = 1 and userf.user_focus_id=`user`.id ) a
	 * LEFT JOIN (SELECT count(*) ucount,userf.user_focus_id bfid FROM userfocus userf,`user`  WHERE userf.user_focus_id=`user`.id GROUP BY userf.user_focus_id)
	 * b ON b.bfid=a.id
	 */



	@SelectProvider(type = UserTkSqlProvider.class,method = "pageSql")
	List<User> selectfoByUserId(@Param("userId")Long userId,@Param("username") String username,Integer type);

	@Select("SELECT " +
			" a.*, b.ucount user_fans " +
			"FROM " +
			" ( " +
			"  SELECT `user`.*  FROM favorite fa,`user` WHERE a_id=#{aid} AND `user`.id=fa.u_id " +
			" ) a " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  count(*) ucount, " +
			"  userf.user_focus_id bfid " +
			" FROM " +
			"  userfocus userf, " +
			"  `user` " +
			" WHERE " +
			"  userf.user_focus_id = `user`.id " +
			" GROUP BY " +
			"  userf.user_focus_id " +
			") b ON b.bfid = a.id")
	List<User> selctfaByaId(@Param("aid") Long aid,@Param("uid")Long uid);

=======
	
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
}
