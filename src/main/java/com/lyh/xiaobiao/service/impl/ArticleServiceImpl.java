package com.lyh.xiaobiao.service.impl;

import com.lyh.xiaobiao.dao.ArticleDao;
import com.lyh.xiaobiao.entity.Article;
import com.lyh.xiaobiao.entity.Meeting;
import com.lyh.xiaobiao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	
	@Autowired
	ArticleDao articleDao;
	
	
	@Override
	public long countArticleByDate(Date date){
		Specification<Article> specification = new Specification<Article>() {
			@Override
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Expression<Date> publishDate = root.get("publishDate").as(Date.class);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.DATE,1);
				Date time = calendar.getTime();
				Predicate between = criteriaBuilder.between(publishDate, date, time);
				return between;
			}
		};
		long count = articleDao.count(specification);
		return  count;
	}
}
