package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.Article;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface ArticleTk extends Mapper<Article> {
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
			" a left join (  select date(publish_date) as datetime, count(*) as count " +
			"  from article " + "  group by date(publish_date) " + ") b on a.click_date = b.datetime;")
	List<Map<String,Object>> SelectCount();

	@SelectProvider(type = ArticleTkSqlProvider.class,method = "selectPageSql")
	 List<Article> selectPage(String articleName);

	@SelectProvider(type = ArticleTkSqlProvider.class,method = "selectPageByUid")
	List<Article> selectPageByUid(@Param("arricleName") String arricleName,@Param("uid") Long uid);

	@Delete(" DELETE FROM favorite WHERE u_id=#{uid} AND a_id=#{aid} ")
	void delfavorite(@Param("aid") String aid, @Param("uid")String uid);

	@Insert(" INSERT INTO favorite VALUES(#{uid},#{aid}) ")
	void insertfavorite(@Param("aid") String aid,@Param("uid")String uid);
}
