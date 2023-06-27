package com.zch.graduationdesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zch.graduationdesign.entity.Questions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionsMapper extends BaseMapper<Questions> {

}
