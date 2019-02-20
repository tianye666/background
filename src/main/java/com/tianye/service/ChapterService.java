package com.tianye.service;

import com.tianye.entity.Chapter;

import java.util.List;

public interface ChapterService {
    public List<Chapter> queryChapterByAlbumId(Integer id);
    public void insertChapter(Chapter chapter);
}
