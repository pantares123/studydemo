package com.zch.graduationdesign.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zch.graduationdesign.entity.*;
import com.zch.graduationdesign.mapper.QtypenumMapper;
import com.zch.graduationdesign.mapper.QuestionsMapper;
import com.zch.graduationdesign.mapper.RecordnumMapper;
import com.zch.graduationdesign.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/count")
public class CountContronller {

    @Autowired
    private QuestionsMapper questionsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RecordnumMapper recordnumMapper;
    @Autowired
    private QtypenumMapper qtypenumMapper;

    @PostMapping("/getquestionnum")
    public List<Recordnum> getQuestonNum(@RequestBody String username) {
        System.out.println(username);
        StringBuffer buffer = new StringBuffer(username);
        //public StringBuffer deleteCharAt(int index):删除指定位置的字符，并返回本身
        buffer.deleteCharAt(username.length() - 1);//删除最后位的元素
        String param = buffer.toString();

        QueryWrapper<Recordnum> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",param);

        return recordnumMapper.selectList(queryWrapper);
    }
    @PostMapping("/updaterecordnum")
    public Integer updateRecordNum(@RequestBody Recordnum recordnum) {
        System.out.println(recordnum);
        QueryWrapper<Recordnum> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",recordnum.getUsername()).eq("recorddate",recordnum.getRecorddate());
        Recordnum results = recordnumMapper.selectOne(queryWrapper);
        if(results==null){
            recordnumMapper.insert(recordnum);

        }
        else {
            UpdateWrapper<Recordnum>  wrapper = new UpdateWrapper<>();
            wrapper.eq("username",recordnum.getUsername()).eq("recorddate",recordnum.getRecorddate()).set("recordnum",recordnum.getRecordnum());
           int update=recordnumMapper.update(null,wrapper);
            System.out.println(update);
        }

        return 0;
    }
    @PostMapping("/getqtypenum")
    public List<Qtypenum> getQtypeNum(@RequestBody String username) {
        System.out.println(username);
        StringBuffer buffer = new StringBuffer(username);
        buffer.deleteCharAt(username.length() - 1);//删除最后位的元素
        String param = buffer.toString();

        QueryWrapper<Qtypenum> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",param);

        return qtypenumMapper.selectList(queryWrapper);
    }

}

