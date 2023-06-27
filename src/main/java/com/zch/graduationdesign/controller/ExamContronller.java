package com.zch.graduationdesign.controller;

import com.zch.graduationdesign.entity.Exam;

import com.zch.graduationdesign.mapper.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamContronller {
    @Autowired
    private ExamMapper examMapper;

    @GetMapping()
    public List<Exam> index(){
        List<Exam> all=examMapper.selectList(null);
        return all;
    }

}
