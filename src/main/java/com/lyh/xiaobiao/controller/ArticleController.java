package com.lyh.xiaobiao.controller;


import com.lyh.xiaobiao.entity.Article;
import com.lyh.xiaobiao.entity.Result;
import com.lyh.xiaobiao.entity.User;
import com.lyh.xiaobiao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {


    @Autowired
    ArticleService articleService;

    @RequestMapping
    public ModelAndView toarticle(){
        return new ModelAndView("/html/article.html");
    }

    @RequestMapping("detail")
    public ModelAndView toDetail(){
        return new ModelAndView("/html/article_detail.html");
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        return new ModelAndView("/html/article_add.html");
    }

    @RequestMapping("tocollect")
    public ModelAndView tocollect(){
        return new ModelAndView("/html/article_collect.html");
    }

    @RequestMapping("selectAll/{pageNum}/{pageSize}")
    public Result selectall(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam String articleName){
        return new Result(true,"查询成功",articleService.selectPage(pageNum,pageSize,articleName));
    }

    @RequestMapping("favoritecount")
    public Result favoriteCount(@RequestParam Long articleId, HttpSession session){

        User user = (User)session.getAttribute("loginuser");
        Map<String, Object> stringObjectMap = articleService.faCount(articleId, user.getId());
        return new Result(true,"查询成功",stringObjectMap);
    }
    @RequestMapping(value = "favoriteup",method = RequestMethod.PUT)
    public Result favoriteUp(@RequestBody Map<String,Object> map,HttpSession session){
        User user = (User)session.getAttribute("loginuser");
        map.put("userid",user.getId());
        return new Result(true,articleService.favoriteUp(map));
    }

    @RequestMapping(value = "doAdd",method = RequestMethod.POST)
    public Result articleAdd(@RequestBody Article article,HttpSession session){
        User user = (User)session.getAttribute("loginuser");
        article.setBrowseCount(0L);
        article.setPublishDate(new Date());
        article.setPublishRealName(user.getRealName());
        article.setUserId(user.getId());
        articleService.addArt(article);
        return new Result(true,"发布成功");
    }

    @RequestMapping("selectUserAll/{pageNum}/{pageSize}")
    public Result selectUserAll(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam String articleName,HttpSession session){
        User user = (User)session.getAttribute("loginuser");
        return new Result(true,"查询成功",articleService.selectPageByUid(pageNum,pageSize,articleName,user.getId()));
    }
}
