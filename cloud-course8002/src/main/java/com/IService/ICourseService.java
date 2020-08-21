package com.IService;

import com.entity.TblCourse;

import java.util.List;

public interface ICourseService {
    int insertCourse(TblCourse course);
    List<TblCourse> selectCoursesByUserId(Integer userId);
}
