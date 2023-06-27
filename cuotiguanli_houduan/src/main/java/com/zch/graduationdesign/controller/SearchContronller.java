package com.zch.graduationdesign.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zch.graduationdesign.entity.Questions;
import com.zch.graduationdesign.mapper.QuestionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchContronller {
    @Autowired
    private QuestionsMapper questionsMapper;
    @PostMapping("/searchquestion")
    public List<Questions> searchQueston(@RequestBody String[] data) {
        System.out.println(data);
        System.out.println(data[0]);
        System.out.println(data[1]);
        QueryWrapper<Questions> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",data[1]).and(i->i.like("stem",data[0]).or().like("knowledgepoint",data[0]));
        List<Questions> all=questionsMapper.selectList(queryWrapper);
        return  all;

    }

}
