package com.tianye.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guru")
public class Guru implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String dharma;
    private String headPic;
    private String status;
}
