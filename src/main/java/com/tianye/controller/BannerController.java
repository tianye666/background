package com.tianye.controller;

import com.tianye.dto.BannerDto;
import com.tianye.entity.Banner;
import com.tianye.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    BannerService bannerService;

    @RequestMapping("queryBannerByPage")
    public BannerDto queryBannerByPage(Integer page,Integer rows){
       return bannerService.queryAllBannerByPage(page,rows);
    }

    @RequestMapping("updateBanner")
    public void updateBanner(Banner banner){
        bannerService.updateBanner(banner);
    }

    @RequestMapping("insertBanner")
    public void insertBanner(Banner banner, MultipartFile file , HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/image");
        String originalFilename = file.getOriginalFilename();
        long time = System.currentTimeMillis();
        String relfile = time+originalFilename;
        // 目标文件
        File tFile = new File(realPath+"/"+relfile);
        // 上传
        file.transferTo(tFile);
        banner.setImgPath("/"+relfile);
        banner.setPubDate(new Date());
        bannerService.insertBanner(banner);
    }

    @RequestMapping("deleteBanner")
    public String deleteBanner(Banner banner,HttpSession session){
        System.out.println(banner);
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/image");
        File file = new File(realPath+banner.getImgPath());
        file.delete();
        bannerService.deleteBannerById(banner.getId());
        return "";
    }

}
