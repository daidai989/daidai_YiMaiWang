package com.kgc.easybuy.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.pojo.News;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.CategoryService;
import com.kgc.easybuy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author daidai
 */
@RestController
@CrossOrigin(origins = "*")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @RequestMapping("getNewsList")
    public ResponseMessage getNewsList() {
        ResponseMessage responeseMsg = newsService.getNewsList();
        return responeseMsg;
    }

    @RequestMapping("getNewsListByPage")
    public Object getNewsListByPage(String title,String currentNo) {
        int _currentPageNo = 1;

        if (currentNo!=null&&!"".equals(currentNo)) {
            _currentPageNo = Integer.parseInt(currentNo);
        }
        Page page = new Page();
        page.setPageSize(5);
        page.setCurrentPageNo(_currentPageNo);
        PageInfo<News> newsList = newsService.getNewsListByPage(title, page);
        return newsList;
    }
    @RequestMapping("delNews")
    public ResponseMessage delNews(Integer id) {
        ResponseMessage responeseMsg = newsService.delNews(id);
        return responeseMsg;
    }

    @RequestMapping("getNewsByTitle")
    public ResponseMessage getNewsByTitle(String title) {
        ResponseMessage responeseMsg = newsService.getNewsByTitle(title);
        return responeseMsg;
    }
    @RequestMapping("getNewsById")
    public ResponseMessage getNewsById(Integer id) {
        ResponseMessage responeseMsg = newsService.getNewsById(id);
        return responeseMsg;
    }

    @RequestMapping("addNews")
    public ResponseMessage addNews(@RequestBody News news) {
        ResponseMessage responeseMsg = newsService.addNews(news);
        return responeseMsg;
    }


    @RequestMapping("updateNews")
    public ResponseMessage updateNews(@RequestBody News news) {
        ResponseMessage responeseMsg = newsService.updateNews(news);
        return responeseMsg;
    }

}
