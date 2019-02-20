package com.tianye.controller;

import com.alibaba.fastjson.JSONObject;
import com.tianye.dto.City;
import com.tianye.dto.UserDto;
import com.tianye.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("showAllUserByMap")
    public Map<String, List<City>> showAllUserByMap(){
        Map<String,List<City>> map = new HashMap<>();
        List<City> users = userService.queryAllCityDto();
        map.put("data",users);
        return map;
    }

    @RequestMapping("queryActivity")
    public List<Integer> queryActivity(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> list1 = userService.queryActivity(list);
        return list1;
    }

    @RequestMapping("queryAllUser")
    public UserDto queryAllUser(Integer page, Integer rows){
        return userService.queryAllUser(page,rows);
    }

    @RequestMapping("updateUserStatus")
    public void updateUserStatus(Integer id,Integer status){
        userService.updateUserStatus(id,status);
        showAllUserByGoeasy();
    }

    @RequestMapping("queryAllByGoeasy")
    public void showAllUserByGoeasy(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-66a9ac718fc24524b917d7c9270dce56");
        Map<String,List<City>> map = new HashMap<>();
        List<City> users = userService.queryAllCityDto();
        //map.put("data",users);
        goEasy.publish("140",JSONObject.toJSONString(users));


    }
    @RequestMapping("queryActivityByGoeasy")
    public void queryActivityByGoeasy(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-66a9ac718fc24524b917d7c9270dce56");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> list1 = userService.queryActivity(list);
        goEasy.publish("activity",JSONObject.toJSONString(list));
    }
}
