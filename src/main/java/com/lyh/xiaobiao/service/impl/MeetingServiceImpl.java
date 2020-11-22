package com.lyh.xiaobiao.service.impl;

import com.lyh.xiaobiao.dao.MeetingDao;
import com.lyh.xiaobiao.dao.MeetingTk;
import com.lyh.xiaobiao.entity.Meeting;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MeetingServiceImpl implements MeetingService {
	
	@Autowired
	MeetingTk meetingTk;
	
	@Autowired
	MeetingDao meetingDao;
	
	@Override
	public List<Long> countMeetingByDate(){
		List<Long> meetingCount=new ArrayList<>();
		List<Map<String, Object>> maps = meetingTk.SelectCount();
		Collections.reverse(maps);
		for ( Map<String, Object> map : maps ) {
			if ( map.get("count")!=null ){
				meetingCount.add((Long)map.get("count"));
			}else {
				meetingCount.add(0L);
			}
		}
		return  meetingCount;
	}
}
