package com.IService;

import com.entity.TblCourseTime;

import java.util.List;

public interface ICourseTimeService {
    int insertCourseTime(TblCourseTime courseTime);
    List<TblCourseTime> selectCourseTimesByCourseId(Integer courseId);
}
