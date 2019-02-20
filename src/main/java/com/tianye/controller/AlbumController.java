package com.tianye.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.tianye.dto.AlbumDto;
import com.tianye.entity.Album;
import com.tianye.service.AlbumService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.content.image.png;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @RequestMapping("queryAllAlbum")
    public AlbumDto queryAllAlbum(Integer page, Integer rows){
        return albumService.queryAllAlbum(page,rows);
    }

    @RequestMapping("queryOneAlbumById")
    public Album queryOneAlbumById(Integer id){
        return albumService.queryOneAlbumById(id);
    }

    @RequestMapping("insertAlbum")
    public void insertAlbum(Album album, MultipartFile file ,MultipartFile txtfile, HttpSession session) throws IOException {
//        ServletContext servletContext = session.getServletContext();
//        String realPath = servletContext.getRealPath("/image");
//        String originalFilename = file.getOriginalFilename();
//        long time = System.currentTimeMillis();
//        String relfile = time+originalFilename;
        // 目标文件
        //File tFile = new File(realPath+"/"+relfile);
        // 上传
        //file.transferTo(tFile);
        InputStream inputStream = file.getInputStream();
        StorePath txt = fastFileStorageClient.uploadFile(inputStream, txtfile.getSize(), "txt", null);
        System.out.println(txt.getGroup()+"==========="+txt.getPath());

        album.setCoverImg(txt.getFullPath());
        album.setCount(0);
        album.setScore(1);
        album.setPublishDate(new Date());
        albumService.insertAlbum(album);
    }

    @RequestMapping("queryAlbum")
    public List<Album> queryAlbum(){
        return albumService.queryAlbum();
    }

    @RequestMapping("exportExl")
    public void exportExl(HttpServletResponse response,HttpSession session){
        List<Album> albums = albumService.export();
        String realPath = session.getServletContext().getRealPath("/image");

        for (Album album : albums) {
            album.setCoverImg(realPath+album.getCoverImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("音频列表", "吉祥妙音"),
                Album.class, albums);

        try {
            String encode = URLEncoder.encode("吉祥妙音.xls", "UTF-8");
            response.setHeader("content-disposition","attachment;filename="+encode);
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("importExl")
    public void importExl(MultipartFile file , HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/xls");
        File file1 = new File(realPath + "/" + file.getOriginalFilename());
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<Album> albums = ExcelImportUtil.importExcel(file1,Album.class, params);
        System.out.println(albums);
        System.out.println(ReflectionToStringBuilder.toString(albums.get(0)));

    }

    @RequestMapping("/search")
    public AlbumDto searcherIndex(String param, Integer page, Integer rows){
        System.out.println(param+"   "+page+"   "+rows);
        AlbumDto albumDto = albumService.searchIndex(param, page, rows);
        System.out.println(albumDto);
        return albumDto;
    }

    @RequestMapping("/addIndex")
    public void addIndex(Album album, MultipartFile file , HttpSession session) throws IOException {
        InputStream inputStream = file.getInputStream();
        StorePath png = fastFileStorageClient.uploadFile(inputStream, file.getSize(), "png", null);
        album.setCoverImg("/"+png.getGroup()+"/"+png.getPath());
        System.out.println(album);
        album.setPublishDate(new Date());
        album.setCount(0);
        album.setScore(1);
        albumService.createIndex(album);
    }
}
