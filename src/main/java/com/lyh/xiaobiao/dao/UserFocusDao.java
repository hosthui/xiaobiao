package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.UserFocus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserFocusDao extends JpaRepository<UserFocus,Long>, JpaSpecificationExecutor<UserFocus> {
	
	@Query(value = "select u.* from UserFocus as u where user_id=?1",
			nativeQuery = true)
	List<UserFocus> findfocous(Long id);
	
	@Query(value = "delete u.* from UserFocus u where user_id=?1",
			nativeQuery = true)
	@Modifying
	@Transactional
	void delbyid(Long id);
	
}
