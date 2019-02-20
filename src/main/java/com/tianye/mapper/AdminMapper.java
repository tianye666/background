package com.tianye.mapper;

import com.tianye.entity.Admin;
import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {
    public Admin queryAdminByUsername(String username);
}
