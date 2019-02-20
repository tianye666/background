package com.tianye.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
@ExcelTarget(value = "album")
public class Album implements Serializable  {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号",needMerge = true)
    private Integer id;
    @Excel(name = "标题_absent",needMerge = true)
    private String title;
    @Excel(name = "数量",needMerge = true)
    private Integer count;
    @Excel(name = "封面",needMerge = true,type =2 )
    private String coverImg;
    @Excel(name = "分数",needMerge = true)
    private Integer score;
    @Excel(name = "作者",needMerge = true)
    private String author;
    @Excel(name = "播音",needMerge = true)
    private String broadcast;
    @Excel(name = "描述",needMerge = true)
    private String brief;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "日期",needMerge = true,format = "yyyy-MM-dd",width = 20)
    private Date publishDate;
    @ExcelEntity
    @ExcelCollection(name = "章节详情",orderNum = "4")
    @Transient
    private List<Chapter> children;

}
