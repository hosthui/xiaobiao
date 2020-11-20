package com.lyh.xiaobiao.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.xiaobiao.dao.UserDao;
import com.lyh.xiaobiao.dao.UserFocusdao;
import com.lyh.xiaobiao.dao.UserTk;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.entity.UserFocus;
import com.lyh.xiaobiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserTk userTk;
	
	@Autowired
	UserFocusdao userFocusdao;
	
	
	@Override
	public Map<String,Object> selectPage(int pageNum, int pageSize,
	                                 String userName,Long id){
		Map<String,Object> map=new HashMap<>();
		Specification<User> specification = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Expression<String> username = root.get("username").as(String.class);
				Expression<Long> id1 = root.get("id").as(Long.class);
				List<Predicate> predicates=new ArrayList<>();
				if ( !StringUtils.isEmpty(userName) ){
					Predicate like = criteriaBuilder.like(username, '%' + userName + '%');
					predicates.add(like);
				}
				if ( id!=null ){
					Predicate equal = criteriaBuilder.notEqual(id1,id);
					predicates.add(equal);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		List<UserFocus> findfocous = userFocusdao.findfocous(id);
		Page<User> all = userDao.findAll(specification, PageRequest.of(pageNum - 1, pageSize));
		map.put("page",all);
		 map.put("focous", findfocous);
		
		return map;
		
//		PageHelper.startPage(pageNum,pageSize);
//		List<User> users = userTk.selectPage(userName);
//		return  new PageInfo<>(users);
		
	}
	
	@Override
	public User selectOne(User user){
		Example<User> of = Example.of(user);
		Optional<User> one = userDao.findOne(of);
		if ( one.isPresent() ){
			return one.get();
		}
		return null;
	}
	
	@Override
	public void insertUser(User user){
		user.setRegisterTime(new Date());
		User save = userDao.save(user);
	}
	
	@Override
	public void update(User user){
		userDao.save(user);
	}
	
	
	@Override
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
