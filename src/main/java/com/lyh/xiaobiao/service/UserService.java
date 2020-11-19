package com.lyh.xiaobiao.service;


import com.lyh.xiaobiao.dao.UserDao;
import com.lyh.xiaobiao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public User selectOne(User user){
		Example<User> of = Example.of(user);
		Optional<User> one = userDao.findOne(of);
		if ( one.isPresent() ){
			return one.get();
		}
		return null;
	}
	
	public void insertUser(User user){
		user.setRegisterTime(new Date());
		User save = userDao.save(user);
	}
	public void update(User user){
		userDao.save(user);
	}
	
	public long countUserByDate(Date date){
		Specification<User> specification = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Expression<Date> register_time = root.get("registerTime").as(Date.class);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.DATE,1);
				Date time = calendar.getTime();
				Predicate between = criteriaBuilder.between(register_time, date, time);
				return between;
			}
		};
		long count = userDao.count(specification);
		return  count;
	}
	
}
