package com.tianye.controller;

import com.tianye.entity.Chapter;
import com.tianye.service.ChapterService;
import com.tianye.util.MyFileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @ResponseBody
    @RequestMapping("queryChapterByAlbumId")
    public List<Chapter> queryChapterByAlbumId(Integer id){
        return chapterService.queryChapterByAlbumId(id);
    }

    @RequestMapping("insertChapter")
    @ResponseBody
    public void insertChapter(Chapter chapter, MultipartFile file , HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/audio");
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        // 目标文件
        File tFile = new File(realPath+"/"+originalFilename);
        // 上传
        file.transferTo(tFile);
        //设置id(使用UUID保证唯一)
        chapter.setId(UUID.randomUUID().toString().replace("-",""));
        //设置文件大小
        chapter.setSize(MyFileUtil.getFileSize(tFile.length()));
        //设置上传时间
        chapter.setUploadDate(new Date());
        //设置上传文件名(此处应当防止文件名重复)
        chapter.setUrl("/"+originalFilename);
        //设置音频文件的时长
        chapter.setDuration(MyFileUtil.getDuration(tFile));
        chapterService.insertChapter(chapter);
    }

    @RequestMapping("/download")
    @ResponseBody
    public void download(String name, String title,HttpSession session, HttpServletResponse response) throws IOException {
        String realPath = session.getServletContext().getRealPath("/audio");
        String extension = FilenameUtils.getExtension(name);
        File srcFile = new File(realPath+"/"+name);
        byte[] bs = FileUtils.readFileToByteArray(srcFile);
        String fileName = title+"."+extension;
        // 设置响应头信息，以附件的形式下载
        response.setHeader("content-disposition","attachment; filename=" + URLEncoder.encode(fileName,"UTF-8"));
        // 使用响应输出流，往client输出文件内容
        ServletOutputStream sos = response.getOutputStream();
        sos.write(bs);
    }




}
