package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingDao extends JpaRepository<Meeting,Long>,
		JpaSpecificationExecutor<Meeting> {
	
	
}
