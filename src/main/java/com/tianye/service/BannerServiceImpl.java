package com.tianye.service;

import com.tianye.dto.BannerDto;
import com.tianye.entity.Banner;
import com.tianye.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public BannerDto queryAllBannerByPage(Integer page,Integer rows) {
        BannerDto bannerDto = new BannerDto();
        PageHelper.startPage(page,rows);
        List<Banner> banners = bannerMapper.selectAll();
        bannerDto.setRows(banners);
        bannerDto.setTotal(bannerMapper.selectCount(null));
        return bannerDto;
    }

    @Override
    public void deleteBannerById(Integer id) {
        bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerMapper.updateByPrimaryKey(banner);
    }

    @Override
    public void insertBanner(Banner banner) {
        bannerMapper.insert(banner);
    }
}
