package com.lyh.xiaobiao.service.impl;

import com.lyh.xiaobiao.dao.MeetingDao;
import com.lyh.xiaobiao.entity.Meeting;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class MeetingServiceImpl implements MeetingService {
	
	@Autowired
	MeetingDao meetingDao;
	
	@Override
	public long countMeetingByDate(Date date){
		Specification<Meeting> specification = new Specification<Meeting>() {
			@Override
			public Predicate toPredicate(Root<Meeting> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Expression<Date> publishDate = root.get("publishDate").as(Date.class);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.DATE,1);
				Date time = calendar.getTime();
				Predicate between = criteriaBuilder.between(publishDate, date, time);
				return between;
			}
		};
		long count = meetingDao.count(specification);
		return  count;
	}
}
