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
}
