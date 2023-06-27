package com.zch.graduationdesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("recordnum")
public class Recordnum {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String recorddate;
    private String recordnum;



}
