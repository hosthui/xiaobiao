package com.lyh.xiaobiao.service;

import com.github.pagehelper.PageInfo;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.entity.UserFocus;


public interface UserFocusService {
    PageInfo<User> selectPage(int pageNum, int pageSize, Long id);

    void delOne(UserFocus userFocus);
}
