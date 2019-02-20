package com.tianye.mapper;

import com.tianye.dto.City;
import com.tianye.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    public List<City> queryAllCityDto();
    public Integer queryActivity(@Param(value = "num") Integer num);
    public void updateUserStatus(@Param(value = "id") Integer id,@Param(value = "status") Integer status);
}
