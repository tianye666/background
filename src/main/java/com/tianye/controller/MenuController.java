package com.tianye.controller;

import com.tianye.entity.Menu;
import com.tianye.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @RequestMapping("queryAllParentMenu")
    public List<Menu> queryAllParentMenu(){
        return menuService.queryAllParentMenu();
    }
    @RequestMapping("queryMenuByParentId")
    public List<Menu> queryMenuByParentId(Integer id){
        return menuService.queryMenuByParentId(id);
    }


}
