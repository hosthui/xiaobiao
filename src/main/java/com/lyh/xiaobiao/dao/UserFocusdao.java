package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.UserFocus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFocusdao extends JpaRepository<UserFocus,Long>, JpaSpecificationExecutor<UserFocus> {
	
	@Query(value = "select u.* from UserFocus as u where user_id=?1",
			nativeQuery = true)
	List<UserFocus> findfocous(Long id);
}
