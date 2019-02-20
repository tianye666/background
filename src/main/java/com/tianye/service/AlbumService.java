package com.tianye.service;

import com.tianye.dto.AlbumDto;
import com.tianye.entity.Album;

import java.util.List;

public interface AlbumService {
    public AlbumDto queryAllAlbum(Integer page , Integer rows);
    public void insertAlbum(Album album);
    public Album queryOneAlbumById(Integer id);
    public List<Album> queryAlbum();
    public List<Album> export();
    public AlbumDto searchIndex(String param, Integer page , Integer rows);
    public void createIndex(Album album);
}
