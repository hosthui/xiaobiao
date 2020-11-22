package com.lyh.xiaobiao.service;

<<<<<<< HEAD
import com.github.pagehelper.PageInfo;
import com.lyh.xiaobiao.entity.Article;
import com.lyh.xiaobiao.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ArticleService {
	List<Long> countArticleByDate();

    PageInfo<Article> selectPage(int pageNum, int pageSize, String articleName);

    PageInfo<Article> selectPageByUid(int pageNum, int pageSize, String articleName, Long uid);

    Map<String,Object> faCount(Long id, Long uid);

    String favoriteUp(Map<String, Object> map);

    void addArt(Article article);
=======
import java.util.Date;
import java.util.List;

public interface ArticleService {
	List<Long> countArticleByDate();
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
}
