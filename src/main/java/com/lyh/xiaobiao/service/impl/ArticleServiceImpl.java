package com.lyh.xiaobiao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.xiaobiao.dao.ArticleDao;
import com.lyh.xiaobiao.dao.ArticleTk;
import com.lyh.xiaobiao.dao.UserTk;
import com.lyh.xiaobiao.entity.Article;
import com.lyh.xiaobiao.entity.Meeting;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	
	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	ArticleTk articleTk;

	@Autowired
	UserTk userTk;

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
	@Override
	public PageInfo<Article> selectPage(int pageNum, int pageSize, String articleName){
		PageHelper.startPage(pageNum,pageSize);
		List<Article> articles = articleTk.selectPage(articleName);
		return new PageInfo<>(articles);
	}

	@Override
	public PageInfo<Article> selectPageByUid(int pageNum, int pageSize, String articleName,Long uid){
		PageHelper.startPage(pageNum,pageSize);
		List<Article> articles = articleTk.selectPageByUid(articleName,uid);
		return new PageInfo<>(articles);
	}

	@Override
	public Map<String,Object> faCount(Long aid, Long uid){
		boolean isfa=false;
		Map<String,Object> map=new HashMap<>();
		List<User> users = userTk.selctfaByaId(aid, uid);
		for (User user1 : users) {
			if (user1.getId().equals(uid)){
				isfa=true;
				users.remove(user1);
				break;
			}
		}
		map.put("list",users);
		map.put("isfa",isfa);
		return map;
	}


	@Override
	public String favoriteUp(Map<String, Object> map){
		if (!StringUtils.isEmpty(map.get("isfa")) &&(Boolean) map.get("isfa")){
			articleTk.delfavorite(  String.valueOf(map.get("articleId")),String.valueOf(map.get("userid")));
			return "取消成功";
		}else {
			articleTk.insertfavorite( String.valueOf(map.get("articleId")),String.valueOf(map.get("userid")));
			return "收藏成功";
		}
	}
	@Override
	public void addArt(Article article){
		articleDao.save(article);
	}
}
