package com.lyh.xiaobiao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.xiaobiao.dao.UserFocusDao;
import com.lyh.xiaobiao.dao.UserFocusTk;
import com.lyh.xiaobiao.dao.UserTk;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.entity.UserFocus;
import com.lyh.xiaobiao.service.UserFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserFocusServiceImpl implements UserFocusService {

    @Autowired
    UserTk userTk;

    @Autowired
    UserFocusDao userFocusDao;


    @Override
    public PageInfo<User> selectPage(int pageNum, int pageSize, Long id){
        PageHelper.startPage(pageNum,pageSize);
        List<User> userFoci = userTk.selectfoByUserId(id,null,2);
        return new PageInfo<>(userFoci);
    }

    @Override
    public void delOne(UserFocus userFocus){
        userFocusDao.delete(userFocus);
    }
}
