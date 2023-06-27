package com.zch.graduationdesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("qtypenum")
public class Qtypenum {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private Integer choicenum;
    private Integer blanknum;
    private Integer tfnum;
    private Integer othernum;

    private Integer shortanswernum;
}
