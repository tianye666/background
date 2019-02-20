package com.tianye;

import com.tianye.dto.AlbumDto;
import com.tianye.dto.BannerDto;
import com.tianye.entity.Admin;
import com.tianye.entity.Album;
import com.tianye.entity.Banner;
import com.tianye.mapper.*;
import com.tianye.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackgroundApplicationTests {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    BannerMapper bannerMapper;
    @Autowired
    UserService userService;
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LuceneAlbumMapper luceneAlbumMapper;
    @Test
    public void contextLoads() {
        BannerDto bannerDto = new BannerDto();
        RowBounds rowBounds = new RowBounds(0,2);
        List<Banner> banners = bannerMapper.selectByRowBounds(null, rowBounds);
        bannerDto.setRows(banners);
        bannerDto.setTotal(bannerMapper.selectCount(null));
        System.out.println(banners);
    }

    @Test
    public void test(){
        System.out.println(UUID.randomUUID().toString().split("-"));
        List<Album> albums = albumMapper.queryAllAlbum();
        for (Album album : albums) {
            System.out.println(album);
        }
    }



    @Test
    public void test3(){
        Admin tianye = adminMapper.queryAdminByUsername("admin");
        System.out.println(tianye);
    }

//    @Test
//    public void test6(){
//
//        String[] strs = {"title", "author"};
//        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_47, strs, new IKAnalyzer());
//        AlbumDto albumDto = null;
//        try {
//            Query query = multiFieldQueryParser.parse("百知教育");
//            albumDto = luceneAlbumMapper.searcherIndex(query, 1, 3);
//            System.out.println(albumDto);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }


}

