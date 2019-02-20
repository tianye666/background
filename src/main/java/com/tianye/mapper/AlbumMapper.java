package com.tianye.mapper;

import com.tianye.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumMapper extends Mapper<Album> {
    public List<Album> queryAllAlbum();
    public List<Album> queryAllAlbumByPage(@Param("page")Integer curPage, @Param("rows")Integer pageSize);
}
