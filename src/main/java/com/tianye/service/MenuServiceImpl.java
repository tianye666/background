package com.tianye.service;

import com.tianye.entity.Menu;
import com.tianye.entity.MenuExample;
import com.tianye.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Menu> queryAllParentMenu() {
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentIdIsNull();
        List<Menu> menus = menuMapper.selectByExample(menuExample);
        return menus;
    }

    @Override
    public List<Menu> queryMenuByParentId(Integer parentId) {
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentIdEqualTo(parentId);
        List<Menu> menus = menuMapper.selectByExample(menuExample);
        for (Menu menu : menus) {
            menu.setText(menu.getTitle());
        }
        return menus;
    }
}
