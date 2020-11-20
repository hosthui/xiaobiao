package com.lyh.xiaobiao.dao;

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
		return buffer.toString();
	}
}
