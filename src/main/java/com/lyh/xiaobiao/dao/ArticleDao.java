package com.lyh.xiaobiao.dao;

import com.lyh.xiaobiao.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleDao extends JpaRepository<Article,Long>, JpaSpecificationExecutor<Article> {
}
