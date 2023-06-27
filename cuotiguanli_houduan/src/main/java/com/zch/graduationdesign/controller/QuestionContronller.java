package com.zch.graduationdesign.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zch.graduationdesign.entity.Exam;
import com.zch.graduationdesign.entity.Questions;
import com.zch.graduationdesign.entity.User;
import com.zch.graduationdesign.mapper.QuestionsMapper;
import com.zch.graduationdesign.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/questions")
public class QuestionContronller {


    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping()
    public List<Questions> index(){
        List<Questions> all=questionsMapper.selectList(null);
        return all;
    }

    @PostMapping("/search")
    public List<Questions> searchQueston(@RequestBody String[] data) {
        System.out.println(data);
        System.out.println(data[0]);
        System.out.println(data[1]);
        System.out.println(data[2]);
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("qtype", data[0]);
        columnMap.put("subject", data[1]);
        columnMap.put("username", data[2]);
        List<Questions> all = questionsMapper.selectByMap(columnMap);
        return all;
    }
    @PostMapping("/add")
    public String addQuestion(@RequestBody Questions questions) {
        System.out.println(questions);
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        if(questions.getPoint()<3){
            questions.setTag("简单");
        }
        else questions.setTag("困难");
        wrapper.eq("username",questions.getUsername()).setSql("questionnum=questionnum+1");

        userMapper.update(null,wrapper);
        questionsMapper.insert(questions);
        return "添加成功";
    }

    @PostMapping("/deletequestion")
    public Integer deleteQuestion(@RequestBody String[] data) {

        int i = Integer.parseInt(data[0]);
        String username=data[1];
        System.out.println(i);
        int info=questionsMapper.deleteById(i);
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("username",username).setSql("questionnum=questionnum-1");
        userMapper.update(null,wrapper);
        System.out.println(info);
        return info;
    }
    @PostMapping("/exam")
    public List<Questions> practice(@RequestBody Exam exam) {
        System.out.println(exam);
        System.out.println(exam.getQtype().length);
//        限制查询的题目数
        String questionsnum="limit "+exam.getQuestionnum();
        System.out.println(exam.getQuestionnum());
        System.out.println(questionsnum);
        List<String> qtype=new ArrayList<>();
        QueryWrapper<Questions> queryWrapper= new QueryWrapper<>();
        queryWrapper
                .eq("subject",exam.getSubject())
                .le("point",exam.getPoint())
                .like("knowledgepoint",exam.getKnowledgepoint()).last(questionsnum);
        // 由于传入的qtype类型可能为数组，为了方便查询，改为List，当传入长度为1时，以单个字符串判断，否则为数组判断
        if(exam.getQtype().length>1){
            for(int i=0;i<exam.getQtype().length;++i){
                qtype.add(exam.getQtype()[i]);
            }
            queryWrapper.in("qtype",qtype);
        }
        else {
            queryWrapper.eq("qtype",exam.getQtype()[0]);
        }
        List<Questions> all = questionsMapper.selectList(queryWrapper);
        return all;
    }


}

