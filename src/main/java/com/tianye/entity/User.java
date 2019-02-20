package com.tianye.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User implements Serializable {
    @Id
    private Integer id;
    private String phone;
    private String password;
    private String salt;
    private String name;
    private String dharma;
    private Integer sex;
    private String status;
    private String province;
    private String city;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date regDate;
}
