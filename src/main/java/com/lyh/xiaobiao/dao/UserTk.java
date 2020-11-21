package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
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
	
}
