package com.lyh.xiaobiao.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

public class ArticleTkSqlProvider {
    
    public String selectPageSql( String arricleName){
        StringBuffer buffer = new StringBuffer(" SELECT * FROM article ");
        if (!StringUtils.isEmpty(arricleName)) {
            buffer.append(" WHERE title LIKE concat('%',#{arricleName},'%') ");
        }
        return buffer.toString();
    }

    /**
     *
     SELECT art.* FROM article art, favorite fav WHERE   fav.a_id=art.id AND fav.u_id=1 AND art.title LIKE '%入%'
     */
    public String selectPageByUid(@Param("arricleName") String arricleName,@Param("uid") Long uid){
        StringBuffer buffer = new StringBuffer(" SELECT art.* FROM article art, favorite fav WHERE fav.a_id=art.id");
        if (!StringUtils.isEmpty(arricleName)) {
            buffer.append(" AND art.title LIKE concat('%',#{arricleName},'%') ");
        }
        buffer.append(" AND fav.u_id=#{uid} ");
        return buffer.toString();
    }
}
