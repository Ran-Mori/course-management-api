package com.service;

import com.IService.ICourseTimeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.TblCourseTime;
import com.mapper.TblCourseTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTimeService implements ICourseTimeService {
    @Autowired
    private TblCourseTimeMapper courseTimeMapper;

    @Override
    public int insertCourseTime(TblCourseTime courseTime) {
        return courseTimeMapper.insert(courseTime);
    }

    @Override
    public List<TblCourseTime> selectCourseTimesByCourseId(Integer courseId) {
        QueryWrapper<TblCourseTime> wrapper=new QueryWrapper<TblCourseTime>();
        wrapper.eq("course_id",courseId);
        return courseTimeMapper.selectList(wrapper);
    }
}
