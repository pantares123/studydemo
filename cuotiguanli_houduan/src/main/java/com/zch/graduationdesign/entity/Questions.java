package com.zch.graduationdesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("questions")
public class Questions {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String qtype;
    private String subject;
    private String stem;
    private String knowledgepoint;
    private String opta;
    private String optb;
    private String optc;
    private String optd;
    private String answer;
    private String recordtime;
    private  int point;
    private String username;


    private String tag;
    private String exp;
    private String img;

}
