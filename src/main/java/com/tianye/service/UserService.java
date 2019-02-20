package com.tianye.service;

import com.tianye.dto.City;
import com.tianye.dto.UserDto;

import java.util.List;

public interface UserService {
    public UserDto queryAllUser(Integer page, Integer rows);
    public List<City> queryAllCityDto();
    public List<Integer> queryActivity(List<Integer> list);
    public void updateUserStatus(Integer id,Integer status);
}
