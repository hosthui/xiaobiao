package com.lyh.xiaobiao.dao;

<<<<<<< HEAD
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;

public class UserTkSqlProvider {

	/**
	 * SELECT auser.*,GROUP_CONCAT(fuser.user_focus_id)  user_focus_id FROM user auser LEFT JOIN userfocus fuser on fuser.user_id=auser.id GROUP BY auser.id
	 */



	
	public String pageSql(@Param("userId")Long userId, @Param("username") String username,Integer type){
		



		/**
		 * SELECT " +
		 * 			" a.*, b.ucount user_fans " +
		 * 			"FROM " +
		 * 			"  ( " +
		 * 			"   SELECT " +
		 * 			"      `user`.* " +
		 * 			"   FROM " +
		 * 			"     userfocus userf, " +
		 * 			"      `user` " +
		 * 			"   WHERE " +
		 * 			"     userf.user_id = #{userId} " +
		 * 			"   AND userf.user_focus_id = `user`.id " +
		 * 			"  ) a " +
		 * 			"LEFT JOIN ( " +
		 * 			" SELECT " +
		 * 			"   count(*) ucount, " +
		 * 			"   userf.user_focus_id bfid " +
		 * 			" FROM " +
		 * 			"   userfocus userf, " +
		 * 			"    `user` " +
		 * 			" WHERE " +
		 * 			"   userf.user_focus_id = `user`.id " +
		 * 			" GROUP BY " +
		 * 			"   userf.user_focus_id " +
		 * 			") b ON b.bfid = a.id
		 */


		StringBuffer buffer = new StringBuffer("SELECT " +
				" a.*, b.ucount user_fans " +
				"FROM " +
				"  ( " );

		if (type==1) {
			buffer.append(" SELECT " +
					"  * " +
					" FROM " +
					"  `user` " +
					" WHERE " +
					"  `user`.id != #{userId} ");
		}else {
			buffer.append(
				"   SELECT " +
				"      `user`.* " +
				"   FROM " +
				"     userfocus userf, " +
				"      `user` " +
				"   WHERE " +
				"     userf.user_id = #{userId} " +
				"   AND userf.user_focus_id = `user`.id " );
		}
		buffer.append(
				"  ) a LEFT JOIN ( " +
				" SELECT " +
				"   count(*) ucount, " +
				"   userf.user_focus_id bfid " +
				" FROM " +
				"   userfocus userf, " +
				"    `user` " +
				" WHERE " +
				"   userf.user_focus_id = `user`.id " +
				" GROUP BY " +
				"   userf.user_focus_id " +
				") b ON b.bfid = a.id ");
		if ( !StringUtils.isEmpty(username) ){
			buffer.append(" where username like CONCAT('%',#{username},'%') ");
		}
		buffer.append(" ORDER BY a.id ");
=======
import org.springframework.util.StringUtils;

public class UserTkSqlProvider {
	
	public String pageSql(String username){
		
		/**
		 * SELECT auser.*,GROUP_CONCAT(fuser.user_focus_id)  user_focus_id FROM user auser LEFT JOIN userfocus fuser on fuser.user_id=auser.id GROUP BY auser.id
		 */
		StringBuffer buffer = new StringBuffer(" SELECT auser.*,GROUP_CONCAT(fuser.user_focus_id)  user_focus_id FROM user auser LEFT JOIN userfocus fuser on fuser.user_id=auser.id GROUP BY auser.id ");
		if ( !StringUtils.isEmpty(username) ){
			buffer.append(" HAVING username like CONCAT('%',#{username},'%') ");
		}
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
		return buffer.toString();
	}
}
