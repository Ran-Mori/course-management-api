package com.service;

import com.IService.ICourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.TblCourse;
import com.mapper.TblCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private TblCourseMapper courseMapper;

    @Override
    public int insertCourse(TblCourse course) {
        return courseMapper.insert(course);
    }

    @Override
    public List<TblCourse> selectCoursesByUserId(Integer userId) {
        QueryWrapper<TblCourse> wrapper=new QueryWrapper<TblCourse>();
        wrapper.eq("user_id",userId);
        return courseMapper.selectList(wrapper);
    }
}
