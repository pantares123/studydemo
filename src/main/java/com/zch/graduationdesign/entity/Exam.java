package com.zch.graduationdesign.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data

public class Exam {

    private String subject;
    private String[]  qtype;
    private int questionnum;
    private int point;
    private String knowledgepoint;

}
