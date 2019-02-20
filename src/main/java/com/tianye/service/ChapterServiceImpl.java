package com.tianye.service;

import com.tianye.entity.Chapter;
import com.tianye.entity.ChapterExample;
import com.tianye.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Chapter> queryChapterByAlbumId(Integer id) {
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.createCriteria().andAlbumIdEqualTo(id);
        List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
        return chapters;
    }

    @Override
    public void insertChapter(Chapter chapter) {
        chapterMapper.insert(chapter);

    }
}
