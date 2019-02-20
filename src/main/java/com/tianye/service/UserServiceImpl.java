package com.tianye.service;

import com.tianye.dto.City;
import com.tianye.dto.UserDto;
import com.tianye.entity.User;
import com.tianye.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<City> queryAllCityDto() {
        return userMapper.queryAllCityDto();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Integer> queryActivity(List<Integer> list) {

        for (int i = 0; i < list.size(); i++) {
            list.set(i,userMapper.queryActivity(list.get(i)));
        }
        return  list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public UserDto queryAllUser(Integer page , Integer rows) {
        UserDto userDto = new UserDto();
        PageHelper.startPage(page,rows);
        List<User> users = userMapper.selectAll();
        userDto.setRows(users);
        userDto.setTotal(userMapper.selectCount(null));
        return userDto;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public void updateUserStatus(Integer id, Integer status) {
        userMapper.updateUserStatus(id,status);
    }
}
