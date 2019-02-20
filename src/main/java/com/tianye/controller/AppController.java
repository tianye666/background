package com.tianye.controller;

import com.tianye.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class AppController {
    @Autowired
    private AppService appService;

    @RequestMapping("/query")
    @ResponseBody
    public Object queryMainpageData(String uid, String type, String sub_type) {
        Object result = appService.queryFirstPage(uid, type, sub_type);
        return result;
    }

    //闻详情页
    @RequestMapping("/weninfo")
    @ResponseBody
    public Object queryAlbumInfoAndChapter(String id, String uid) {
        Object detailOfWen = appService.detailOfWen(id, uid);
        return detailOfWen;
    }
}
