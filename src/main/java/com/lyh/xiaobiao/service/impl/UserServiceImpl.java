package com.lyh.xiaobiao.service.impl;


<<<<<<< HEAD
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
=======
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
import com.lyh.xiaobiao.dao.UserDao;
import com.lyh.xiaobiao.dao.UserFocusTk;
import com.lyh.xiaobiao.dao.UserFocusDao;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserTk userTk;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserFocusTk userFocusTk;
	
	@Autowired
	UserFocusDao userFocusdao;
	
	
	@Override
	public Map<String,Object> selectPage(int pageNum, int pageSize,
	                                 String userName,Long id){
<<<<<<< HEAD
		PageHelper.startPage(pageNum,pageSize);
		Map<String,Object> map=new HashMap<>();
//		Specification<User> specification = new Specification<User>() {
//			@Override
//			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//				Expression<String> username = root.get("username").as(String.class);
//				Expression<Long> id1 = root.get("id").as(Long.class);
//				List<Predicate> predicates=new ArrayList<>();
//				if ( !StringUtils.isEmpty(userName) ){
//					Predicate like = criteriaBuilder.like(username, '%' + userName + '%');
//					predicates.add(like);
//				}
//				if ( id!=null ){
//					Predicate equal = criteriaBuilder.notEqual(id1,id);
//					predicates.add(equal);
//				}
//				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//			}
//		};
//		Page<User> all = userDao.findAll(specification, PageRequest.of(pageNum - 1, pageSize));
		List<User> users = userTk.selectfoByUserId(id, userName, 1);
		PageInfo<User> userPageInfo = new PageInfo<>(users);
		List<UserFocus> findfocous = userFocusdao.findfocous(id);
		map.put("page",userPageInfo);
		 map.put("focous", findfocous);
=======
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
		
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
		return map;
		
//		PageHelper.startPage(pageNum,pageSize);
//		List<User> users = userTk.selectPage(userName);
//		return  new PageInfo<>(users);
<<<<<<< HEAD

=======
		
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
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
	public Map<String,List> countUserByDate(){
//		Specification<User> specification = new Specification<User>() {
//			@Override
//			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//				Expression<Date> register_time = root.get("registerTime").as(Date.class);
//				Calendar calendar = new GregorianCalendar();
//				calendar.setTime(date);
//				calendar.add(Calendar.DATE,1);
//				Date time = calendar.getTime();
//				Predicate between = criteriaBuilder.between(register_time, date, time);
//				return between;
//			}
//		};
//		long count = userDao.count(specification);
		List<String> date=new ArrayList<>();
		List<Long> userCount=new ArrayList<>();
		Map<String,List> rmap=new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd");
		List<Map<String, Object>> maps = userTk.SelectCount();
		Collections.reverse(maps);
		for ( Map<String, Object> map : maps ) {
			Date date1 = (Date)map.get("click_date");
			String format = simpleDateFormat.format(date1);
			date.add(format);
			if ( map.get("count")!=null ){
				userCount.add((Long)map.get("count"));
			}else {
				userCount.add(0L);
			}
		}
		rmap.put("Date",date);
		rmap.put("userCount",userCount);
		return  rmap;
	}
	
	@Override
	public int upfocous(Map<String, Long[]> map, Long id){
		
		if ( map.containsKey("newfocus")&&map.get("newfocus")!=null&&map.containsKey("focusId")&&map.get("focusId")!=null ){
		if ( map.get("newfocus").length==map.get("focusId").length&&Arrays.equals(map.get("newfocus"),map.get("focusId"))){
			return 1;
			}
<<<<<<< HEAD
		if (map.get("focusId").length!=0) {
			userFocusTk.delBatch(map.get("focusId"),id);
		}
			if (map.get("newfocus").length!=0) {
				userFocusTk.insertBatch(map.get("newfocus"),id);
			}

=======
		userFocusdao.delbyid(id);
			userFocusTk.insertBatch(map.get("newfocus"),id);
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
		}
		return 1;
	}
	
}
