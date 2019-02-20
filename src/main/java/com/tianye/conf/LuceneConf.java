package com.tianye.conf;

import com.tianye.mapper.LuceneAlbumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConf {
    @Bean
    public LuceneAlbumMapper getLuceneAlbumMapper(){
        return new LuceneAlbumMapper();
    }

}
