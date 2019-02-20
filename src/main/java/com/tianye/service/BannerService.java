package com.tianye.service;

import com.tianye.dto.BannerDto;
import com.tianye.entity.Banner;

public interface BannerService {
    public BannerDto queryAllBannerByPage(Integer page,Integer rows);
    public void deleteBannerById(Integer id);
    public void updateBanner(Banner banner);
    public void insertBanner(Banner banner);
}
