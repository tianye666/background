package com.tianye.mapper;

import com.tianye.dto.AlbumDto;
import com.tianye.entity.Album;
import com.tianye.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class LuceneAlbumMapper {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public void createIndex(Album album) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromAlbum = getDocFromAlbum(album);
        try {
            indexWriter.addDocument(docFromAlbum);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    public void deleteIndex(String id) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }

    }

    public void updateIndex(Album album) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromAlbum = getDocFromAlbum(album);
        try {
            indexWriter.updateDocument(new Term("id", album.getId().toString()), docFromAlbum);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    public AlbumDto searcherIndex(Query query, Integer page, Integer pageSize) {
        AlbumDto albumDto = new AlbumDto();
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        List<Album> list = null;
        try {
            TopDocs topDocs = indexSearcher.search(query,page*pageSize);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            System.out.println(topDocs.totalHits);
            albumDto.setTotal(topDocs.totalHits);
            list = new ArrayList<>();
            for (int i = (page - 1) * pageSize; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Album album = getAlbumFromDoc(document);
                list.add(album);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        albumDto.setRows(list);
        return albumDto;
    }

    public Document getDocFromAlbum(Album album) {

        Document document = new Document();
        document.add(new StringField("id", album.getId().toString(), Field.Store.YES));
        document.add(new StringField("author", album.getAuthor(), Field.Store.YES));
        document.add(new StringField("brief", album.getBrief(), Field.Store.YES));
        document.add(new StringField("broadcast", album.getBroadcast(), Field.Store.YES));
        document.add(new StringField("coverImg", album.getCoverImg(), Field.Store.YES));
        document.add(new StringField("title", album.getTitle(), Field.Store.YES));
        document.add(new StringField("count", album.getCount().toString(), Field.Store.YES));
        document.add(new StringField("score", album.getScore().toString(), Field.Store.YES));
        document.add(new StringField("publishDate", sdf.format(album.getPublishDate()),Field.Store.YES));
        return document;
    }

    public Album getAlbumFromDoc(Document document)  {
        Album album = new Album();
        album.setId(Integer.parseInt(document.get("id")));
        album.setAuthor(document.get("author"));
        album.setBrief(document.get("brief"));
        album.setBroadcast(document.get("broadcast"));
        album.setCoverImg(document.get("coverImg"));
        album.setTitle(document.get("title"));
        album.setCount(Integer.parseInt(document.get("count")));
        album.setScore(Integer.parseInt(document.get("score")));
        try {
            album.setPublishDate(sdf.parse(document.get("publishDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return album;
    }

}
