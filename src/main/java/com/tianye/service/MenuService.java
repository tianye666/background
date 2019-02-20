package com.tianye.service;

import com.tianye.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> queryAllParentMenu();
    public List<Menu> queryMenuByParentId(Integer parentId);
}
