package com.lyh.xiaobiao.service.impl;

import com.lyh.xiaobiao.dao.ArticleDao;
import com.lyh.xiaobiao.dao.ArticleTk;
import com.lyh.xiaobiao.entity.Article;
import com.lyh.xiaobiao.entity.Meeting;
import com.lyh.xiaobiao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	
	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	ArticleTk articleTk;
	
	
	@Override
	public List<Long> countArticleByDate(){
		List<Long> ArticleCount=new ArrayList<>();
		List<Map<String, Object>> maps = articleTk.SelectCount();
		Collections.reverse(maps);
		for ( Map<String, Object> map : maps ) {
			if ( map.get("count")!=null ){
				ArticleCount.add((Long)map.get("count"));
			}else {
				ArticleCount.add(0L);
			}
		}
		return  ArticleCount;
	}
}
