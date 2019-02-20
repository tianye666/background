package com.tianye.service;

import com.tianye.dto.AlbumDto;
import com.tianye.entity.Album;
import com.tianye.mapper.AlbumMapper;
import com.tianye.mapper.ChapterMapper;
import com.tianye.mapper.LuceneAlbumMapper;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    ChapterMapper chapterMapper;
    @Autowired
    LuceneAlbumMapper luceneAlbumMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public AlbumDto queryAllAlbum(Integer page, Integer rows) {
//        PageHelper.startPage(page,rows);
//        List<Album> albums = albumMapper.selectAll();
        AlbumDto albumDto = new AlbumDto();
        List<Album> albums = albumMapper.queryAllAlbumByPage(page, rows);
        albumDto.setRows(albums);
        albumDto.setTotal(albumMapper.selectCount(null));
        return albumDto;
    }

    @Override
    public void insertAlbum(Album album) {
        albumMapper.insert(album);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Album queryOneAlbumById(Integer id){
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Album> queryAlbum(){
        return albumMapper.selectAll();
    };

    @Override
    public List<Album> export() {
        List<Album> albums = albumMapper.queryAllAlbum();
        return albums;
    }

    @Override
    public AlbumDto searchIndex(String param, Integer page , Integer rows){
        String[] strs = {"title", "author"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, strs, new IKAnalyzer());
        AlbumDto albumDto = null;
        try {
            Query query = multiFieldQueryParser.parse(param);
            albumDto = luceneAlbumMapper.searcherIndex(query, page, rows);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return albumDto;

    }

    @Override
    public void createIndex(Album album) {
        albumMapper.insert(album);
        luceneAlbumMapper.createIndex(album);
    }
}
