package com.lyh.xiaobiao.dao;

import org.apache.ibatis.annotations.Param;

public class UserFocusTkSqlProvider {
	
	public String insertsql(@Param("focusIds") Long[] focusIds, @Param("UserId") Long UserId){
		StringBuffer buffer = new StringBuffer(" INSERT INTO userfocus VALUES ");
		for ( int i = 0; i < focusIds.length; i++ ) {
			buffer.append(" ( #{UserId},#{focusIds["+i+"]} ) ,");
		}
		buffer.deleteCharAt(buffer.length()-1);
		return buffer.toString();
	}
<<<<<<< HEAD

	public String delsql(@Param("focusIds") Long[] focusIds,@Param("UserId") Long UserId){
		StringBuffer buffer = new StringBuffer(" DELETE FROM userfocus WHERE user_id=#{UserId} and user_focus_id in ( ");
		for ( int i = 0; i < focusIds.length; i++ ) {
			buffer.append("  #{focusIds["+i+"]},");
		}
		buffer.deleteCharAt(buffer.length()-1);
		buffer.append("  ) ");
		return buffer.toString();
	}
=======
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
}
